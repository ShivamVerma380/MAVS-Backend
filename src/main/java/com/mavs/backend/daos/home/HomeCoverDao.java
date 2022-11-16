package com.mavs.backend.daos.home;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mavs.backend.entities.home.HomeCover;

public interface HomeCoverDao extends MongoRepository<HomeCover,String> {
    public HomeCover findHomeCoverByDescription(String description);
}
