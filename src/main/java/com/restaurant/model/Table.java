package com.restaurant.model;

public class Table {
    private int id;
    private int tableNumber;
    private int capacity;
    private Status status;

    public enum Status {
        AVAILABLE,
        OCCUPIED,
        RESERVED
    }

    public Table() {}

    public Table(int id, int tableNumber, int capacity, Status status) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.status = status;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getTableNumber() { return tableNumber; }
    public void setTableNumber(int tableNumber) { this.tableNumber = tableNumber; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("Table %d | Capacity: %d người | Status: %s",
                tableNumber, capacity, status);
    }
}