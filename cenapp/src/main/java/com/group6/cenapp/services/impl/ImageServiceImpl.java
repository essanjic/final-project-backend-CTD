package com.group6.cenapp.services.impl;

import com.group6.cenapp.exception.BadRequestException;
import com.group6.cenapp.model.Image;
import com.group6.cenapp.model.Restaurant;
import com.group6.cenapp.repository.ImageRepository;
import com.group6.cenapp.repository.RestaurantRepository;
import com.group6.cenapp.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepository imageRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public Optional<Image> getImageById(Integer id) {
        return imageRepository.findById(id);
    }

    @Override
    public Image saveImage(Image image, Integer productId) {
        Optional<Restaurant> optProduct = restaurantRepository.findById(productId);

            image.setProduct(optProduct.get());
            return imageRepository.save(image);
        }

    @Override
    public Image updateImage(Image image,Integer productId) throws BadRequestException {
        Optional<Image> imageBdd = imageRepository.findById(image.getId());
        boolean productDidntChange = imageBdd.get().getProduct().getId().equals(productId);

        if (!productDidntChange) {
            throw new BadRequestException("La imagen no corresponde al producto con ID: "+ productId);
        }

        imageBdd.get().setName(image.getName());
        imageBdd.get().setUrl(image.getUrl());
        return imageRepository.save(imageBdd.get());
    }

    @Override
    public void deleteImageById(Integer id) {
        imageRepository.deleteById(id);
    }
}
