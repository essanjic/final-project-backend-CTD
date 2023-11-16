package com.group6.cenapp.services;

import com.group6.cenapp.exception.DuplicatedValueException;
import com.group6.cenapp.model.FoodAttribute;

import java.util.List;
import java.util.Optional;

public interface ProductAttributeService {
    List<FoodAttribute> getAllProductAttribute();
    Optional<FoodAttribute> getProductAttributeById(Integer id);
    FoodAttribute saveProductAttribute(FoodAttribute foodAttribute) throws DuplicatedValueException;
    FoodAttribute updateProductAttribute(FoodAttribute foodAttribute);
    void deleteProductAttributeById(Integer id);
    Optional<FoodAttribute> getProductAttributeByIcon(String icon);
}
