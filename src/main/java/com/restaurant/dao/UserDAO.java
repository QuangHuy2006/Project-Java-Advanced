package com.restaurant.dao;

import com.restaurant.model.User;

import java.sql.SQLException;

public interface UserDAO {
    boolean register(User user) throws SQLException;

    User login(String username, String password) throws SQLException;

    User getUserById(int id) throws SQLException;

    User getUserByUsername(String username) throws SQLException;

    boolean updateUser(User user) throws SQLException;

    boolean isUsernameExists(String username) throws SQLException;

    boolean disableUser(int userId) throws  SQLException;

    boolean addUserToBackUp(User user) throws SQLException;

}