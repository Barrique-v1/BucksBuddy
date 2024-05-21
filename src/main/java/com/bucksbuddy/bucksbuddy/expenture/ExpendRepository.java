package com.bucksbuddy.bucksbuddy.expenture;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpendRepository extends CrudRepository<Expenditure, Integer>{
}
