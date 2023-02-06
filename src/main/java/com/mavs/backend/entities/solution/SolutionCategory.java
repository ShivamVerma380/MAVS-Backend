package com.mavs.backend.entities.solution;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "solcategory")
@Component
public class SolutionCategory {
    
    @Id
    private String category;

    private String catimg;

    private String catdescription;

    private ArrayList<Solution> solutions;

    

    public SolutionCategory() {
    }



    public SolutionCategory(String category, String catimg, String catdescription,ArrayList<Solution> solutions) {
        this.category = category;
        this.catimg = catimg;
        this.catdescription = catdescription;
        this.solutions = solutions;
    }



    public String getCategory() {
        return category;
    }



    public void setCategory(String category) {
        this.category = category;
    }



    public String getCatimg() {
        return catimg;
    }



    public void setCatimg(String catimg) {
        this.catimg = catimg;
    }



    public String getCatdescription() {
        return catdescription;
    }



    public void setCatdescription(String catdescription) {
        this.catdescription = catdescription;
    }



    public List<Solution> getSolutions() {
        return solutions;
    }



    public void setSolutions(ArrayList<Solution> solutions) {
        this.solutions = solutions;
    }



    @Override
    public String toString() {
        return "SolutionCategory [catdescription=" + catdescription + ", category=" + category + ", catimg=" + catimg
                + ", solutions=" + solutions + "]";
    }

    
}
