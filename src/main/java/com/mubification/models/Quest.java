package com.mubification.models;

public class Quest {

    private int id;
    private String name;
    private String description;
    private String daysToComplete;
    private int points;

    public Quest() {}

    public Quest(String name, String description, String daysToComplete, int points) {
        this.name = name;
        this.description = description;
        this.daysToComplete = daysToComplete;
        this.points = points;
    }

    public Quest(int id, String name, String description, String daysToComplete, int points) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.daysToComplete = daysToComplete;
        this.points = points;
    }

    // GETTERS E SETTERS
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDaysToComplete() { return daysToComplete; }
    public void setDaysToComplete(String daysToComplete) { this.daysToComplete = daysToComplete; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }
}
