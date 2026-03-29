package com.restaurant.model;

import java.sql.Timestamp;

public class Order {
    private int id;
    private int userId;
    private int tableId;
    private double totalAmount;
    private Timestamp createdAt;
    private Timestamp checkedOutAt;

    public Order() {}

    public Order(int id, int userId, int tableId, double totalAmount, Timestamp createdAt, Timestamp checkedOutAt) {
        this.id = id;
        this.userId = userId;
        this.tableId = tableId;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.checkedOutAt = checkedOutAt;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getTableId() { return tableId; }
    public void setTableId(int tableId) { this.tableId = tableId; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
    public Timestamp getCheckedOutAt() { return checkedOutAt; }
    public void setCheckedOutAt(Timestamp checkedOutAt) { this.checkedOutAt = checkedOutAt; }
}