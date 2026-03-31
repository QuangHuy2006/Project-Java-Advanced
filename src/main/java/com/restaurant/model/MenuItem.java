package com.restaurant.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import com.restaurant.dao.impl.MenuItemsImpl.Status;

public class MenuItem {
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private Status status;
    private Timestamp createdAt;


    public MenuItem() {}

    public MenuItem(int id, String name, String description, BigDecimal price,
                    String category, Status status, Timestamp createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getter / Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return String.format("%-3d | %-20s | %7.0f VND | %-12s | %-12s\n",
                id, name, price, category, status);
    }
}