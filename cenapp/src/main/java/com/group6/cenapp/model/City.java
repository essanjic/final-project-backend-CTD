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
    private List<Food> food;

    public City() {
    }

    public City(Integer id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public City(Integer id, String name, Country country, List<Food> food) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.food = food;
    }

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

    public List<Food> getProduct() {
        return food;
    }

    public void setProduct(List<Food> food) {
        this.food = food;
    }
}
