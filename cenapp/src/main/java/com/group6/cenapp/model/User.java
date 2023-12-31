package com.group6.cenapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "lastname")
    @JsonProperty("lastname")
    private String lastName;
    private String email;
    private String password;
    private boolean enabled;

//    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
//    @OneToMany(mappedBy = "user")
//    @JsonProperty("city_id")
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;


//    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @JsonProperty("role_id")
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;


//    @JsonBackReference
//    @JsonProperty("image_id")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;


//    public User(int id) {
//        this.id = id;
//
//    }

    public User() {
    }

    public User(int id, String name, String lastName, String email, String password, boolean enabled, City city, Role role, Image image) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.city = city;
        this.role = role;
        this.image = image;
    }




//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

//    public City getCity() {
//        return city;
//    }
//
//    public void setCity(City city) {
//        this.city = city;
//    }
//
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//    public Image getImage() {
//        return image;
//    }
//    public void setImage(Image image) {
//        this.image = image;
//    }


    @Override
    public String toString() {
        return "User{" +
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
