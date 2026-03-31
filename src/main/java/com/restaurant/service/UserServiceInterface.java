package com.restaurant.service;

import com.restaurant.model.User;
import java.sql.SQLException;
import java.util.List;

public interface UserServiceInterface {
    User getUserById(int id) throws SQLException;
    User getUserByUsername(String username) throws SQLException;
    boolean updateUser(User user) throws SQLException;
    boolean disableUser(int userId) throws SQLException;
    List<User> getAllUsers() throws SQLException;
    boolean createUser(User user) throws SQLException;
    boolean updateUserRole(int userId, String role) throws SQLException;
}