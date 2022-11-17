package com.mavs.backend.daos.home;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mavs.backend.entities.home.Achievements;


public interface AchievementsDao extends MongoRepository<Achievements,String>{
    public Achievements findAchievementsByAchievementImg(String achievementImg);
}
