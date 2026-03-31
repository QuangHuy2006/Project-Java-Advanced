package com.restaurant.service.impl;

import com.restaurant.dao.UserDAO;
import com.restaurant.dao.impl.UserImpl;
import com.restaurant.model.User;
import com.restaurant.service.UserServiceInterface;

import java.sql.SQLException;
import java.util.List;

public class UserService implements UserServiceInterface {

    private final UserDAO userDAO = new UserImpl();

    @Override
    public User getUserById(int id) throws SQLException {
        if (id <= 0) {
            System.out.println("ID không hợp lệ!");
            return null;
        }
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByUsername(String username) throws SQLException {
        if (username == null || username.trim().isEmpty()) {
            System.out.println("Tên đăng nhập không được để trống!");
            return null;
        }
        return userDAO.getUserByUsername(username);
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        if (user == null) {
            System.out.println("Thông tin người dùng không hợp lệ!");
            return false;
        }
        if (user.getId() <= 0) {
            System.out.println("ID người dùng không hợp lệ!");
            return false;
        }
        return userDAO.updateUser(user);
    }

    @Override
    public boolean disableUser(int userId) throws SQLException {
        if (userId <= 0) {
            System.out.println("ID người dùng không hợp lệ!");
            return false;
        }

        User user = getUserById(userId);
        if (user == null) {
            System.out.println("Không tìm thấy người dùng!");
            return false;
        }

        return userDAO.disableUser(userId);
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    @Override
    public boolean createUser(User user) throws SQLException {
        if (user == null) {
            System.out.println("Thông tin người dùng không hợp lệ!");
            return false;
        }

        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            System.out.println("Tên đăng nhập không được để trống!");
            return false;
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            System.out.println("Mật khẩu không được để trống!");
            return false;
        }

        if (user.getPassword().length() < 6) {
            System.out.println("Mật khẩu phải có ít nhất 6 ký tự!");
            return false;
        }

        if (userDAO.isUsernameExists(user.getUsername())) {
            System.out.println("Tên đăng nhập đã tồn tại!");
            return false;
        }

        return userDAO.register(user);
    }

    @Override
    public boolean updateUserRole(int userId, String role) throws SQLException {
        if (userId <= 0) {
            System.out.println("ID người dùng không hợp lệ!");
            return false;
        }

        if (role == null || role.trim().isEmpty()) {
            System.out.println("Vai trò không hợp lệ!");
            return false;
        }

        // Kiểm tra role hợp lệ
        String validRole = role.toUpperCase();
        if (!validRole.equals("ADMIN") && !validRole.equals("MANAGER")
                && !validRole.equals("CHEF") && !validRole.equals("CUSTOMER")) {
            System.out.println("Vai trò không hợp lệ! Chấp nhận: ADMIN, MANAGER, CHEF, CUSTOMER");
            return false;
        }

        User user = getUserById(userId);
        if (user == null) {
            System.out.println("Không tìm thấy người dùng!");
            return false;
        }

        user.setRole(validRole);
        return userDAO.updateUser(user);
    }
}