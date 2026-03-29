package com.restaurant.model;

public class OrderDetail {
    private int id;
    private int orderId;
    private int menuItemId;
    private int quantity;
    private double price;
    private String status;
    private String menuItemName; // For display

    public OrderDetail() {}

    public OrderDetail(int id, int orderId, int menuItemId, int quantity, double price, String status) {
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
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getMenuItemName() { return menuItemName; }
    public void setMenuItemName(String menuItemName) { this.menuItemName = menuItemName; }

    public String getStatusText() {
        switch (status) {
            case "PENDING": return "Pending";
            case "COOKING": return "Cooking";
            case "READY": return "Ready";
            case "SERVED": return "Served";
            default: return status;
        }
    }
}