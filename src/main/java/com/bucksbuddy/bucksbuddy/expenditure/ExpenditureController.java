package com.bucksbuddy.bucksbuddy.expenditure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users/journeys/expenditures")
public class ExpenditureController {

    @Autowired
    private ExpenditureRepository expenditureRepository;

    @GetMapping
    public ResponseEntity<Iterable<Expenditure>> getAllExpenditures(@RequestParam("JourneyID") int journeyId) {
        Iterable<Expenditure> expenditures = expenditureRepository.findByJourneyId(journeyId);
        return ResponseEntity.ok(expenditures);
    }

    //TODO Implement getAllExpendituresByJourneyId
    //TODO Implement createExpenditure
    //TODO Implement deleteExpenditure
    //TODO Implement updateExpenditure
}
