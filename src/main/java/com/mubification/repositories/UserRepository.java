package com.mubification.repositories;

import com.mubification.models.User;
import com.mubification.util.Database;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final DataSource ds = Database.getDataSource();

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, name, username, email, password, points FROM users";

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setPoints(rs.getInt("points"));
                users.add(u);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public User findById(int id) {
        String sql = "SELECT id, name, username, email, password, points FROM users WHERE id = ?";

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setPoints(rs.getInt("points"));
                return u;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public User save(User u) {
        String sql = """
            INSERT INTO users (name, username, email, password, points)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, u.getName());
            stmt.setString(2, u.getUsername());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPassword());
            stmt.setInt(5, u.getPoints());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) u.setId(keys.getInt(1));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return u;
    }

    public User update(User u) {
        String sql = """
            UPDATE users SET name = ?, username = ?, email = ?, password = ?, points = ?
            WHERE id = ?
        """;

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getName());
            stmt.setString(2, u.getUsername());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPassword());
            stmt.setInt(5, u.getPoints());
            stmt.setInt(6, u.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return u;
    }

    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = ds.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
