package com.mubification.models;

public class Achievement {

    private int id;
    private String name;
    private String description;
    private int tier;

    public Achievement() {}

    public Achievement(String name, String description) {
        this.name = name;
        this.description = description;
        this.tier = 0;
    }

    public Achievement(int id, String name, String description, int tier) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tier = tier;
    }

    // GETTERS E SETTERS
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getTier() { return tier; }
    public void setTier(int tier) { this.tier = tier; }
}
