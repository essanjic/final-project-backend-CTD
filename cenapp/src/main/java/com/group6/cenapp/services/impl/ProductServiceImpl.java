package com.group6.cenapp.services.impl;
import com.group6.cenapp.model.Category;
import com.group6.cenapp.model.City;
import com.group6.cenapp.model.Food;
import com.group6.cenapp.repository.FoodRepository;
import com.group6.cenapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private FoodRepository foodRepository;

    @Override
    public List<Food> getAllProducts() {
        return foodRepository.findAll();
    }

    @Override
    public Optional<Food> getProductById(Integer id) {
        return foodRepository.findById(id);
    }

    @Override
    public Food saveProduct(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public Food updateProduct(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public void deleteProductById(Integer id) {
        foodRepository.deleteById(id);
    }

    @Override
    public List<Food> getProductsByCategory(Category id) {
        return foodRepository.getByCategory(id);
    }

    public List<Food> getProductsByCity(City id)  { return foodRepository.getByCity(id);
    }

    @Override
    public List<Food> getProductsByRangeDate(LocalDate check_in_date, LocalDate check_out_date) {
        //System.out.println(check_in_date + " --- " + check_out_date);
        return foodRepository.getByRangeDate(check_in_date,check_out_date);
    }

    @Override
    public List<Food> getProductsByCityAndRangeDate(Integer city_id, LocalDate check_in_date, LocalDate check_out_date) {
        return foodRepository.getByCityAndRangeDate(city_id, check_in_date,check_out_date);
    }
    @Override
    public List<Food> getRandomProduct() {
        return foodRepository.getRandomProduct();
    }
}
