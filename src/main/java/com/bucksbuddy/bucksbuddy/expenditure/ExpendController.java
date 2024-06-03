package com.bucksbuddy.bucksbuddy.expenditure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

@RequestMapping("/api")
public class ExpendController {

    @Autowired
    ExpendRepository expendRepository;

    // get expense by id
    @GetMapping("/expenditure")
    public ResponseEntity<Expenditure> getExpenditureById(@RequestParam(value = "id") int id) {
        Optional<Expenditure> expenditureInDB = expendRepository.findById(id);
        if (!expenditureInDB.isPresent()) {
            return new ResponseEntity("No expenditure found with id " + id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(expenditureInDB.get(), HttpStatus.OK);
    }
    // get all expenses
    @GetMapping("/expenditures")
    public ResponseEntity<Iterable<Expenditure>> getExpenditures() {
        Iterable<Expenditure> expenditures = expendRepository.findAll();
        return new ResponseEntity<>(expenditures, HttpStatus.OK);
    }

    // create expense
    @PostMapping("/expenditure")
    public ResponseEntity<Expenditure> createExpenditure(@RequestBody Expenditure newExpenditure){
        expendRepository.save(newExpenditure);
        return new ResponseEntity<>(newExpenditure, HttpStatus.OK);
    }

    // delete expense by id
    @DeleteMapping("/expenditure")
    public ResponseEntity deleteExpenditure(@RequestParam(value = "id") int id) {
        Optional<Expenditure> expenditureInDB = expendRepository.findById(id);
        if (!expenditureInDB.isPresent()) {
            return new ResponseEntity("No expenditure found with id " + id, HttpStatus.NOT_FOUND);
        }
        expendRepository.delete(expenditureInDB.get());
        return new ResponseEntity("Expenditure with id " + id + " deleted", HttpStatus.OK);
    }

    // update expense
    @PutMapping("/expenditure")
    public ResponseEntity<Expenditure> updateExpenditure(@RequestBody Expenditure updatedExpenditure) {
        Optional<Expenditure> expenditureInDB = expendRepository.findById(updatedExpenditure.getId());
        if (!expenditureInDB.isPresent()) {
            return new ResponseEntity("No expenditure found with id " + updatedExpenditure.getId(), HttpStatus.NOT_FOUND);
        }
        expendRepository.save(updatedExpenditure);
        return new ResponseEntity<>(updatedExpenditure, HttpStatus.OK);
    }
}
