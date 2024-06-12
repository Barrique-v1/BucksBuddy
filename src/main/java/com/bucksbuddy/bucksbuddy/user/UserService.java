package com.bucksbuddy.bucksbuddy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean validate(String email, String password) {
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        return user.isPresent();
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserByUuid(String uuid) {
        return userRepository.findByUuid(uuid);
    }

    public void deleteUser(String uuid) {
        getUserByUuid(uuid).ifPresent(user -> userRepository.delete(user));
    }

    // Implement the update user's email method
    public Optional<User> updateUserEmail(String uuid, String newEmail) {
        Optional<User> userOpt = getUserByUuid(uuid);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setEmail(newEmail);
            userRepository.save(user);
            return Optional.of(user);
        }
        return Optional.empty();
    }

    // Implement the update user's password method
    public Optional<User> updateUserPassword(String uuid, String newPassword) {
        Optional<User> userOpt = getUserByUuid(uuid);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPassword(newPassword);
            userRepository.save(user);
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
