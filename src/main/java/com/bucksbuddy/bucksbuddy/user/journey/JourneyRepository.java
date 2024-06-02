package com.bucksbuddy.bucksbuddy.user.journey;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyRepository extends CrudRepository<Journey, Integer>{
}
