package com.mubification.repositories;

import com.mubification.models.Achievement;
import com.mubification.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AchievementRepository {

    public List<Achievement> getAll() {

        String sql = "SELECT id, name, description, criteria, points FROM achievements";
        List<Achievement> list = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Achievement a = new Achievement(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("criteria"),
                        rs.getInt("points")
                );
                list.add(a);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar achievements", e);
        }

        return list;
    }

    public List<Integer> getCompletedAchievements(int userId) {

        String sql = "SELECT achievement_id FROM user_achievements WHERE user_id = ?";
        List<Integer> completed = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    completed.add(rs.getInt("achievement_id"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar conquistas completadas", e);
        }

        return completed;
    }

    public void markCompleted(int userId, int achievementId) {

        String sql = """
            INSERT INTO user_achievements (user_id, achievement_id, unlocked_at)
            VALUES (?, ?, NOW())
        """;

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, achievementId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao marcar achievement como completo", e);
        }
    }
}
