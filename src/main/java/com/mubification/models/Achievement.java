package com.mubification.models;

public class Achievement {

    private int id;
    private String name;
    private String description;
    private String criteria; // json
    private int points;

    public Achievement() {}

    public Achievement(String name, String description, String criteria, int points) {
        this.name = name;
        this.description = description;
        this.criteria = criteria;
        this.points = points;
    }

    public Achievement(int id, String name, String description, String criteria, int points) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.criteria = criteria;
        this.points = points;
    }

    // GETTERS E SETTERS
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCriteria() { return criteria; }
    public void setCriteria(String criteria) { this.criteria = criteria; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }
}
