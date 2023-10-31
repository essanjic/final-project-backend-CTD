package com.group6.cenapp.services;

import com.group6.cenapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Integer id);
    User saveUser(User user);
    User updateUser(User user);
    void deleteUserById(Integer id);
    }
