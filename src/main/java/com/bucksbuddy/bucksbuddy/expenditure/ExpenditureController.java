package com.bucksbuddy.bucksbuddy.expenditure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/{userId}/expenditures")
public class ExpenditureController {

    @Autowired
    private ExpenditureService expenditureService;

    // Get all expenditures by user id
    @GetMapping
    public ResponseEntity<List<Expenditure>> getAllExpendituresByUserId(@PathVariable int userId) {
        List<Expenditure> expenditures = expenditureService.getAllExpendituresByUserId(userId);
        return ResponseEntity.ok(expenditures);
    }

    // Get expenditure by id
    @GetMapping("/{id}")
    public ResponseEntity<Expenditure> getExpenditureById(@PathVariable int id) {
        Optional<Expenditure> expenditure = expenditureService.getExpenditureById(id);
        return expenditure.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create expenditure
    @PostMapping
    public ResponseEntity<Expenditure> createExpenditure(@PathVariable int userId, @RequestBody ExpenditureDTO expenditureDTO) {
        Expenditure newExpenditure = expenditureService.saveExpenditure(userId, expenditureDTO);
        return ResponseEntity.ok(newExpenditure);
    }

    // Delete expenditure by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpenditure(@PathVariable int id) {
        boolean isRemoved = expenditureService.deleteExpenditure(id);
        if (!isRemoved) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    // Update expenditure by id
    @PutMapping("/{id}")
    public ResponseEntity<Expenditure> updateExpenditure(@PathVariable int id, @RequestBody ExpenditureDTO expenditureDTO) {
        Optional<Expenditure> updatedExpenditure = expenditureService.updateExpenditure(id, expenditureDTO);
        return updatedExpenditure.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
