package com.restaurant.dao.impl;

import com.restaurant.dao.OrderDAO;
import com.restaurant.model.Order;
import com.restaurant.model.Order.Status;
import com.restaurant.model.User;
import com.restaurant.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderImpl implements OrderDAO {

    @Override
    public boolean createOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders(user_id,table_id, total_amount, status) values(?,?,?,?)";

        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement smtm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            smtm.setInt(1, order.getUserId());
            smtm.setInt(2, order.getTableId());
            smtm.setDouble(3, order.getTotalAmount());
            smtm.setString(4, order.getStatus().name());
            int affectedRow = smtm.executeUpdate();
            if (affectedRow > 0) {
                ResultSet rs = smtm.getGeneratedKeys();
                if (rs.next()) {
                    order.setId(rs.getInt(1));
                }
                return true;
            }
            return false;
        }

    }

    @Override
    public Order getOrderById(int id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setTableId(rs.getInt("table_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setStatus(Status.valueOf(rs.getString("status").trim().toUpperCase()));
                order.setCreatedAt(rs.getTimestamp("created_at"));
                order.setUpdatedAt(rs.getTimestamp("updated_at"));
                return order;
            }
        }
        return null;
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_id = ? ORDER BY created_at DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setTableId(rs.getInt("table_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setStatus(Status.valueOf(rs.getString("status").trim().toUpperCase()));
                order.setCreatedAt(rs.getTimestamp("created_at"));
                order.setUpdatedAt(rs.getTimestamp("updated_at"));
                orders.add(order);
            }
        }
        return orders;
    }

    @Override
    public boolean updateOrderStatus(int orderId, Status status) throws SQLException {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status.name());
            stmt.setInt(2, orderId);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateOrderDetailStatus(int orderId, Status status) throws SQLException {
        String sql = "UPDATE order_details SET status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status.name());
            stmt.setInt(2, orderId);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateOrderTotal(int orderId, double totalAmount) throws SQLException {
        String sql = "UPDATE orders SET total_amount = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, totalAmount);
            stmt.setInt(2, orderId);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders ORDER BY created_at DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setTableId(rs.getInt("table_id"));
                order.setUserId(rs.getInt("user_id"));
                order.setTotalAmount(rs.getDouble("total_amount"));
                order.setStatus(Status.valueOf(rs.getString("status").trim().toUpperCase()));
                order.setCreatedAt(rs.getTimestamp("created_at"));
                order.setUpdatedAt(rs.getTimestamp("updated_at"));
                orders.add(order);
            }
        }
        return orders;
    }

    @Override
    public List<Order> getOrdersByStatus(Status status, User user) throws SQLException {
        List<Order> orders = new ArrayList<>();
        if(user == null) {
            String sql = "SELECT * FROM orders WHERE status = ? ORDER BY id desc";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, status.name());

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Order order = new Order();
                        order.setId(rs.getInt("id"));
                        order.setTableId(rs.getInt("table_id"));
                        order.setUserId(rs.getInt("user_id"));
                        order.setTotalAmount(rs.getDouble("total_amount"));
                        order.setStatus(Status.valueOf(rs.getString("status").trim().toUpperCase()));
                        order.setCreatedAt(rs.getTimestamp("created_at"));
                        order.setUpdatedAt(rs.getTimestamp("updated_at"));
                        orders.add(order);
                    }
                }
            }
            return orders;
        }else{
            String sql = "SELECT * FROM orders WHERE status = ? AND user_id = ? ORDER BY id desc";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, status.name());
                stmt.setInt(2,user.getId());

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Order order = new Order();
                        order.setId(rs.getInt("id"));
                        order.setTableId(rs.getInt("table_id"));
                        order.setUserId(rs.getInt("user_id"));
                        order.setTotalAmount(rs.getDouble("total_amount"));
                        order.setStatus(Status.valueOf(rs.getString("status").trim().toUpperCase()));
                        order.setCreatedAt(rs.getTimestamp("created_at"));
                        order.setUpdatedAt(rs.getTimestamp("updated_at"));
                        orders.add(order);
                    }
                }
            }
            return orders;
        }
    }
}