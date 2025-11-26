package com.mubification.models;

import java.time.LocalDateTime;

public class UserAchievement {

    private int id;
    private int userId;
    private int achievementId;
    private LocalDateTime completedAt;

    public UserAchievement() {}

    public UserAchievement(int userId, int achievementId, LocalDateTime completedAt) {
        this.userId = userId;
        this.achievementId = achievementId;
        this.completedAt = completedAt;
    }

    // GETTERS / SETTERS
    


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAchievementId() {
        return this.achievementId;
    }

    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    public LocalDateTime getCompletedAt() {
        return this.completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

}