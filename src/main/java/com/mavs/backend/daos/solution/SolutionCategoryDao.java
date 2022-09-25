package com.mavs.backend.daos.solution;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mavs.backend.entities.solution.SolutionCategory;

public interface SolutionCategoryDao extends MongoRepository<SolutionCategory,String> {
    
    // public SolutionCategory findSolutionCategorybyCategoryName(String category);
}
