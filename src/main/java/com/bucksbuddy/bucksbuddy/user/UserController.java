package com.bucksbuddy.bucksbuddy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    // Get user by id
    @GetMapping("/user")
    public ResponseEntity<User> getUserById(@RequestParam(value = "id") int id) {
        Optional<User> userInDB = userRepository.findById(id);
        if (!userInDB.isPresent()) {
            return new ResponseEntity("No user found with id " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userInDB.get(), HttpStatus.OK);
    }

    // Get all users
    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getUsers() {
        Iterable<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Create user
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        userRepository.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    // Delete user by id
    @DeleteMapping("/user")
    public ResponseEntity deleteUser(@RequestParam(value = "id") int id) {
        Optional<User> userInDB = userRepository.findById(id);
        if (!userInDB.isPresent()) {
            return new ResponseEntity("No user found with id " + id, HttpStatus.NOT_FOUND);
        }
        userRepository.delete(userInDB.get());
        return new ResponseEntity("User with id " + id + " deleted", HttpStatus.OK);
    }

    // Update user
    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@RequestBody User updatedUser) {
        Optional<User> userInDB = userRepository.findById(updatedUser.getId());
        if (!userInDB.isPresent()) {
            return new ResponseEntity("No user found with id " + updatedUser.getId(), HttpStatus.NOT_FOUND);
        }
        userRepository.save(updatedUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
