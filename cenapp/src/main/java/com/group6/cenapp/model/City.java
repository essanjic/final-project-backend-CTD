package com.group6.cenapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToOne
    @JoinColumn(name="country_id", referencedColumnName = "id")
    private Country country;

    @OneToMany(mappedBy = "city")
    @JsonIgnore
    private List<Restaurant> restaurant;

    public City(Integer id) {
        this.id = id;
    }

//    public City(Integer id, String name, Country country) {
//        this.id = id;
//        this.name = name;
//        this.country = country;
//    }
//
//    public City(Integer id, String name, Country country, List<Restaurant> restaurant) {
//        this.id = id;
//        this.name = name;
//        this.country = country;
//        this.restaurant = restaurant;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Restaurant> getProduct() {
        return restaurant;
    }

    public void setProduct(List<Restaurant> restaurant) {
        this.restaurant = restaurant;
    }
}
