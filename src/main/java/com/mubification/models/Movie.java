package com.mubification.models;

public class Movie {

    private int id;
    private String name;
    private String director;
    private String mainActor;
    private String genre;
    private float rating;
    private int releaseYear;

    public Movie() {}

    public Movie(String name, String genre, String director, String actor, float rating, int year) {
        this.name        = name;
        this.genre       = genre;
        this.director    = director;
        this.mainActor   = actor;
        this.rating      = rating;
        this.releaseYear = year;
    }

    // GETTERS E SETTERS
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public String getMainActor() { return mainActor; }
    public void setMainActor(String mainActor) { this.mainActor = mainActor; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }

    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }
}
