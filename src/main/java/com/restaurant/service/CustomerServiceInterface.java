package com.restaurant.service;
import com.restaurant.model.User;

import java.sql.SQLException;
import java.util.Scanner;

public interface CustomerServiceInterface {

    void viewMenu() throws SQLException;

    void searchDishes(Scanner sc) throws SQLException;

    void createNewOrder(Scanner sc, User user) throws SQLException;

    void addDishesToOrder(Scanner sc, User user) throws SQLException;

    void viewCurrentOrder() throws SQLException;

    void updateOrderItemQuantity(Scanner sc, User user) throws SQLException;

    void removeOrderItem(Scanner sc, User user) throws SQLException;

    void checkout(User user) throws SQLException;

    void viewOrderHistory(User user) throws SQLException;

    void viewOrderDetail(int orderId) throws SQLException;

    void viewCurrentOrderItems() throws SQLException;

    void updateOrderTotal() throws SQLException;
}
