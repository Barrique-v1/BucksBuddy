package com.bucksbuddy.bucksbuddy.expenditure;

import com.bucksbuddy.bucksbuddy.journey.Journey;

import com.bucksbuddy.bucksbuddy.journey.JourneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenditureService {

    @Autowired
    private ExpenditureRepository expenditureRepository;

    @Autowired
    private JourneyRepository journeyRepository;

    // Get all expenditures by user id
    public List<Expenditure> getAllExpendituresByUserId(int userId) {
        return expenditureRepository.findByJourneyUserId(userId);
    }

    // Get expenditure by id
    public Optional<Expenditure> getExpenditureById(int id) {
        return expenditureRepository.findById(id);
    }

    // Create expenditure
    public Expenditure saveExpenditure(int userId, ExpenditureDTO expenditureDTO) {
        int journeyId = expenditureDTO.getJourneyId();

        return journeyRepository.findById(journeyId).map(journeyEntity -> {
            if (journeyEntity.getUser().getId() != userId) {
                throw new RuntimeException("Journey does not belong to the user");
            }
            Expenditure expenditure = new Expenditure();
            expenditure.setName(expenditureDTO.getName());
            expenditure.setAmount(expenditureDTO.getAmount());
            expenditure.setDate(expenditureDTO.getDate());
            expenditure.setJourney(journeyEntity);
            return expenditureRepository.save(expenditure);
        }).orElseThrow(() -> new RuntimeException("Journey not found"));
    }


    // Delete expenditure by id
    public boolean deleteExpenditure(int id) {
        return expenditureRepository.findById(id).map(expenditure -> {
            expenditureRepository.delete(expenditure);
            return true;
        }).orElse(false);
    }

    // Update expenditure by id
    public Optional<Expenditure> updateExpenditure(int id, ExpenditureDTO expenditureDTO) {
        return expenditureRepository.findById(id).map(expenditure -> {
            expenditure.setName(expenditureDTO.getName());
            expenditure.setAmount(expenditureDTO.getAmount());
            expenditure.setDate(expenditureDTO.getDate());
            int journeyId = expenditureDTO.getJourneyId();
            Journey journey = journeyRepository.findById(journeyId)
                    .orElseThrow(() -> new RuntimeException("Journey not found"));
            expenditure.setJourney(journey);
            return Optional.of(expenditureRepository.save(expenditure));
        }).orElse(Optional.empty());
    }
}
