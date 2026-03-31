package com.restaurant.dao.impl;

import com.restaurant.dao.OrderDetailDAO;
import com.restaurant.model.OrderDetail;
import com.restaurant.model.Order.Status;
import com.restaurant.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailImpl implements OrderDetailDAO {

    @Override
    public boolean addOrderDetail(OrderDetail detail) throws SQLException {
        String sql = "INSERT INTO order_details (order_id, menu_item_id, quantity, price, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, detail.getOrderId());
            stmt.setInt(2, detail.getMenuItemId());
            stmt.setInt(3, detail.getQuantity());
            stmt.setDouble(4, detail.getPrice());
            stmt.setString(5, detail.getStatus().name());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    detail.setId(rs.getInt(1));
                }
                return true;
            }
            return false;
        }
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) throws SQLException {
        List<OrderDetail> details = new ArrayList<>();
        String sql = "SELECT od.*, mi.name as menu_item_name FROM order_details od " +
                "JOIN menu_items mi ON od.menu_item_id = mi.id " +
                "WHERE od.order_id = ? ORDER BY od.id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrderDetail detail = new OrderDetail();
                detail.setId(rs.getInt("id"));
                detail.setOrderId(rs.getInt("order_id"));
                detail.setMenuItemId(rs.getInt("menu_item_id"));
                detail.setQuantity(rs.getInt("quantity"));
                detail.setPrice(rs.getDouble("price"));
                detail.setStatus(Status.valueOf(rs.getString("status")));
                detail.setMenuItemName(rs.getString("menu_item_name"));
                details.add(detail);
            }
        }
        return details;
    }

    @Override
    public boolean updateOrderDetailStatus(int detailId, Status status) throws SQLException {
        String sql = "UPDATE order_details SET status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status.name());
            stmt.setInt(2, detailId);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateOrderDetailQuantity(int detailId, int quantity) throws SQLException {
        String sql = "UPDATE order_details SET quantity = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quantity);
            stmt.setInt(2, detailId);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteOrderDetail(int detailId) throws SQLException {
        String sql = "DELETE FROM order_details WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, detailId);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public List<OrderDetail> getOrderDetailsByStatus(Status status) throws SQLException {
        List<OrderDetail> details = new ArrayList<>();
        String sql = "SELECT od.*, mi.name as menu_item_name FROM order_details od " +
                "JOIN menu_items mi ON od.menu_item_id = mi.id " +
                "WHERE od.status = ? ORDER BY od.created_at";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status.name());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrderDetail detail = new OrderDetail();
                detail.setId(rs.getInt("id"));
                detail.setOrderId(rs.getInt("order_id"));
                detail.setMenuItemId(rs.getInt("menu_item_id"));
                detail.setQuantity(rs.getInt("quantity"));
                detail.setPrice(rs.getDouble("price"));
                detail.setStatus(Status.valueOf(rs.getString("status")));
                detail.setMenuItemName(rs.getString("menu_item_name"));
                details.add(detail);
            }
        }
        return details;
    }

    @Override
    public OrderDetail getOrderDetailById(int id) throws SQLException {
        String sql = "SELECT od.*, mi.name as menu_item_name FROM order_details od " +
                "JOIN menu_items mi ON od.menu_item_id = mi.id " +
                "WHERE od.id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                OrderDetail detail = new OrderDetail();
                detail.setId(rs.getInt("id"));
                detail.setOrderId(rs.getInt("order_id"));
                detail.setMenuItemId(rs.getInt("menu_item_id"));
                detail.setQuantity(rs.getInt("quantity"));
                detail.setPrice(rs.getDouble("price"));
                detail.setStatus(Status.valueOf(rs.getString("status")));
                detail.setMenuItemName(rs.getString("menu_item_name"));
                detail.setCreatedAt(rs.getTimestamp("created_at"));
                detail.setUpdatedAt(rs.getTimestamp("updated_at"));
                return detail;
            }
        }
        return null;
    }

    @Override
    public List<OrderDetail> getAllOrderDetails() throws SQLException {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT od.*, mi.name as menu_item_name FROM order_details od " +
                "JOIN menu_items mi ON od.menu_item_id = mi.id";


        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                OrderDetail detail = new OrderDetail();
                detail.setId(rs.getInt("id"));
                detail.setOrderId(rs.getInt("order_id"));
                detail.setMenuItemId(rs.getInt("menu_item_id"));
                detail.setQuantity(rs.getInt("quantity"));
                detail.setPrice(rs.getDouble("price"));
                detail.setStatus(Status.valueOf(rs.getString("status")));
                detail.setCreatedAt(rs.getTimestamp("created_at"));
                detail.setMenuItemName(rs.getString("menu_item_name"));
                detail.setUpdatedAt(rs.getTimestamp("updated_at"));
                list.add(detail);
            }
            return list;
        }

    }
}