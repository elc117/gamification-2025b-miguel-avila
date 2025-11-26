package com.mubification.models;

import java.time.Instant;

public class ReviewDTO {
    private int id;
    private String user; 
    private String movie;
    private int rating;
    private String comment;
    // private Instant createdAt;

    // GETTERS E SETTERS
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }

    public String getMovie() { return movie; }
    public void setMovie(String movie) { this.movie = movie; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    // public Instant getCreatedAt() { return createdAt; }
    // public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
