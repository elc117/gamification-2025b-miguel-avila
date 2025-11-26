package com.mubification.controllers;

import com.mubification.models.AchievementDTO;
import com.mubification.services.AchievementService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List;


public class AchievementController {
    
    private AchievementService achievementService;

    public AchievementController() {
        this.achievementService = new AchievementService();
    }

    public void getUserAchievements(Context ctx) {
        int userId = Integer.parseInt(ctx.pathParam("id"));
        List<AchievementDTO> userAchievements = achievementService.getUserAchievements(userId);
        ctx.json(userAchievements);
    }

    public void registerRoutes(Javalin app) {
        app.get("/api/achievements/user/{id}", this::getUserAchievements);
    }

}
