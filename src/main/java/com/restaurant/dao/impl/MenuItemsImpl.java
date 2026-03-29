package com.restaurant.dao.impl;

import com.restaurant.dao.MenuItemDAO;
import com.restaurant.model.MenuItem;
import com.restaurant.utils.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuItemsImpl implements MenuItemDAO {
    public enum Status {
        AVAILABLE,
        UNAVAILABLE
    }
    @Override
    public void addDishes(String dishesName, String description, BigDecimal price, String category){
        String sql = "INSERT INTO menu_items(name, description, price, category, status) values (?,?,?,?, 'AVAILABLE')";

        try (Connection conn = DatabaseConnection.getConnection();

             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dishesName);
            stmt.setString(2, description);
            stmt.setString(3, String.valueOf(price));
            stmt.setString(4, category);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDishes(int id, String dishesName, String description, BigDecimal price, String category, Status status){
        String sql = "UPDATE menu_items set name = ?, description = ?, price = ?, category = ?, status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dishesName);
            stmt.setString(2, description);
            stmt.setBigDecimal(3, price);
            stmt.setString(4, category);
            stmt.setString(5, status.name());
            stmt.setInt(6, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDishes(int id){
        String sql = "DELETE FROM menu_items WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                System.out.println("No dish found to delete!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MenuItem getDishesById(int id) {
        String sql = "SELECT * FROM menu_items WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                MenuItem item = new MenuItem();
                item.setCategory(rs.getString("category"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getBigDecimal("price"));
                item.setDescription(rs.getString("description"));
                item.setStatus(Status.valueOf(rs.getString("status")));
                return item;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MenuItem> showAllDishes(){
        String sql = "SELECT * FROM menu_items";
        List<MenuItem> list = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MenuItem item = new MenuItem();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setPrice(rs.getBigDecimal("price"));
                item.setCategory(rs.getString("category"));

                String statusStr = rs.getString("status");
                if (statusStr != null) {
                    item.setStatus(Status.valueOf(statusStr));
                }

                list.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
