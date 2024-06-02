package com.bucksbuddy.bucksbuddy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository repository;

    // create user
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User newUser){
        repository.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }
}
