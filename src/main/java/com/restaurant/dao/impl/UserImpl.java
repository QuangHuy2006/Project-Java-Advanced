package com.restaurant.dao.impl;

import com.restaurant.dao.UserDAO;
import com.restaurant.model.User;
import com.restaurant.utils.DatabaseBackUp;
import com.restaurant.utils.DatabaseConnection;
import com.restaurant.utils.PasswordHasher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImpl implements UserDAO {

    @Override
    public User login(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            System.out.println("    Tên đăng nhập không được để trống!");
            return null;
        }

        if (password == null || password.trim().isEmpty()) {
            System.out.println("    Mật khẩu không được để trống!");
            return null;
        }

        String sql = "SELECT * FROM users WHERE username = ? AND status = 'ACTIVE'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hashedPassword = rs.getString("password");

                if (PasswordHasher.verify(password, hashedPassword)) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setFullName(rs.getString("full_name"));
                    user.setRole(rs.getString("role"));
                    user.setStatus(rs.getString("status"));
                    user.setPhone(rs.getString("phone"));
                    user.setEmail(rs.getString("email"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean register(User user) {
        String sql = "INSERT INTO users (username, password, full_name, phone, email, role, status) VALUES (?, ?, ?, ?, ?, 'CUSTOMER', 'ACTIVE')";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, PasswordHasher.hash(user.getPassword()));
            stmt.setString(3, user.getFullName());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getEmail());

            int result = stmt.executeUpdate();

            if (result > 0) {
                System.out.println("    Đăng ký thành công! Vui lòng đăng nhập.");
                return true;
            } else {
                System.out.println("    Đăng ký thất bại! Vui lòng thử lại.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("    Lỗi database: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addUserToBackUp(User user) {
        String sql = "INSERT INTO users (username, password, full_name, phone, email, role, status) VALUES (?, ?, ?, ?, ?, 'CUSTOMER', 'ACTIVE')";

        try (Connection conn = DatabaseBackUp.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFullName());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getEmail());
            int result = stmt.executeUpdate();

            if (result > 0) {
                System.out.println("    Đăng ký thành công! Vui lòng đăng nhập.");
                return true;
            } else {
                System.out.println("    Đăng ký thất bại! Vui lòng thử lại.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("    Lỗi database: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean isUsernameExists(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setFullName(rs.getString("full_name"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getString("status"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("full_name"));
                user.setRole(rs.getString("role"));
                user.setStatus(rs.getString("status"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "UPDATE users SET full_name = ?, phone = ?, email = ?, status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getPhone());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getStatus());
            stmt.setInt(5, user.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean disableUser(int userId) {
        String sql = "UPDATE users SET status = 'INACTIVE' WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
