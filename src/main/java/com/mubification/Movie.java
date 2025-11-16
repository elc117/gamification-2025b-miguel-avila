package com.mubification;

public class Movie {
    private int id;
    private String name;
    // private Cast cast;
    private String genre;
    private float averageRating;
    // private int numberOfRatings; // modificar no diagrama de classes para incluir o uso disso
    private int year;
    // private List<Review> reviews;

    public Movie(String name, String genre, float averageRating, int year) {
        this.id = 0; // !!!!!! mudar
        this.name = name;
        this.genre = genre;
        this.averageRating = averageRating;
        this.year = year;
    }

    // name
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    // genre
    public String getGenre() {
        return this.name;
    }

    private void setGenre(String genre) {
        this.genre = genre;
    }

    // rating
    public float getAvgRating() {
        return this.averageRating;
    }

    private void setAvgRating(float averageRating) {
        this.averageRating = averageRating;
    }

    // rating
    public int getYear() {
        return this.year;
    }

    private void setYear(int year) {
        this.year = year;
    }

}
