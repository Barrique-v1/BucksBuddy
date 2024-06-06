package com.bucksbuddy.bucksbuddy.journey;

import com.bucksbuddy.bucksbuddy.user.User;
import com.bucksbuddy.bucksbuddy.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JourneyService {

    @Autowired
    private JourneyRepository journeyRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Journey> getAllJourneys(String uuid) {
        return journeyRepository.findAllByUserUuid(uuid);
    }

    public Optional<Journey> getJourneyById(int id) {
        return journeyRepository.findById(id);
    }

    public Journey createJourney(String uuid, Journey journey) {
        Optional<User> user = userRepository.findByUuid(uuid);
        user.ifPresent(journey::setUser);
        return journeyRepository.save(journey);
    }

    public boolean deleteJourney(int id) {
        if (journeyRepository.existsById(id)) {
            journeyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Journey> updateJourney(int id, Journey updatedJourney) {
        return journeyRepository.findById(id).map(journey -> {
            journey.setName(updatedJourney.getName());
            journey.setExpenditures(updatedJourney.getExpenditures());
            return journeyRepository.save(journey);
        });
    }
}
