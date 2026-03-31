package com.restaurant.dao.impl;

import com.restaurant.dao.TableDAO;
import com.restaurant.model.Table;
import com.restaurant.model.Table.Status;
import com.restaurant.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TableImpl implements TableDAO {

    @Override
    public void addTable(int tableNumber, int capacity) throws SQLException {
        String sql = "INSERT INTO restaurant_tables (table_number, capacity, status) VALUES (?, ?, 'AVAILABLE')";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, tableNumber);
            ps.setInt(2, capacity);
            ps.executeUpdate();
        }
    }

    @Override
    public void checkTableNumberExists(int tableNumber) throws SQLException {
        String sql = "SELECT table_number FROM restaurant_tables WHERE table_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, tableNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    throw new SQLException("Bàn số " + tableNumber + " đã tồn tại!");
                }
            }
        }
    }

    @Override
    public Table getExistTable(int tableNumber) throws SQLException {
        String sql = "SELECT * FROM restaurant_tables WHERE table_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, tableNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Table(
                            rs.getInt("id"),
                            rs.getInt("table_number"),
                            rs.getInt("capacity"),
                            Status.valueOf(rs.getString("status").trim().toUpperCase())
                    );
                } else {
                    throw new SQLException("Không tìm thấy bàn số " + tableNumber);
                }
            }
        }
    }

    // ================= UPDATE TABLE - DÙNG int capacity =================
    @Override
    public void updateTable(int id, int capacity, Status status) throws SQLException {

            String sql = "UPDATE restaurant_tables SET status = ?, capacity = ? WHERE id = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, status.name());
                ps.setInt(2, capacity);
                ps.setInt(3, id);
                int rows = ps.executeUpdate();
                if (rows == 0) {
                    throw new SQLException("Không tìm thấy bàn để cập nhật!");
                }
            }
    }

    @Override
    public void deleteTable(int tableNumber) throws SQLException {
        String sql = "DELETE FROM restaurant_tables WHERE table_number = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, tableNumber);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Không tìm thấy bàn số " + tableNumber + " để xóa!");
            }
        }
    }

    @Override
    public List<Table> getAllTables() throws SQLException {
        List<Table> list = new ArrayList<>();
        String sql = "SELECT * FROM restaurant_tables ORDER BY table_number";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Table t = new Table(
                        rs.getInt("id"),
                        rs.getInt("table_number"),
                        rs.getInt("capacity"),
                        Status.valueOf(rs.getString("status").trim().toUpperCase())
                );
                list.add(t);
            }
        }
        return list;
    }
    @Override
    public List<Table> getAvailableTables() throws SQLException {
        List<Table> list = new ArrayList<>();
        String sql = "SELECT * FROM restaurant_tables WHERE status = 'AVAILABLE'";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Table t = new Table(
                        rs.getInt("id"),
                        rs.getInt("table_number"),
                        rs.getInt("capacity"),
                        Status.valueOf(rs.getString("status").trim().toUpperCase())
                );
                list.add(t);
            }
        }
        return list;
    }
}