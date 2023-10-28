package com.cenapp.controller;

import com.cenapp.model.User;
import com.cenapp.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserServiceImplementation userServiceImplementation;

    public UserController(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @GetMapping("/users")
    public List<User> findAllUsers(){

        return userServiceImplementation.findAll();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        User userForID = userServiceImplementation.findById(id);

        if (userForID != null) {
            return ResponseEntity.ok(userForID);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/save")
    public ResponseEntity<User> save(@RequestBody User user){
        User newUser = userServiceImplementation.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> update(@PathVariable long id, User user){
        User updatedUser = userServiceImplementation.findById(id);
        updatedUser.setName(user.getName());
        updatedUser.setLastname(user.getLastname());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setPhone(user.getPhone());
        updatedUser.setCity(user.getCity());

        User userUpdated = userServiceImplementation.save(updatedUser);

        return new ResponseEntity<>(userUpdated, HttpStatus.OK);

    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<HashMap<String, Boolean>> delete(@PathVariable long id){
        this.userServiceImplementation.delete(id);

        HashMap<String, Boolean> response = new HashMap<>();

        response.put("deleted", true);
        return ResponseEntity.ok(response);
    }


}
