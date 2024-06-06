package com.bucksbuddy.bucksbuddy.journey;

import com.bucksbuddy.bucksbuddy.user.User;
import com.bucksbuddy.bucksbuddy.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class JourneyController {

    @Autowired
    JourneyRepository journeyRepository;

    @Autowired
    UserRepository userRepository;


    // Get all journeys by user uuid
    @GetMapping("/journeys")
    public ResponseEntity<Iterable<Journey>> getAllJourneys(@RequestHeader("uuid") String uuid) {
        Iterable<Journey> journeys = journeyRepository.findAllJourneys(uuid);
        return new ResponseEntity<>(journeys, HttpStatus.OK);
    }

    // Get journey by id
    @GetMapping("/journey")
    public ResponseEntity<Journey> getJourneyById(@RequestParam(value = "id") int id) {
        Optional<Journey> journeyInDB = journeyRepository.findById(id);
        if (!journeyInDB.isPresent()) {
            return new ResponseEntity("No journey found with id " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(journeyInDB.get(), HttpStatus.OK);
    }

    // Create journey for a specific user
    @PostMapping("/user/{userId}/journey")
    public ResponseEntity<Journey> createJourney(@PathVariable int userId, @RequestBody Journey newJourney) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            return new ResponseEntity("No user found with id " + userId, HttpStatus.NOT_FOUND);
        }
        User user = userOptional.get();
        newJourney.setUser(user);
        journeyRepository.save(newJourney);
        return new ResponseEntity<>(newJourney, HttpStatus.OK);
    }

    // Delete journey by id
    @DeleteMapping("/journey")
    public ResponseEntity deleteJourney(@RequestParam(value = "id") int id) {
        Optional<Journey> journeyInDB = journeyRepository.findById(id);
        if (!journeyInDB.isPresent()) {
            return new ResponseEntity("No journey found with id " + id, HttpStatus.NOT_FOUND);
        }
        journeyRepository.delete(journeyInDB.get());
        return new ResponseEntity("Journey with id " + id + " deleted", HttpStatus.OK);
    }

    // Update journey
    @PutMapping("/journey")
    public ResponseEntity<Journey> updateJourney(@RequestBody Journey updatedJourney) {
        Optional<Journey> journeyInDB = journeyRepository.findById(updatedJourney.getId());
        if (!journeyInDB.isPresent()) {
            return new ResponseEntity("No journey found with id " + updatedJourney.getId(), HttpStatus.NOT_FOUND);
        }
        journeyRepository.save(updatedJourney);
        return new ResponseEntity<>(updatedJourney, HttpStatus.OK);
    }
}
