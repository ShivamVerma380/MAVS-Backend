package com.mavs.backend.daos;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mavs.backend.entities.Admin;

public interface AdminDao extends MongoRepository<Admin,String>{

    public Admin findAdminByEmail(String email);
}
