package com.mavs.backend.daos.home;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mavs.backend.entities.home.CompanyDescription;

public interface CompanyDescriptionDao extends MongoRepository<CompanyDescription,String> {
    public CompanyDescription findCompanyDescriptionByTitle(String title);
}
