package com.mavs.backend.helper;

import org.springframework.stereotype.Component;

@Component
public class ProductUsedResponse {
    
    private String modelNum;

    private String productName;

    private String productImage;

    public ProductUsedResponse() {
    }

    public ProductUsedResponse(String modelNum, String productName, String productImage) {
        this.modelNum = modelNum;
        this.productName = productName;
        this.productImage = productImage;
    }

    public String getModelNum() {
        return modelNum;
    }

    public void setModelNum(String modelNum) {
        this.modelNum = modelNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    @Override
    public String toString() {
        return "ProductUsedResponse [modelNum=" + modelNum + ", productName=" + productName + ", productImage="
                + productImage + "]";
    }

    

}
