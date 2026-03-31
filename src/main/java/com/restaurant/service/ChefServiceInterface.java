package com.restaurant.service;

import com.restaurant.model.Order;
import com.restaurant.model.Order.Status;
import com.restaurant.model.OrderDetail;
import java.sql.SQLException;
import java.util.List;

public interface ChefServiceInterface {
    List<OrderDetail> getPendingOrders() throws SQLException;
    List<OrderDetail> getOrdersByStatus(Status status) throws SQLException;
    boolean updateOrderStatus(int orderDetailId, Status status) throws SQLException;
}