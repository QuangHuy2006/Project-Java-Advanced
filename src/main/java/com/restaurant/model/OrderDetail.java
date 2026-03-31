package com.restaurant.model;

import java.sql.Timestamp;
import com.restaurant.model.Order.Status;

public class OrderDetail {
    private int id;
    private int orderId;
    private int menuItemId;
    private int quantity;
    private double price;
    private Status status;
    private String menuItemName;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public OrderDetail() {}

    public OrderDetail(int id, int orderId, int menuItemId, int quantity, double price, Status status) {
        this.id = id;
        this.orderId = orderId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getMenuItemId() { return menuItemId; }
    public void setMenuItemId(int menuItemId) { this.menuItemId = menuItemId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public String getMenuItemName() { return menuItemName; }
    public void setMenuItemName(String menuItemName) { this.menuItemName = menuItemName; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }


    public double getSubtotal() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return String.format("%-20s x %d = %.0f VND [%s]",
                menuItemName, quantity, getSubtotal(), status);
    }
}