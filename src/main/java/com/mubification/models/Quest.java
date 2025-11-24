package com.mubification.models;

import javax.persistence.*;

@Entity
@Table(name = "quests")
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int    id;
    
    private String name;
    private String description;
    private String daysToComplete;
    private int    points;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDaysToComplete() {
        return this.daysToComplete;
    }

    public void setDaysToComplete(String daysToComplete) {
        this.daysToComplete = daysToComplete;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }


    public Quest() {}


    public Quest(String name, String description, String days, int points) {
        this.name           = name;
        this.description    = description;
        this.daysToComplete = days;
        this.points         = points;
    }

}
