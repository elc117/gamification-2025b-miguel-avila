package com.mubification.services;

import com.mubification.models.Movie;
import com.mubification.repositories.MovieRepository;

import java.util.List;

public class MovieService {

    private MovieRepository movieRepository;
    // private AchievementService achievementService = new AchievementService();

    public MovieService() {
        this.movieRepository = new MovieRepository();
    }

    // busca filmes pelo título
    public List<Movie> searchMoviesByTitle(String title) {
        return movieRepository.searchMoviesByTitle(title);
    }

    // adiciona um novo filme
    public Movie addMovie(Movie movie) {
        return movieRepository.addMovie(movie);
    }
}
