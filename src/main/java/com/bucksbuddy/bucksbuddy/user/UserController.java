package com.bucksbuddy.bucksbuddy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173, https://bucksbuddyfrontend.onrender.com")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest request) {
        Optional<User> validUser = userService.getUserByEmail(request.getEmail());
        if (validUser.isPresent() && userService.validate(request.getEmail(), request.getPassword())) {
            return new ResponseEntity<>("UUID: " + validUser.get().getUuid(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid credentials", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User savedUser = userService.saveUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestHeader String uuid) {
        Optional<User> userOpt = userService.getUserByUuid(uuid);
        if (userOpt.isPresent()) {
            userService.deleteUser(uuid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/password")
    public ResponseEntity<User> updateUserPassword(@RequestHeader String uuid, @RequestBody Map<String, String> payload) {
        String newPassword = payload.get("newPassword");
        if (newPassword == null || newPassword.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<User> updatedUser = userService.updateUserPassword(uuid, newPassword);
        return updatedUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
