package com.restaurant.service.impl;

import com.restaurant.dao.OrderDetailDAO;
import com.restaurant.dao.impl.OrderDetailImpl;
import com.restaurant.model.Order.Status;
import com.restaurant.model.OrderDetail;
import com.restaurant.service.ChefServiceInterface;

import java.sql.SQLException;
import java.util.List;

public class ChefService implements ChefServiceInterface {

    private final OrderDetailDAO orderDetailDAO = new OrderDetailImpl();

    @Override
    public List<OrderDetail> getPendingOrders() throws SQLException {
        return orderDetailDAO.getOrderDetailsByStatus(Status.PENDING);
    }

    @Override
    public List<OrderDetail> getOrdersByStatus(Status status) throws SQLException {
        return orderDetailDAO.getOrderDetailsByStatus(status);
    }

    @Override
    public boolean updateOrderStatus(int orderDetailId, Status status) throws SQLException {
        return orderDetailDAO.updateOrderDetailStatus(orderDetailId, status);
    }
}