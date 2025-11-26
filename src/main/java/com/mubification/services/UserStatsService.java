package com.mubification.services;

import com.mubification.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserStatsService {

    public int getStat(int userId, String key) {
        return switch (key) {
            case "reviews_count" -> getReviewCount(userId);
            case "movies_added" -> getMoviesAdded(userId);
            case "likes_received" -> getLikesReceived(userId);
            default -> 0;
        };
    }

    private int getReviewCount(int userId) {
        String sql = "SELECT COUNT(*) FROM reviews WHERE userid = ?";
        return simpleCount(sql, userId);
    }

    private int getMoviesAdded(int userId) {
        String sql = "SELECT COUNT(*) FROM user_movies WHERE userid = ?";
        return simpleCount(sql, userId);
    }

    private int getLikesReceived(int userId) {
        String sql = "SELECT SUM(likes) FROM reviews WHERE userid = ?";
        return simpleSum(sql, userId);
    }

    private int simpleCount(String sql, int userId) {
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int simpleSum(String sql, int userId) {
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
