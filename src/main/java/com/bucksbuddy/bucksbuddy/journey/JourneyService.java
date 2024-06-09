package com.bucksbuddy.bucksbuddy.journey;

import com.bucksbuddy.bucksbuddy.expenditure.Expenditure;
import com.bucksbuddy.bucksbuddy.user.User;
import com.bucksbuddy.bucksbuddy.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public Optional<Journey> updateJourneyName(int id, String name) {
        return journeyRepository.findById(id).map(journey -> {
            journey.setName(name);
            return journeyRepository.save(journey);
        });
    }

    public Optional<String> getHomeCurrency(int id) {
        return journeyRepository.findById(id).map(Journey::getVacCurr);
    }

    public Optional<Integer> getJourneyBudget(int id) {
        return journeyRepository.findById(id).map(Journey::getBudget);
    }

    public Optional<String> getVacCurr(int id) {
        return journeyRepository.findById(id).map(Journey::getHomeCurr);
    }

    public Optional<String> getStartDate(int id) {
        return journeyRepository.findById(id).map(Journey::getStartDate);
    }

    public Optional<String> getEndDate(int id) {
        return journeyRepository.findById(id).map(Journey::getEndDate);
    }

    public Optional<Journey> updateJourneyCurrency(int id, String currency) {
        return journeyRepository.findById(id).map(journey -> {
            journey.setHomeCurr(currency);
            return journeyRepository.save(journey);
        });
    }

    public Optional<Journey> updateJourneyBudget(int id, int budget) {
        return journeyRepository.findById(id).map(journey -> {
            journey.setBudget(budget);
            return journeyRepository.save(journey);
        });
    }

    public Optional<Journey> updateVacCurr(int id, String vacCurr) {
        return journeyRepository.findById(id).map(journey -> {
            journey.setVacCurr(vacCurr);
            return journeyRepository.save(journey);
        });
    }
}
