package com.bucksbuddy.bucksbuddy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpendService {

    @Autowired
    ExpendRepo repo;

    public Expenditures saveExpenditure(Expenditures expenditure){
        return repo.save(expenditure);
    }

    public Expenditures getExpenditureById(Long id){
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Expenditure not found"));
    }
}