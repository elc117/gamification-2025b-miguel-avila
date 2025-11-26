package com.mubification.repositories;

import com.mubification.models.Review;
import com.mubification.models.ReviewDTO;
import com.mubification.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {

    public Review addReview(Review review) {
        String sql = "INSERT INTO reviews (userid, movieid, rating, comment) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1,    review.getUserId());
            stmt.setInt(2,    review.getMovieId());
            stmt.setDouble(3, review.getRating());
            stmt.setString(4, review.getComment());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar a review no banco", e);
        }
        return review;
    }

    // carrega as n primeiras reviews
    public List<ReviewDTO> getTopReviews(int limit) {
        String sql = """
            SELECT r.id, r.rating, r.comment, u.username AS user, m.name AS movie
            FROM reviews r
            LEFT JOIN users u ON r.userid = u.id
            LEFT JOIN movies m ON r.movieid = m.id
            ORDER BY r.id DESC
            LIMIT ?
        """;

        List<ReviewDTO> topReviews = new ArrayList<>();

        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, limit);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ReviewDTO review = new ReviewDTO();
                    review.setId(rs.getInt("id"));
                    review.setUser(rs.getString("user"));
                    review.setMovie(rs.getString("movie"));
                    review.setRating(rs.getInt("rating"));
                    review.setComment(rs.getString("comment"));

                    topReviews.add(review);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar as reviews do banco", e);
        }

        return topReviews;
    }



}
