package com.mubification.repositories;

import com.mubification.models.Store;
import com.mubification.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StoreRepository {

    public List<Store> getAllItems() {
        String sql = "SELECT * FROM store_items ORDER BY id ASC";

        List<Store> items = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Store s = new Store();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setDescription(rs.getString("description"));
                s.setPrice(rs.getInt("price"));
                s.setImage(rs.getString("image"));
                s.setCreated(rs.getString("created_at"));
                items.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar itens da loja", e);
        }

        return items;
    }

    public Store getItemById(int id) {
        String sql = "SELECT * FROM store_items WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Store s = new Store();
                    s.setId(rs.getInt("id"));
                    s.setName(rs.getString("name"));
                    s.setDescription(rs.getString("description"));
                    s.setPrice(rs.getInt("price"));
                    s.setImage(rs.getString("image"));
                    s.setCreated(rs.getString("created"));
                    return s;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar item da loja", e);
        }

        return null;
    }

    public void insertPurchase(int userId, int itemId) {
        String sql = "INSERT INTO user_items (user_id, item_id) VALUES (?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, itemId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao registrar compra", e);
        }
    }
}
