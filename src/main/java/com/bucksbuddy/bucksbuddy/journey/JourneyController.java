//package com.bucksbuddy.bucksbuddy.journey;
//
//import com.bucksbuddy.bucksbuddy.journey.Journey;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api")
//public class JourneyController {
//
//    @Autowired
//    JourneyRepository repo;
//
//    // get all journeys
//    // new journey
//    // update journey
//    // delete journey
//    // get journey by id
//    // get journey by user id
//    // get journey by user id and journey id
//    // get expense by id
//
//
//    //TODO: Hier nur beispiele aus Expenditure kopiert, muss noch angepasst werden
//    @GetMapping("/journey")
//    public ResponseEntity<Journey> getJourneyById(@RequestParam(value = "id") int id) {
//        Optional<Journey> expenditureInDB = repo.findById(id);
//        if (!expenditureInDB.isPresent()) {
//            return new ResponseEntity("No expenditure found with id " + id, HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(expenditureInDB.get(), HttpStatus.OK);
//    }
//    // get all expenses
//    @GetMapping("/journey")
//    public ResponseEntity<Iterable<Journey>> getExpenditures() {
//        Iterable<Journey> expenditures = repo.findAll();
//        return new ResponseEntity<>(expenditures, HttpStatus.OK);
//    }
//
//    // create expense
//    @PostMapping("/journey")
//    public ResponseEntity<Journey> createJourney(@RequestBody Journey newExpenditure){
//        repo.save(newExpenditure);
//        return new ResponseEntity<>(newExpenditure, HttpStatus.OK);
//    }
//
//    // delete expense by id
//    @DeleteMapping("/journey")
//    public ResponseEntity deleteJourney(@RequestParam(value = "id") int id) {
//        Optional<Journey> JourneyInDB = repo.findById(id);
//        if (!JourneyInDB.isPresent()) {
//            return new ResponseEntity("No expenditure found with id " + id, HttpStatus.NOT_FOUND);
//        }
//        repo.delete(JourneyInDB.get());
//        return new ResponseEntity("Expenditure with id " + id + " deleted", HttpStatus.OK);
//    }
//
//    // update expense
//    @PutMapping("/journey")
//    public ResponseEntity<Journey> updateJourney(@RequestBody Journey updatedExpenditure) {
//        Optional<Journey> JourneyInDB = repo.findById(updatedExpenditure.getId());
//        if (!JourneyInDB.isPresent()) {
//            return new ResponseEntity("No expenditure found with id " + updatedExpenditure.getId(), HttpStatus.NOT_FOUND);
//        }
//        repo.save(updatedExpenditure);
//        return new ResponseEntity<>(updatedExpenditure, HttpStatus.OK);
//    }
//
//}
