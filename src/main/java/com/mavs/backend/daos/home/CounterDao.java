package com.mavs.backend.daos.home;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mavs.backend.entities.home.Counter;

public interface CounterDao extends MongoRepository<Counter,String> {

    public Counter findCounterByParameter(String parameter);
    
}
