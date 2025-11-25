package com.mubification.controllers;

import com.mubification.models.Movie;
import com.mubification.services.MovieService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class MovieController {

    private MovieService movieService;

    public MovieController() {
        this.movieService = new MovieService();
    }

    // buscar filmes por título
    public void searchMovies(Context ctx) {
        String title = ctx.queryParam("title");
        List<Movie> movies = movieService.searchMoviesByTitle(title);
        ctx.json(movies);
    }

    // adicionar um novo filme
    public void addMovie(Context ctx) {
        Movie newMovie = ctx.bodyAsClass(Movie.class);
        Movie addedMovie = movieService.addMovie(newMovie);
        ctx.status(201).json(addedMovie);
    }

    // registrar os endpoints
    public void registerRoutes(Javalin app) {
        app.get("/api/movies/search", this::searchMovies);
        app.post("/api/movies", this::addMovie);
    }
}
