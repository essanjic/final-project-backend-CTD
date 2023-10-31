package com.group6.cenapp.services;

import com.group6.cenapp.exception.DuplicatedValueException;
import com.group6.cenapp.model.ProductAttribute;

import java.util.List;
import java.util.Optional;

public interface ProductAttributeService {
    List<ProductAttribute> getAllProductAttribute();
    Optional<ProductAttribute> getProductAttributeById(Integer id);
    ProductAttribute saveProductAttribute(ProductAttribute productAttribute) throws DuplicatedValueException;
    ProductAttribute updateProductAttribute(ProductAttribute productAttribute);
    void deleteProductAttributeById(Integer id);
    Optional<ProductAttribute> getProductAttributeByIcon(String icon);
}
