package com.mavs.backend.daos.admin;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mavs.backend.entities.admin.Admin;

public interface AdminDao extends MongoRepository<Admin,String>{

    public Admin findAdminByEmail(String email);
}
