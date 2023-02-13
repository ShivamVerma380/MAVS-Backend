package com.mavs.backend.daos.home;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mavs.backend.entities.home.Home;

public interface HomeDao extends MongoRepository<Home,String>{
    public Home findHomeByName(String name);
    //findHomeByName should compulsary be in this syntax @id after By with capital
    //and entity name after find in captial
}
