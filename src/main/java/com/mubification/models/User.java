package com.mubification.models;

public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private String password;
    private int points;

    public User() {}


    public User(int id, String name, String username, String email, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.points = points;
    }


    // GETTERS E SETTERS
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    public int incrementPoints(int added) {
        this.points += added;
        return this.points;
    }

    public int decrementPoints(int dec) {
        this.points -= dec;
        return this.points;
    }
}
