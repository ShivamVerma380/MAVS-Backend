package com.mavs.backend.daos.product;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mavs.backend.entities.product.Product;

public interface ProductDao extends MongoRepository<Product,String> {
    public Product findProductBymodelNumber(String modelNumber);

    
}
