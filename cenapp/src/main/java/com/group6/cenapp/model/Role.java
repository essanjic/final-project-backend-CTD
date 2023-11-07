package com.group6.cenapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("role_id")
    private int id;
    private String name;

   @OneToMany(mappedBy = "role")
    private List<User> users;

//    public Role() {
//        this.id = 8;
//    }

//    public Role(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }

    public Role(int id) {
        this.id = id;
        switch (id) {
            case 1:
                setName("USER");
                break;
            case 2:
                setName("ADMINISTRATOR");
                break;
        }
    }

    public Role() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
