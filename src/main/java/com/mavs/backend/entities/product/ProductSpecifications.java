package com.mavs.backend.entities.product;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ProductSpecifications {
    
    private String head;

    private List<SpecificationDetails> specs;

    public ProductSpecifications() {
    }

    public ProductSpecifications(String head, List<SpecificationDetails> specs) {
        this.head = head;
        this.specs = specs;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public List<SpecificationDetails> getSpecs() {
        return specs;
    }

    public void setSpecs(List<SpecificationDetails> specs) {
        this.specs = specs;
    }

    @Override
    public String toString() {
        return "ProductSpecifications [head=" + head + ", specs=" + specs + "]";
    }

    

    
}
