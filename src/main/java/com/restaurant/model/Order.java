package com.restaurant.model;

import java.sql.Timestamp;

public class Order {
    private int id;
    private int userId;
    private int tableId;
    private double totalAmount;
    private Status status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    public enum Status{
        PENDING,
        COOKING,
        COMPLETED,
        PAID,
        CANCELLED
    }
    public Order() {}

    public Order(int id, int userId, int tableId, double totalAmount, Status status, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.userId = userId;
        this.tableId = tableId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return String.format("Order #%d | Bàn: %d | Tổng: %.0f VND | Trạng thái: %s",
                id, tableId, totalAmount, status);
    }
}