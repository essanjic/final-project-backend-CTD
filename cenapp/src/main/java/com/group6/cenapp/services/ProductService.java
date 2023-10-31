package com.group6.cenapp.services;

import com.group6.cenapp.model.Category;
import com.group6.cenapp.model.City;
import com.group6.cenapp.model.Food;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Food> getAllProducts();
    Optional<Food> getProductById(Integer id);
    List<Food>getProductsByCategory(Category id);
    Food saveProduct(Food food);
    Food updateProduct(Food food);
    void deleteProductById(Integer id);
    List<Food>getProductsByCity(City id);
    List<Food> getProductsByRangeDate(LocalDate check_in_date, LocalDate check_out_date);
    List<Food> getProductsByCityAndRangeDate(Integer city_id, LocalDate check_in_date, LocalDate check_out_date);

    List<Food> getRandomProduct();

}
