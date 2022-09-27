package com.mavs.backend.helper;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ProductCategoryResponse {
    
    private String title;

    private List<ProductCategoryModelsResponse> products;

    

    public ProductCategoryResponse() {
    }



    public ProductCategoryResponse(String title, List<ProductCategoryModelsResponse> products) {
        this.title = title;
        this.products = products;
    }



    public String getTitle() {
        return title;
    }



    public void setTitle(String title) {
        this.title = title;
    }



    public List<ProductCategoryModelsResponse> getProducts() {
        return products;
    }



    public void setProducts(List<ProductCategoryModelsResponse> products) {
        this.products = products;
    }



    @Override
    public String toString() {
        return "ProductCategoryResponse [products=" + products + ", title=" + title + "]";
    }

    
    
}
