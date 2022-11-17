package com.mavs.backend.entities.home;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document(collection = "homecover")
public class HomeCover {
    
    @Id
    private String description;

    private String img;

    public HomeCover() {
    }

    public HomeCover(String description, String img) {
        this.description = description;
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "HomeCover [description=" + description + ", img=" + img + "]";
    }

    

    

    

    
}
