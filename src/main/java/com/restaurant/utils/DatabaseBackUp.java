package com.restaurant.utils;

import java.sql.*;

public class DatabaseBackUp {
    private static DatabaseBackUp instance;
    private static final String URL = "jdbc:mysql://localhost:3306/backup_db";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private static Connection connection = null;

    private DatabaseBackUp() {
    }

    public static DatabaseBackUp getInstance() {
        if (instance == null) {
            instance = new DatabaseBackUp();
        }
        return instance;
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        DatabaseBackUp dbConnection = DatabaseBackUp.getInstance();

        if (dbConnection != null) {
            System.out.println("Connection test successful");
        }

    }
}