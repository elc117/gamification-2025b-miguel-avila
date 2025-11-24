package com.mubification.models;

import javax.persistence.*;

@Entity
@Table(name = "achievements")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int    id;

    private String name;
    private String description;
    private int    tier;

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

    public int getTier() {
        return this.tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public Achievement(String name, String description) {
        this.name        = name;
        this.description = description;
        this.tier        = 0;
    }

    public Achievement() {}

    // aumenta o nível de uma conquista
    // retorna o nível do novo tier
    private int upgradeTier() {

        if(this.tier < 3) {
            this.tier = this.tier+1;
            System.out.println("conquista subiu de nível!");
        } 
        if(this.tier == 3) System.out.println("conquista no nivel máximo!");
        
        return this.tier;
    }
}
