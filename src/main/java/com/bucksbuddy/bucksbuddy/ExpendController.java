package com.bucksbuddy.bucksbuddy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExpendController {

    @Autowired
    ExpendService service;

    @PostMapping("/expenses")
    public Expenditures createExpenditure(@RequestBody Expenditures expenditure){
        return service.saveExpenditure(expenditure);
    }

    @GetMapping("/expenses/{id}")
    public Expenditures getExpenditureById(@PathVariable Long id){
        return service.getExpenditureById(id);
    }
}
