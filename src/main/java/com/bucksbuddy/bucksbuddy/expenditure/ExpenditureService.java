package com.bucksbuddy.bucksbuddy.expenditure;

import com.bucksbuddy.bucksbuddy.user.User;
import com.bucksbuddy.bucksbuddy.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenditureService {

    @Autowired
    private ExpenditureRepository expenditureRepository;

    @Autowired
    private UserRepository userRepository;

//    // Get all expenditures by user uuid
//    public List<Expenditure> getAllExpenditures(String uuid) {
//        return expenditureRepository.findAllByUserUuid(uuid);
//    }

    //TODO Implement getAllExpendituresByJourneyId
    //TODO Implement createExpenditure
    //TODO Implement deleteExpenditure
    //TODO Implement updateExpenditure
}
