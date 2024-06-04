package com.bucksbuddy.bucksbuddy.expenditure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/expenditures")
public class ExpenditureController {

    @Autowired
    private ExpenditureService expenditureService;

    @GetMapping
    public List<Expenditure> getAllExpenditures() {
        return expenditureService.getAllExpenditures();
    }

    @GetMapping("/{id}")
    public Optional<Expenditure> getExpenditureById(@PathVariable int id) {
        return expenditureService.getExpenditureById(id);
    }

    @PostMapping
    public Expenditure createExpenditure(@RequestBody Expenditure expenditure) {
        return expenditureService.saveExpenditure(expenditure);
    }

    @DeleteMapping("/{id}")
    public void deleteExpenditure(@PathVariable int id) {
        expenditureService.deleteExpenditure(id);
    }

    @PutMapping("/{id}")
    public Expenditure updateExpenditure(@PathVariable int id, @RequestBody Expenditure expenditureDetails) {
        return expenditureService.updateExpenditure(id, expenditureDetails);
    }
}
