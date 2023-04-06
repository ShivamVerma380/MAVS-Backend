package com.mavs.backend.entities.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "solutions")
@Component
public class Solution {
    
    @Id
    private String title;

    private String description;

    private String coverimg;

    private String solcategory;

    private ArrayList<SolutionFeatures> solutionFeatures;

    private ArrayList<SolutionBenefits> solutionBenefits;

    private String solimg1;

    private String solimg2;

    private String solimg3;

    private String[] productused;

    private List<String> productusedlist;

    public Solution() {
    }

    public Solution(String title, String description, String coverimg,String solcategory, ArrayList<SolutionFeatures> solutionFeatures,
            ArrayList<SolutionBenefits> solutionBenefits, String solimg1, String solimg2, String solimg3,
            String[] productused,List<String> productusedlist) {
        this.title = title;
        this.description = description;
        this.coverimg = coverimg;
        this.solutionFeatures = solutionFeatures;
        this.solutionBenefits = solutionBenefits;
        this.solimg1 = solimg1;
        this.solimg2 = solimg2;
        this.solimg3 = solimg3;
        this.productused = productused;
        this.solcategory = solcategory;
        this.productusedlist = productusedlist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverimg() {
        return coverimg;
    }

    public void setCoverimg(String coverimg) {
        this.coverimg = coverimg;
    }

    public ArrayList<SolutionFeatures> getSolutionFeatures() {
        return solutionFeatures;
    }

    public void setSolutionFeatures(ArrayList<SolutionFeatures> solutionFeatures) {
        this.solutionFeatures = solutionFeatures;
    }

    public ArrayList<SolutionBenefits> getSolutionBenefits() {
        return solutionBenefits;
    }

    public void setSolutionBenefits(ArrayList<SolutionBenefits> solutionBenefits) {
        this.solutionBenefits = solutionBenefits;
    }

    public String getSolimg1() {
        return solimg1;
    }

    public void setSolimg1(String solimg1) {
        this.solimg1 = solimg1;
    }

    public String getSolimg2() {
        return solimg2;
    }

    public void setSolimg2(String solimg2) {
        this.solimg2 = solimg2;
    }

    public String getSolimg3() {
        return solimg3;
    }

    public void setSolimg3(String solimg3) {
        this.solimg3 = solimg3;
    }

    public String[] getProductused() {
        return productused;
    }

    public void setProductused(String[] productused) {
        this.productused = productused;
    }

    public String getSolcategory() {
        return solcategory;
    }

    public void setSolcategory(String solcategory) {
        this.solcategory = solcategory;
    }

    

    @Override
    public String toString() {
        return "Solution [title=" + title + ", description=" + description + ", coverimg=" + coverimg + ", solcategory="
                + solcategory + ", solutionFeatures=" + solutionFeatures + ", solutionBenefits=" + solutionBenefits
                + ", solimg1=" + solimg1 + ", solimg2=" + solimg2 + ", solimg3=" + solimg3 + ", productused="
                + Arrays.toString(productused) + ", productusedlist=" + productusedlist + "]";
    }

    public List<String> getProductusedlist() {
        return productusedlist;
    }

    public void setProductusedlist(List<String> productusedlist) {
        this.productusedlist = productusedlist;
    }

    
    
}
