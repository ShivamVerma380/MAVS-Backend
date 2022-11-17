package com.mavs.backend.entities.home;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "companydescription")
@Component
public class CompanyDescription {
    @Id
    private String title;

    private String description;

    public CompanyDescription() {
    }

    public CompanyDescription(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
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

    @Override
    public String toString() {
        return "CompanyDescription [title=" + title + ", description=" + description + "]";
    }

    
}
