package com.mavs.backend.entities.home;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document(collection = "achievements")
public class Achievements {
    @Id
    private String achievementImg;

    public Achievements() {
    }

    public Achievements(String achievementImg) {
        this.achievementImg = achievementImg;
    }

    public String getAchievementImg() {
        return achievementImg;
    }

    public void setAchievementImg(String achievementImg) {
        this.achievementImg = achievementImg;
    }

    @Override
    public String toString() {
        return "Achievements [achievementimg=" + achievementImg + "]";
    }

    
    
}
