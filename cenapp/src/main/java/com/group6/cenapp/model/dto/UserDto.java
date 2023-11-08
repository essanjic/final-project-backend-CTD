package com.group6.cenapp.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.group6.cenapp.model.City;
import com.group6.cenapp.model.Image;
import com.group6.cenapp.model.Role;
import lombok.*;

@Getter
@Setter
//@Builder
@AllArgsConstructor
//@NoArgsConstructor
//@Data
public class UserDto {
//    @JsonProperty("id")
    private int id;
//    @JsonProperty("name")
    private String name;
    @JsonProperty("lastname")
    private String lastName;
//    @JsonProperty("email")
    private String email;
//    @JsonProperty("password")
    private String password;
//    @JsonProperty("enabled")
    private boolean enabled;
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonProperty("city_id")
    private City city;
    @JsonProperty("role_id")
    private Role role;
//    @JsonProperty("image")
    private Image image;

    public UserDto(){
        super();
    }

//    @JsonCreator
//    public UserDto(int id, String name, String lastName, String email, String password, boolean enabled, City city, Role role, Image image) {
//        this.id = id;
//        this.name = name;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//        this.enabled = enabled;
//        this.city = city;
//        this.role = role;
//        this.image = image;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
//
//    @JsonIgnoreProperties(allowSetters = true)
//    public City getCity() {
//        return city;
//    }
//
//    public void setCity(City city) {
//        this.city = city;
//    }

//
//    public Role getRole() {
//
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//
//    public Image getImage() {return image;}
//
//    public void setImage(Image image) {this.image = image;}


    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", city=" + city +
                ", role=" + role +
                ", image=" + image +
                '}';
    }
}