package com.bucksbuddy.bucksbuddy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/email/{email}/password/{password}")
    public Optional<User> getUserByEmailAndPassword(@PathVariable String email, @PathVariable String password) {
        return userService.getUserByEmailAndPassword(email, password);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    @GetMapping("/uuid/{uuid}")
    public Optional<User> getUserByUuid(@PathVariable String uuid) {
        return userService.getUserByUuid(uuid);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody UserLoginRequest request) {
        return userService.validateUser(request.getEmail(), request.getPassword());
    }
}
