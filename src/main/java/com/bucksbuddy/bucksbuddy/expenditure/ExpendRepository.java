package com.bucksbuddy.bucksbuddy.expenditure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpendRepository extends CrudRepository<Expenditure, Integer>{
}
