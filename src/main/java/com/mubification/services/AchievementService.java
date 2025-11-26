package com.mubification.services;

import com.mubification.models.Achievement;
import com.mubification.repositories.AchievementRepository;

public class AchievementService {
    
    private AchievementRepository achievementRepository;

    public AchievementService() {
        this.achievementRepository = new AchievementRepository();
    }

    // adiciona um novo achievement no banco
    public Achievement addAchievement(Achievement achievement) {
        return achievementRepository.addAchievement(achievement);
    }
}
