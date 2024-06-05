package com.bucksbuddy.bucksbuddy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        return user.isPresent();
    }

    public Optional<Integer> getUserIdByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(User::getId);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public User updateUser(int id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setExpenditures(userDetails.getExpenditures());

        return userRepository.save(user);
    }
}
