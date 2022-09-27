package com.mavs.backend.helper;

import org.springframework.stereotype.Component;

@Component
public class ProductCategoryModelsResponse {
    
    private String productimg;

    private String productname;

    private String modelNum;

    public ProductCategoryModelsResponse() {
    }

    public ProductCategoryModelsResponse(String productimg, String productname, String modelNum) {
        this.productimg = productimg;
        this.productname = productname;
        this.modelNum = modelNum;
    }

    public String getProductimg() {
        return productimg;
    }

    public void setProductimg(String productimg) {
        this.productimg = productimg;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getModelNum() {
        return modelNum;
    }

    public void setModelNum(String modelNum) {
        this.modelNum = modelNum;
    }

    @Override
    public String toString() {
        return "ProductCategoryModelsResponse [modelNum=" + modelNum + ", productimg=" + productimg + ", productname="
                + productname + "]";
    }

    
}
