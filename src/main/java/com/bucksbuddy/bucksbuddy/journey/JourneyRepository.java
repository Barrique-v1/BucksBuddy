package com.bucksbuddy.bucksbuddy.journey;

import com.bucksbuddy.bucksbuddy.expenditure.Expenditure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JourneyRepository extends CrudRepository<Journey, Integer>{
    List<Journey> findAllByUserUuid(String uuid);
    Optional<Journey> findById(int id);
}
