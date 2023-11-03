package com.group6.cenapp.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name="image")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Image.class)
@JsonIgnoreProperties(value = "restaurant")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String url;

    @ManyToOne
    @JoinColumn(name="restaurant_id", referencedColumnName = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Restaurant restaurant;


    public Image(Integer id, String name, String url, Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.restaurant = restaurant;
    }

    public Image() {
    }
    public Image(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Restaurant getProduct() {
        return restaurant;
    }

    public void setProduct(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
