package com.bucksbuddy.bucksbuddy.expenditure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenditureService {

    @Autowired
    private ExpenditureRepository expenditureRepository;

    public List<Expenditure> getAllExpenditures() {
        return expenditureRepository.findAll();
    }

    public Optional<Expenditure> getExpenditureById(int id) {
        return expenditureRepository.findById(id);
    }

    public Expenditure saveExpenditure(Expenditure expenditure) {
        return expenditureRepository.save(expenditure);
    }

    public void deleteExpenditure(int id) {
        expenditureRepository.deleteById(id);
    }

    public Expenditure updateExpenditure(int id, Expenditure expenditureDetails) {
        Expenditure expenditure = expenditureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expenditure not found with id " + id));

        expenditure.setName(expenditureDetails.getName());
        expenditure.setAmount(expenditureDetails.getAmount());
        expenditure.setDate(expenditureDetails.getDate());
        expenditure.setUser(expenditureDetails.getUser());

        return expenditureRepository.save(expenditure);
    }
}

