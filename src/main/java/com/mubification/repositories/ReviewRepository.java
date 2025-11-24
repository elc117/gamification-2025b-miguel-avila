package com.mubification.repositories;

import com.mubification.models.Review;
import com.mubification.util.Database;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {

    private final DataSource ds = Database.getDataSource();

    public List<Review> findAll() {
        List<Review> reviews = new ArrayList<>();

        String sql = "SELECT id, userid, movieid, rating, comment FROM reviews";

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Review r = new Review();
                r.setId(rs.getLong("id"));
                r.setUserId(rs.getInt("userid"));
                r.setMovieId(rs.getInt("movieid"));
                r.setRating(rs.getDouble("rating"));
                r.setComment(rs.getString("comment"));
                reviews.add(r);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return reviews;
    }

    public Review findById(long id) {
        String sql = "SELECT id, userid, movieid, rating, comment FROM reviews WHERE id = ?";

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Review r = new Review();
                r.setId(rs.getLong("id"));
                r.setUserId(rs.getInt("userid"));
                r.setMovieId(rs.getInt("movieid"));
                r.setRating(rs.getDouble("rating"));
                r.setComment(rs.getString("comment"));
                return r;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Review save(Review r) {
        String sql = """
            INSERT INTO reviews (userid, movieid, rating, comment)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, r.getUserId());
            stmt.setInt(2, r.getMovieId());
            stmt.setDouble(3, r.getRating());
            stmt.setString(4, r.getComment());
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) r.setId(keys.getLong(1));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return r;
    }

    public void delete(long id) {
        String sql = "DELETE FROM reviews WHERE id = ?";

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
