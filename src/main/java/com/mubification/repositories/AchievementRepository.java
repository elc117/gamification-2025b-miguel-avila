package com.mubification.repositories;

import com.mubification.models.Achievement;
import com.mubification.util.Database;

import java.sql.*;

public class AchievementRepository {

    public Achievement addAchievement(Achievement achievement) {
        String sql = "INSERT INTO achievements (name, description, criteria, points) VALUES (?,?,?,?)";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, achievement.getName());
            stmt.setString(2, achievement.getDescription());
            stmt.setString(3, achievement.getCriteria());
            stmt.setInt   (4, achievement.getPoints());
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { achievement.setId(rs.getInt("id")); }
        } catch (SQLException e) { e.printStackTrace(); }

        return achievement;
    }

}