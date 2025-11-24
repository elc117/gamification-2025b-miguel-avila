package com.mubification.repositories;

import com.mubification.models.Quest;
import com.mubification.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestRepository {

    public List<Quest> findAll() {
        List<Quest> list = new ArrayList<>();

        String sql = "SELECT id, name, description, days_to_complete, points FROM quests";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Quest q = new Quest(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("days_to_complete"),
                        rs.getInt("points")
                );
                list.add(q);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Quest findById(int id) {
        String sql = "SELECT id, name, description, days_to_complete, points FROM quests WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Quest(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("days_to_complete"),
                        rs.getInt("points")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Quest save(Quest q) {
        String sql = "INSERT INTO quests(name, description, days_to_complete, points) VALUES (?, ?, ?, ?) RETURNING id";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, q.getName());
            stmt.setString(2, q.getDescription());
            stmt.setString(3, q.getDaysToComplete());
            stmt.setInt(4, q.getPoints());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                q.setId(rs.getInt("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return q;
    }

    public Quest update(Quest q) {
        String sql = "UPDATE quests SET name=?, description=?, days_to_complete=?, points=? WHERE id=?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, q.getName());
            stmt.setString(2, q.getDescription());
            stmt.setString(3, q.getDaysToComplete());
            stmt.setInt(4, q.getPoints());
            stmt.setInt(5, q.getId());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return q;
    }

    public void delete(int id) {
        String sql = "DELETE FROM quests WHERE id=?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
