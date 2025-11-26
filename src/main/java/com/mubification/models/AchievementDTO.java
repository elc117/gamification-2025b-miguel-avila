package com.mubification.models;

public class AchievementDTO {

    private int id;
    private String name;
    private String description;
    private boolean completed;
    private int points;

    public AchievementDTO(int id, String name, String description, boolean completed, int points) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.points = points;
    }

    // GETTERS / SETTERS
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return completed; }
    public int getPoints() { return points; }
}
