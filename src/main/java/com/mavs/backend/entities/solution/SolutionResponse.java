package com.mavs.backend.entities.solution;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mavs.backend.helper.ProductUsedResponse;

@Component
public class SolutionResponse {
    
    private String title;

    private String description;

    private String coverimg;

    private String solcategory;

    private ArrayList<SolutionFeatures> solutionFeatures;

    private ArrayList<SolutionBenefits> solutionBenefits;

    private String solimg1;

    private String solimg2;

    private String solimg3;

    private List<ProductUsedResponse> productused;

    public SolutionResponse() {
    }

    public SolutionResponse(String title, String description, String coverimg, String solcategory,
            ArrayList<SolutionFeatures> solutionFeatures, ArrayList<SolutionBenefits> solutionBenefits, String solimg1,
            String solimg2, String solimg3, List<ProductUsedResponse> productused) {
        this.title = title;
        this.description = description;
        this.coverimg = coverimg;
        this.solcategory = solcategory;
        this.solutionFeatures = solutionFeatures;
        this.solutionBenefits = solutionBenefits;
        this.solimg1 = solimg1;
        this.solimg2 = solimg2;
        this.solimg3 = solimg3;
        this.productused = productused;
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

    public String getSolcategory() {
        return solcategory;
    }

    public void setSolcategory(String solcategory) {
        this.solcategory = solcategory;
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

    public List<ProductUsedResponse> getProductused() {
        return productused;
    }

    public void setProductused(List<ProductUsedResponse> productused) {
        this.productused = productused;
    }

    @Override
    public String toString() {
        return "SolutionResponse [title=" + title + ", description=" + description + ", coverimg=" + coverimg
                + ", solcategory=" + solcategory + ", solutionFeatures=" + solutionFeatures + ", solutionBenefits="
                + solutionBenefits + ", solimg1=" + solimg1 + ", solimg2=" + solimg2 + ", solimg3=" + solimg3
                + ", productused=" + productused + "]";
    }

    
}
