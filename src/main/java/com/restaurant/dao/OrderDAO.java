package com.restaurant.dao;

import com.restaurant.model.Order;
import com.restaurant.model.Order.Status;
import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {
    boolean createOrder(Order order) throws SQLException;
    Order getOrderById(int id) throws SQLException;
    List<Order> getOrdersByStatus(Status status) throws SQLException;
    List<Order> getOrdersByUserId(int userId) throws SQLException;
    List<Order> getOrdersByTableId(int tableId) throws SQLException;
    boolean updateOrderStatus(int orderId, Status status) throws SQLException;
    boolean updateOrderTotal(int orderId, double totalAmount) throws SQLException;
    List<Order> getAllOrders() throws SQLException;
}