package com.mavs.backend.daos.product;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mavs.backend.entities.product.ProductCategory;

public interface ProductCategoryDao extends MongoRepository<ProductCategory,String> {
    
    // public ProductCategory findCategoryByProductCategory(String productcategory);
    public ProductCategory findProductCategoryByProductcategory(String productcategory);
}
