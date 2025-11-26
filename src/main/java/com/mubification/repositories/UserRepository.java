package com.mubification.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mubification.models.User;
import com.mubification.util.Database;

public class UserRepository {

    public User addUser(User user) {
        String sql = "INSERT INTO users (name, username, email, password, points) VALUES (?,?,?,?,?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setInt   (5, 0);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar a user no banco", e);
        }
        return user;
    }

    public Boolean userExists(User user) {
        String sql = "SELECT * FROM users WHERE username = ? OR email = ?";
        
        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) { return true; }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao verificar a existência do usuário no banco", e);
        }
        
        return false;
    }

    public User findByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar usuário no banco", e);
        }
        return null;
    }

    public User getUserInfo(int userId) {
        String sql = """
            SELECT id, name, username, points
            FROM users
            WHERE id = ?
        """;

        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setUsername(rs.getString("username"));
                u.setPoints(rs.getInt("points"));
                return u;
            }
            return null;
        } catch (SQLException e) { throw new RuntimeException("Erro ao carregar informações do usuário", e); }
    }

    public int getPoints(int userId) {
        String sql = "SELECT points FROM users WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next())
                    return rs.getInt("points");
            }

        } catch (SQLException e) { e.printStackTrace(); }

        return 0;
    }

    public void updatePoints(int userId, int newPoints) {
        String sql = "UPDATE users SET points = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newPoints);
            stmt.setInt(2, userId);
            stmt.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }
    }

}
