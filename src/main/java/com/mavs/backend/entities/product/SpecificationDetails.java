package com.mavs.backend.entities.product;

import org.springframework.stereotype.Component;

@Component
public class SpecificationDetails {
    private String key;

    private String value;

    public SpecificationDetails() {
    }

    public SpecificationDetails(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SpecificationDetails [key=" + key + ", value=" + value + "]";
    }

    
    
}
