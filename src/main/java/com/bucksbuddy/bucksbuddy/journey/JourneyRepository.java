package com.bucksbuddy.bucksbuddy.journey;

import com.bucksbuddy.bucksbuddy.journey.Journey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyRepository extends CrudRepository<Journey, Integer>{
}
