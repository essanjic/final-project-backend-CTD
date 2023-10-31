package com.group6.cenapp.services;

import com.group6.cenapp.exception.BadRequestException;
import com.group6.cenapp.model.Image;

import java.util.List;
import java.util.Optional;

public interface ImageService {
    List<Image> getAllImages();
    Optional<Image> getImageById(Integer id);
    Image saveImage(Image image, Integer productId);
    //Image saveImage(Image image);
    Image updateImage(Image image, Integer productId) throws BadRequestException;
    void deleteImageById(Integer id);
}
