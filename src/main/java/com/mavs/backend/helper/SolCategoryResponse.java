package com.mavs.backend.helper;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SolCategoryResponse {
    
    private String category;

    private List<String> solutionName;

    public SolCategoryResponse() {
    }

    public SolCategoryResponse(String category, List<String> solutionName) {
        this.category = category;
        this.solutionName = solutionName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getSolutionName() {
        return solutionName;
    }

    public void setSolutionName(List<String> solutionName) {
        this.solutionName = solutionName;
    }

    @Override
    public String toString() {
        return "SolCategoryResponse [category=" + category + ", solutionName=" + solutionName + "]";
    }
    
}
