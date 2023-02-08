package com.mavs.backend.helper;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.mavs.backend.entities.product.AdditionalFeatures;
import com.mavs.backend.entities.product.ProductDescription;

@Component
public class ProductsDetailsResponse {
    public String modelNumber;

    private String productName;

    private String productHighlights;
    
    private String productImage1;

    private String productImage2;

    private String productImage3;

    private String videoLink;

    private String productPrice;

    private ArrayList<ProductDescription> productDescriptions;

    private ArrayList<AdditionalFeatures> additionalFeatures;

    private ArrayList<String> imgSrc;

    private String index;

    private String brochureLink;
    

    public ProductsDetailsResponse() {
    }



    



    public ProductsDetailsResponse(String modelNumber, String productName, String productHighlights,
            String productImage1, String productImage2, String productImage3, String videoLink, String productPrice,
            ArrayList<ProductDescription> productDescriptions, ArrayList<AdditionalFeatures> additionalFeatures,
            ArrayList<String> imgSrc,String index,String brochureLink) {
        this.modelNumber = modelNumber;
        this.productName = productName;
        this.productHighlights = productHighlights;
        this.productImage1 = productImage1;
        this.productImage2 = productImage2;
        this.productImage3 = productImage3;
        this.videoLink = videoLink;
        this.productPrice = productPrice;
        this.productDescriptions = productDescriptions;
        this.additionalFeatures = additionalFeatures;
        this.imgSrc = imgSrc;
        this.index = index;
        this.brochureLink = brochureLink;
    }



    



    public String getModelNumber() {
        return modelNumber;
    }



    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }



    public String getProductName() {
        return productName;
    }



    public void setProductName(String productName) {
        this.productName = productName;
    }



    public String getProductHighlights() {
        return productHighlights;
    }



    public void setProductHighlights(String productHighlights) {
        this.productHighlights = productHighlights;
    }



    public String getProductImage1() {
        return productImage1;
    }



    public void setProductImage1(String productImage1) {
        this.productImage1 = productImage1;
    }



    public String getProductImage2() {
        return productImage2;
    }



    public void setProductImage2(String productImage2) {
        this.productImage2 = productImage2;
    }



    public String getProductImage3() {
        return productImage3;
    }



    public void setProductImage3(String productImage3) {
        this.productImage3 = productImage3;
    }



    public String getVideoLink() {
        return videoLink;
    }



    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }



    public String getProductPrice() {
        return productPrice;
    }



    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }



    public ArrayList<ProductDescription> getProductDescriptions() {
        return productDescriptions;
    }



    public void setProductDescriptions(ArrayList<ProductDescription> productDescriptions) {
        this.productDescriptions = productDescriptions;
    }



   



    



    public ArrayList<AdditionalFeatures> getAdditionalFeatures() {
        return additionalFeatures;
    }



    public void setAdditionalFeatures(ArrayList<AdditionalFeatures> additionalFeatures) {
        this.additionalFeatures = additionalFeatures;
    }







    public ArrayList<String> getImgSrc() {
        return imgSrc;
    }







    public void setImgSrc(ArrayList<String> imgSrc) {
        this.imgSrc = imgSrc;
    }







    public String getIndex() {
        return index;
    }







    public void setIndex(String index) {
        this.index = index;
    }







    public String getBrochureLink() {
        return brochureLink;
    }







    public void setBrochureLink(String brochureLink) {
        this.brochureLink = brochureLink;
    }







    @Override
    public String toString() {
        return "ProductsDetailsResponse [modelNumber=" + modelNumber + ", productName=" + productName
                + ", productHighlights=" + productHighlights + ", productImage1=" + productImage1 + ", productImage2="
                + productImage2 + ", productImage3=" + productImage3 + ", videoLink=" + videoLink + ", productPrice="
                + productPrice + ", productDescriptions=" + productDescriptions + ", additionalFeatures="
                + additionalFeatures + ", imgSrc=" + imgSrc + ", index=" + index + ", brochureLink=" + brochureLink
                + "]";
    }

    
    


}
