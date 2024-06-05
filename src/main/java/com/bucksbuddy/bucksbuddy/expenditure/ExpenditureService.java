package com.bucksbuddy.bucksbuddy.expenditure;

import com.bucksbuddy.bucksbuddy.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenditureService {

    @Autowired
    private ExpenditureRepository expenditureRepository;

    @Autowired
    private UserRepository userRepository;

    // Get all expenditures by user id
    public List<Expenditure> getAllExpendituresByUserId(int userId) {
        return expenditureRepository.findByUserId(userId);
    }

    // Get expenditure by id
    public Optional<Expenditure> getExpenditureById(int id) {
        return expenditureRepository.findById(id);
    }

    // Create expenditure
    public Expenditure saveExpenditure(int userId, ExpenditureDTO expenditureDTO) {
        return userRepository.findById(userId).map(user -> {
            Expenditure expenditure = new Expenditure();
            expenditure.setName(expenditureDTO.getName());
            expenditure.setAmount(expenditureDTO.getAmount());
            expenditure.setDate(expenditureDTO.getDate());
            expenditure.setUser(user);
            return expenditureRepository.save(expenditure);
        }).orElseThrow(() -> new RuntimeException("User not found"));
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
            return Optional.of(expenditureRepository.save(expenditure));
        }).orElse(Optional.empty());
    }
}

