package com.bucksbuddy.bucksbuddy.journey;

import com.bucksbuddy.bucksbuddy.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users/journeys")
public class JourneyController {

    @Autowired
    JourneyService journeyService;

    // Get all journeys by user uuid
    @GetMapping
    public ResponseEntity<Iterable<Journey>> getAllJourneys(@RequestHeader("uuid") String uuid) {
        Iterable<Journey> journeys = journeyService.getAllJourneys(uuid);
        return ResponseEntity.ok(journeys);
    }

    // Get journey by id
    @GetMapping("/{id}")
    public ResponseEntity<Journey> getJourneyById(@PathVariable int id) {
        Optional<Journey> journey = journeyService.getJourneyById(id);
        return journey.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new journey
    @PostMapping
    public ResponseEntity<Journey> createJourney(@RequestHeader("uuid") String uuid, @RequestBody Journey journey) {
        Journey createdJourney = journeyService.createJourney(uuid, journey);
        return new ResponseEntity<>(createdJourney, HttpStatus.CREATED);
    }

    // Delete a journey by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJourney(@PathVariable int id) {
        boolean isDeleted = journeyService.deleteJourney(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Update a journey by id
    @PutMapping("/{id}")
    public ResponseEntity<Journey> updateJourney(@PathVariable int id, @RequestBody Journey journey) {
        Optional<Journey> updatedJourney = journeyService.updateJourney(id, journey);
        return updatedJourney.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
