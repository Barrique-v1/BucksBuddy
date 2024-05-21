package com.bucksbuddy.bucksbuddy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ExpendController {

    @Autowired
    ExpendRepo repo;

    // get expense by id
    @GetMapping("/expense")
    public ResponseEntity<Expenditure> getExpenditureById(@RequestParam(value = "id") int id) {
        Optional<Expenditure> expenditureInDB = repo.findById(id);
        if (!expenditureInDB.isPresent()) {
            return new ResponseEntity("No expenditure found with id " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(expenditureInDB.get(), HttpStatus.OK);
    }
    // get all expenses
    @GetMapping("/expenses")
    public ResponseEntity<Iterable<Expenditure>> getExpenditures() {
        Iterable<Expenditure> expenditures = repo.findAll();
        return new ResponseEntity<>(expenditures, HttpStatus.OK);
    }

    // create expense
    @PostMapping("/expense")
    public ResponseEntity<Expenditure> createExpenditure(@RequestBody Expenditure newExpenditure){
        repo.save(newExpenditure);
        return new ResponseEntity<>(newExpenditure, HttpStatus.OK);
    }

    // delete expense by id
    @DeleteMapping("/expense")
    public ResponseEntity deleteExpenditure(@RequestParam(value = "id") int id) {
        Optional<Expenditure> expenditureInDB = repo.findById(id);
        if (!expenditureInDB.isPresent()) {
            return new ResponseEntity("No expenditure found with id " + id, HttpStatus.NOT_FOUND);
        }
        repo.delete(expenditureInDB.get());
        return new ResponseEntity("Expenditure with id " + id + " deleted", HttpStatus.OK);
    }

    // update expense
    @PutMapping("/expense")
    public ResponseEntity<Expenditure> updateExpenditure(@RequestBody Expenditure updatedExpenditure) {
        Optional<Expenditure> expenditureInDB = repo.findById(updatedExpenditure.getId());
        if (!expenditureInDB.isPresent()) {
            return new ResponseEntity("No expenditure found with id " + updatedExpenditure.getId(), HttpStatus.NOT_FOUND);
        }
        repo.save(updatedExpenditure);
        return new ResponseEntity<>(updatedExpenditure, HttpStatus.OK);
    }
}
