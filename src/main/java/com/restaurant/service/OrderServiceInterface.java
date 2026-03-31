package com.restaurant.service;

import com.restaurant.model.Order;
import com.restaurant.model.OrderDetail;
import java.sql.SQLException;
import java.util.List;
import com.restaurant.model.Order.Status;
import com.restaurant.model.User;

public interface OrderServiceInterface {
    // Order methods
    boolean createOrder(Order order) throws SQLException;
    Order getOrderById(int id) throws SQLException;
    List<Order> getOrdersByStatus(Status status, User user) throws SQLException;
    List<Order> getOrdersByUserId(int userId) throws SQLException;
    boolean updateOrderStatus(int orderId, Status status) throws SQLException;
    boolean updateOrderTotal(int orderId, double totalAmount) throws SQLException;
    List<Order> getAllOrders() throws SQLException;

    // OrderDetail methods
    boolean addOrderDetail(OrderDetail detail) throws SQLException;
    boolean updateOrderDetailQuantity(int detailId, int quantity) throws SQLException;
    List<OrderDetail> getOrderDetails(int orderId) throws SQLException;
    boolean deleteOrderDetail(int detailId) throws SQLException;
    boolean updateOrderDetailStatus(int orderId, Status status) throws SQLException;
    List<OrderDetail> getOrderDetailsByStatus(Status status) throws SQLException;
    OrderDetail getOrderDetailById(int id) throws SQLException;
    List<OrderDetail> getAllOrderDetails() throws SQLException;
}