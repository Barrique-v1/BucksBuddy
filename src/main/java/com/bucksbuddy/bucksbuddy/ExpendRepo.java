package com.bucksbuddy.bucksbuddy;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpendRepo extends CrudRepository<Expenditures, Long>{
}
