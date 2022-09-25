package com.mavs.backend.daos.solution;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mavs.backend.entities.solution.Solution;

public interface SolutionDao extends MongoRepository<Solution,String>{
    public Solution findSolutionByTitle(String title);

    // public List<Solution> findSolutionByCategory(String solcategory);
}
