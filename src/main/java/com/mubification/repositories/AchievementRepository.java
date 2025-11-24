package com.mubification.repositories;

import com.mubification.models.Achievement;
import com.mubification.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AchievementRepository {

    public List<Achievement> findAll() {
        List<Achievement> list = new ArrayList<>();

        String sql = "SELECT id, name, description, tier FROM achievements";

        try (Connection conn = Database.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Achievement a = new Achievement(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("tier")
                );
                list.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Achievement findById(int id) {
        String sql = "SELECT id, name, description, tier FROM achievements WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Achievement(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("tier")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Achievement save(Achievement a) {
        String sql = "INSERT INTO achievements(name, description, tier) VALUES (?, ?, ?) RETURNING id";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, a.getName());
            stmt.setString(2, a.getDescription());
            stmt.setInt(3, a.getTier());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                a.setId(rs.getInt("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return a;
    }

    public Achievement update(Achievement a) {
        String sql = "UPDATE achievements SET name=?, description=?, tier=? WHERE id=?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, a.getName());
            stmt.setString(2, a.getDescription());
            stmt.setInt(3, a.getTier());
            stmt.setInt(4, a.getId());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return a;
    }

    public void delete(int id) {
        String sql = "DELETE FROM achievements WHERE id=?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
