package com.mubification.repositories;

import com.mubification.models.Movie;
import com.mubification.util.Database;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {

    private final DataSource ds = Database.getDataSource();

    public List<Movie> findAll() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT id, name, director, main_actor, genre, rating, release_year FROM movies";

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Movie m = new Movie();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setDirector(rs.getString("director"));
                m.setMainActor(rs.getString("main_actor"));
                m.setGenre(rs.getString("genre"));
                m.setRating(rs.getFloat("rating"));
                m.setReleaseYear(rs.getInt("release_year"));
                movies.add(m);
            }

        } catch (SQLException e) {
            System.out.println("HAHAHAHAHAHAHAHAHAHA");
            throw new RuntimeException(e);
        }

        return movies;
    }

    public Movie findById(int id) {
        String sql = """
            SELECT id, name, director, main_actor, genre, rating, release_year
            FROM movies WHERE id = ?
        """;

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Movie m = new Movie();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setDirector(rs.getString("director"));
                m.setMainActor(rs.getString("main_actor"));
                m.setGenre(rs.getString("genre"));
                m.setRating(rs.getFloat("rating"));
                m.setReleaseYear(rs.getInt("release_year"));
                return m;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public Movie save(Movie m) {
        String sql = """
            INSERT INTO movies (name, director, main_actor, genre, rating, release_year)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, m.getName());
            stmt.setString(2, m.getDirector());
            stmt.setString(3, m.getMainActor());
            stmt.setString(4, m.getGenre());
            stmt.setFloat(5, m.getRating());
            stmt.setInt(6, m.getReleaseYear());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) m.setId(keys.getInt(1));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return m;
    }

    public Movie update(Movie m) {
        String sql = """
            UPDATE movies
            SET name = ?, director = ?, main_actor = ?, genre = ?, rating = ?, release_year = ?
            WHERE id = ?
        """;

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, m.getName());
            stmt.setString(2, m.getDirector());
            stmt.setString(3, m.getMainActor());
            stmt.setString(4, m.getGenre());
            stmt.setFloat(5, m.getRating());
            stmt.setInt(6, m.getReleaseYear());
            stmt.setInt(7, m.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return m;
    }

    public void delete(int id) {
        String sql = "DELETE FROM movies WHERE id = ?";

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
