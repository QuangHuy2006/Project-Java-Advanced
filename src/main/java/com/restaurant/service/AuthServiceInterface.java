package com.restaurant.service;

import com.restaurant.model.User;

import java.sql.SQLException;

public interface AuthServiceInterface {
    boolean register(User user) throws SQLException;

    User login(String username, String password) throws SQLException;

    boolean isUsernameExists(String username) throws SQLException;

}
