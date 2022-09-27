package com.mavs.backend.entities.product;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "productcategory")
@Component
public class ProductCategory {
    
    @Id
    private String productcategory;

    private List<String> modelNum;

    public ProductCategory() {
    }

    public ProductCategory(String productcategory, List<String> modelNum) {
        this.productcategory = productcategory;
        this.modelNum = modelNum;
    }

    public String getProductcategory() {
        return productcategory;
    }

    public void setProductcategory(String productcategory) {
        this.productcategory = productcategory;
    }

    public List<String> getModelNum() {
        return modelNum;
    }

    public void setModelNum(List<String> modelNum) {
        this.modelNum = modelNum;
    }

    @Override
    public String toString() {
        return "ProductCategory [modelNum=" + modelNum + ", productcategory=" + productcategory + "]";
    }

    
}
