package com.mubification.repositories;

import com.mubification.models.Review;
import com.mubification.util.Database;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {

    private final DataSource ds = Database.getDataSource();

    // buscar todas as reviews
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
        } catch (SQLException e) { throw new RuntimeException(e); }

        return reviews;
    }

    // buscar uma review por id
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

        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    public Review addReview(Review review) {
        String sql = "INSERT INTO reviews (userid, movieid, rating, comment) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, review.getUserId());
            stmt.setInt(2, review.getMovieId());
            stmt.setDouble(3, review.getRating());
            stmt.setString(4, review.getComment());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar a review no banco", e);
        }
        return review;
    }
}
