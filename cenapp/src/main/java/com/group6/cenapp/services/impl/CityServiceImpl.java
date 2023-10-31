package com.group6.cenapp.services.impl;
import com.group6.cenapp.model.City;
import com.group6.cenapp.repository.CityRepository;
import com.group6.cenapp.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> getCityById(Integer id) {
        return cityRepository.findById(id);
    }

    @Override
    public City saveCity(City city) {
        return cityRepository.save(city);
    }
    @Override
    public City updateCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void deleteCityById(Integer id) {
        cityRepository.deleteById(id);
    }

}
