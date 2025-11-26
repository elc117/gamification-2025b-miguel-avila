package com.mubification.controllers;

import com.mubification.models.Achievement;
import com.mubification.services.AchievementService;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class AchievementController {
    
    private AchievementService achievementService;

    public AchievementController() {
        this.achievementService = new AchievementService();
    }

    public void addAchievement(Context ctx) {
        Achievement newAchievement   = ctx.bodyAsClass(Achievement.class);
        Achievement addedAchievement = achievementService.addAchievement(newAchievement);
        ctx.status(201).json(addedAchievement);
    }

    // registrar os endpoints
    public void registerRoutes(Javalin app) {
        app.post("/api/achievements", this::addAchievement);
    }
}
