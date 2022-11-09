package com.mavs.backend.daos.home;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mavs.backend.entities.home.Home;

public interface HomeDao extends MongoRepository<Home,String>{
    public Home findHomeByName(String name);
}
