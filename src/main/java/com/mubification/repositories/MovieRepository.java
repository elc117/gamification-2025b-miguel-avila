package com.mubification.repositories;

import com.mubification.models.Movie;
import com.mubification.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {

    // buscar filmes pelo titulo
    public List<Movie> searchMoviesByTitle(String title) {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies WHERE name ILIKE ? LIMIT 5";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + title + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setName(rs.getString("name"));
                movie.setGenre(rs.getString("genre"));
                movie.setDirector(rs.getString("director"));
                movie.setMainActor(rs.getString("mainactor"));
                movie.setRating(rs.getFloat("rating"));
                movie.setReleaseYear(rs.getInt("year"));
                movies.add(movie);
            }
        } catch (SQLException e) { e.printStackTrace(); }

        for (Movie m : movies) { System.out.println( "ID: " + m.getId() + " | Nome: " + m.getName() ); }
        return movies;
    }

    // adicionar um novo filme
    public Movie addMovie(Movie movie) {
        String sql = "INSERT INTO movies (name, genre, director, mainactor, rating, year) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, movie.getName());
            stmt.setString(2, movie.getGenre());
            stmt.setString(3, movie.getDirector());
            stmt.setString(4, movie.getMainActor());
            stmt.setFloat(5, movie.getRating());
            stmt.setInt(6, movie.getReleaseYear());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { movie.setId(rs.getInt("id")); }
        } catch (SQLException e) { e.printStackTrace(); }

        return movie;
    }
}
