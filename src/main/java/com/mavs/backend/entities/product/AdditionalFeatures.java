package com.mavs.backend.entities.product;

import org.springframework.stereotype.Component;

@Component
public class AdditionalFeatures {
    private String title;

    private String description;

    public AdditionalFeatures() {
    }

    public AdditionalFeatures(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "AdditionalFeatures [description=" + description + ", title=" + title + "]";
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
    
}
