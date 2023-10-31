package com.group6.cenapp.services.impl;
import com.group6.cenapp.model.Category;
import com.group6.cenapp.model.City;
import com.group6.cenapp.model.Product;
import com.group6.cenapp.repository.ProductRepository;
import com.group6.cenapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsByCategory(Category id) {
        return productRepository.getByCategory(id);
    }

    public List<Product> getProductsByCity(City id)  { return productRepository.getByCity(id);
    }

    @Override
    public List<Product> getProductsByRangeDate(LocalDate check_in_date, LocalDate check_out_date) {
        //System.out.println(check_in_date + " --- " + check_out_date);
        return productRepository.getByRangeDate(check_in_date,check_out_date);
    }

    @Override
    public List<Product> getProductsByCityAndRangeDate(Integer city_id, LocalDate check_in_date, LocalDate check_out_date) {
        return productRepository.getByCityAndRangeDate(city_id, check_in_date,check_out_date);
    }
    @Override
    public List<Product> getRandomProduct() {
        return productRepository.getRandomProduct();
    }
}
