package com.mavs.backend.entities.home;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document(collection = "homecover")
public class HomeCover {
    
    @Id
    private String description;

    private String video;

    public HomeCover() {
    }

    public HomeCover(String description, String video) {
        this.description = description;
        this.video = video;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return "HomeCover [description=" + description + ", video=" + video + "]";
    }

    

    

    

    
}
