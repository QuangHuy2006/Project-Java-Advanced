package com.restaurant.service.impl;

import com.restaurant.dao.OrderDAO;
import com.restaurant.dao.OrderDetailDAO;
import com.restaurant.dao.impl.OrderImpl;
import com.restaurant.dao.impl.OrderDetailImpl;
import com.restaurant.model.Order;
import com.restaurant.model.OrderDetail;
import com.restaurant.model.Order.Status;
import com.restaurant.model.User;
import com.restaurant.service.OrderServiceInterface;

import java.sql.SQLException;
import java.util.List;

public class OrderService implements OrderServiceInterface {

    private final OrderDAO orderDAO = new OrderImpl();
    private final OrderDetailDAO orderDetailDAO = new OrderDetailImpl();

    @Override
    public boolean createOrder(Order order) throws SQLException {
        if (order == null) {
            System.out.println("Thông tin đơn hàng không hợp lệ!");
            return false;
        }
        if (order.getUserId() <= 0) {
            System.out.println("ID người dùng không hợp lệ!");
            return false;
        }
        if (order.getTableId() <= 0) {
            System.out.println("ID bàn không hợp lệ!");
            return false;
        }
        if (order.getStatus() == null) {
            order.setStatus(Status.PENDING);
        }
        return orderDAO.createOrder(order);
    }

    @Override
    public List<Order> getOrdersByStatus(Status status, User user) throws SQLException {
        return orderDAO.getOrdersByStatus(status, user);
    }

    @Override
    public Order getOrderById(int id) throws SQLException {
        if (id <= 0) {
            System.out.println("ID đơn hàng không hợp lệ!");
            return null;
        }
        return orderDAO.getOrderById(id);
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) throws SQLException {
        if (userId <= 0) {
            System.out.println("ID người dùng không hợp lệ!");
            return null;
        }
        return orderDAO.getOrdersByUserId(userId);
    }

    @Override
    public boolean updateOrderStatus(int orderId, Status status) throws SQLException {
        if (orderId <= 0) {
            System.out.println("ID đơn hàng không hợp lệ!");
            return false;
        }
        if (status == null) {
            System.out.println("Trạng thái không hợp lệ!");
            return false;
        }
        return orderDAO.updateOrderStatus(orderId, status);
    }

    @Override
    public boolean updateOrderDetailStatus(int orderId, Status status) throws SQLException {
        if (orderId <= 0) {
            System.out.println("ID đơn hàng không hợp lệ!");
            return false;
        }
        if (status == null) {
            System.out.println("Trạng thái không hợp lệ!");
            return false;
        }
        return orderDAO.updateOrderDetailStatus(orderId, status);
    }

    @Override
    public boolean updateOrderTotal(int orderId, double totalAmount) throws SQLException {
        if (orderId <= 0) {
            System.out.println("ID đơn hàng không hợp lệ!");
            return false;
        }
        return orderDAO.updateOrderTotal(orderId, totalAmount);
    }

    @Override
    public List<Order> getAllOrders() throws SQLException {
        return orderDAO.getAllOrders();
    }

    @Override
    public boolean addOrderDetail(OrderDetail detail) throws SQLException {
        if (detail == null) {
            System.out.println("Thông tin chi tiết đơn hàng không hợp lệ!");
            return false;
        }
        if (detail.getOrderId() <= 0) {
            System.out.println("ID đơn hàng không hợp lệ!");
            return false;
        }
        if (detail.getMenuItemId() <= 0) {
            System.out.println("ID món ăn không hợp lệ!");
            return false;
        }
        if (detail.getQuantity() <= 0) {
            System.out.println("Số lượng phải lớn hơn 0!");
            return false;
        }
        if (detail.getStatus() == null) {
            detail.setStatus(Status.PENDING);
        }
        return orderDetailDAO.addOrderDetail(detail);
    }

    @Override
    public List<OrderDetail> getOrderDetails(int orderId) throws SQLException {
        if (orderId <= 0) {
            System.out.println("ID đơn hàng không hợp lệ!");
            return null;
        }
        return orderDetailDAO.getOrderDetailsByOrderId(orderId);
    }

    @Override
    public boolean deleteOrderDetail(int detailId) throws SQLException {
        if (detailId <= 0) {
            System.out.println("ID chi tiết đơn hàng không hợp lệ!");
            return false;
        }
        return orderDetailDAO.deleteOrderDetail(detailId);
    }

    @Override
    public List<OrderDetail> getOrderDetailsByStatus(Status status) throws SQLException {
        if (status == null) {
            System.out.println("Trạng thái không hợp lệ!");
            return null;
        }
        return orderDetailDAO.getOrderDetailsByStatus(status);
    }

    @Override
    public OrderDetail getOrderDetailById(int id) throws SQLException {
        if (id <= 0) {
            System.out.println("ID chi tiết đơn hàng không hợp lệ!");
            return null;
        }
        return orderDetailDAO.getOrderDetailById(id);
    }
    @Override
    public List<OrderDetail> getAllOrderDetails() throws SQLException {
        return orderDetailDAO.getAllOrderDetails();
    }

    @Override
    public boolean updateOrderDetailQuantity(int detailId, int quantity) throws SQLException {
        return orderDetailDAO.updateOrderDetailQuantity(detailId, quantity);
    }
}