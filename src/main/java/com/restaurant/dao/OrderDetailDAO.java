package com.restaurant.dao;

import com.restaurant.model.OrderDetail;
import com.restaurant.model.Order.Status;
import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO {
    boolean addOrderDetail(OrderDetail detail) throws SQLException;
    List<OrderDetail> getOrderDetailsByOrderId(int orderId) throws SQLException;
    boolean updateOrderDetailStatus(int detailId, Status status) throws SQLException;
    boolean updateOrderDetailQuantity(int detailId, int quantity) throws SQLException;
    boolean deleteOrderDetail(int detailId) throws SQLException;
    List<OrderDetail> getOrderDetailsByStatus(Status status) throws SQLException;
    OrderDetail getOrderDetailById(int id) throws SQLException;
    List<OrderDetail> getAllOrderDetails() throws SQLException;
}