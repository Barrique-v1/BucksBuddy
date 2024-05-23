package com.bucksbuddy.bucksbuddy.expenture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

@RequestMapping("/api")
public class ExpendController {

    @Autowired
    ExpendRepository repo;

    // get expense by id
    @CrossOrigin(origins = "https://bucksbuddyfrontend.onrender.com")
    @GetMapping("/expenditure")
    public ResponseEntity<Expenditure> getExpenditureById(@RequestParam(value = "id") int id) {
        Optional<Expenditure> expenditureInDB = repo.findById(id);
        if (!expenditureInDB.isPresent()) {
            return new ResponseEntity("No expenditure found with id " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(expenditureInDB.get(), HttpStatus.OK);
    }
    // get all expenses
    @GetMapping("/expenditures")
    public ResponseEntity<Iterable<Expenditure>> getExpenditures() {
        Iterable<Expenditure> expenditures = repo.findAll();
        return new ResponseEntity<>(expenditures, HttpStatus.OK);
    }

    // create expense
    @PostMapping("/expenditure")
    public ResponseEntity<Expenditure> createExpenditure(@RequestBody Expenditure newExpenditure){
        repo.save(newExpenditure);
        return new ResponseEntity<>(newExpenditure, HttpStatus.OK);
    }

    // delete expense by id
    @DeleteMapping("/expenditure")
    public ResponseEntity deleteExpenditure(@RequestParam(value = "id") int id) {
        Optional<Expenditure> expenditureInDB = repo.findById(id);
        if (!expenditureInDB.isPresent()) {
            return new ResponseEntity("No expenditure found with id " + id, HttpStatus.NOT_FOUND);
        }
        repo.delete(expenditureInDB.get());
        return new ResponseEntity("Expenditure with id " + id + " deleted", HttpStatus.OK);
    }

    // update expense
    @PutMapping("/expenditure")
    public ResponseEntity<Expenditure> updateExpenditure(@RequestBody Expenditure updatedExpenditure) {
        Optional<Expenditure> expenditureInDB = repo.findById(updatedExpenditure.getId());
        if (!expenditureInDB.isPresent()) {
            return new ResponseEntity("No expenditure found with id " + updatedExpenditure.getId(), HttpStatus.NOT_FOUND);
        }
        repo.save(updatedExpenditure);
        return new ResponseEntity<>(updatedExpenditure, HttpStatus.OK);
    }
}
