package com.group6.cenapp.services.impl;

import com.group6.cenapp.exception.DuplicatedValueException;
import com.group6.cenapp.model.FoodAttribute;
import com.group6.cenapp.repository.FoodAttributeRepository;
import com.group6.cenapp.services.ProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductAttributeServiceImpl implements ProductAttributeService {

    @Autowired
    FoodAttributeRepository foodAttributeRepository;

    @Override
    public List<FoodAttribute> getAllProductAttribute() {
        return foodAttributeRepository.findAll();
    }

    @Override
    public Optional<FoodAttribute> getProductAttributeById(Integer id) {
        return foodAttributeRepository.findById(id);
    }

    @Override
    public FoodAttribute saveProductAttribute(FoodAttribute foodAttribute) throws DuplicatedValueException {
        Optional<FoodAttribute> existIcon = foodAttributeRepository.getByIcon(foodAttribute.getIcon());
        if(existIcon.isPresent()){
            throw new DuplicatedValueException("Ya existe este ícono: '" + existIcon.get().getName() + "'");
        }
        return foodAttributeRepository.save(foodAttribute);
    }

    @Override
    public FoodAttribute updateProductAttribute(FoodAttribute foodAttribute) {
        return foodAttributeRepository.save(foodAttribute);
    }

    @Override
    public void deleteProductAttributeById(Integer id) {
        foodAttributeRepository.deleteById(id);
    }


    @Override
    public Optional<FoodAttribute> getProductAttributeByIcon(String icon) {
        return foodAttributeRepository.getByIcon(icon);
    }
}
