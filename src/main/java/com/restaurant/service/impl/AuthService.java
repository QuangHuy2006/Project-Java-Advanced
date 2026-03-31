package com.restaurant.service.impl;

import com.restaurant.dao.impl.UserImpl;
import com.restaurant.model.User;
import com.restaurant.dao.UserDAO;
import com.restaurant.service.AuthServiceInterface;

import java.sql.SQLException;

public class AuthService implements AuthServiceInterface{
    final UserDAO userDAO = new UserImpl();

    @Override
    public User login(String username, String password) throws SQLException {
        // Kiểm tra đầu vào
        if (username == null || username.trim().isEmpty()) {
            System.out.println("    Tên đăng nhập không được để trống!");
            return null;
        }

        if (password == null || password.trim().isEmpty()) {
            System.out.println("    Mật khẩu không được để trống!");
            return null;
        }

        // Gọi DAO để xử lý đăng nhập
        return userDAO.login(username, password);
    }

    @Override
    public boolean register(User user) throws SQLException {
        // Kiểm tra đầu vào
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            System.out.println("    Tên đăng nhập không được để trống!");
            return false;
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            System.out.println("    Mật khẩu không được để trống!");
            return false;
        }

        if (user.getPassword().length() < 6) {
            System.out.println("    Mật khẩu phải có ít nhất 6 ký tự!");
            return false;
        }

        if (userDAO.isUsernameExists(user.getUsername())) {
            System.out.println("    Tên đăng nhập đã tồn tại! Vui lòng chọn tên khác.");
            return false;
        }
        userDAO.addUserToBackUp(user);
        return userDAO.register(user);
    }

    @Override
    public boolean isUsernameExists(String username) throws SQLException {
        return userDAO.isUsernameExists(username);
    }
}