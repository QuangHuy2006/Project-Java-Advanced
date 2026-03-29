package com.restaurant.service.impl;

import com.restaurant.dao.TableDAO;
import com.restaurant.dao.impl.TableImpl;
import com.restaurant.model.Table;
import com.restaurant.model.Table.Status;

import java.sql.SQLException;
import java.util.List;

public class TableService {

    private final TableDAO tableDAO = new TableImpl();

    // ================= ADD TABLE =================
    public void addTable(int tableNumber, int capacity) throws SQLException {
        tableDAO.addTable(tableNumber, capacity);
    }

    // ================= CHECK TABLE EXISTS =================
    public void checkTableNumberExists(int tableNumber) throws SQLException {
        tableDAO.checkTableNumberExists(tableNumber);
    }

    // ================= GET EXIST TABLE =================
    public Table getExistTable(int tableNumber) throws SQLException {
        Table table = tableDAO.getExistTable(tableNumber);

        if (table == null) {
            throw new RuntimeException("Bàn số " + tableNumber + " không tồn tại!");
        }

        return table;
    }

    // ================= UPDATE TABLE =================
    // Chỉ có 3 tham số: id (tableNumber), capacity, status
    public void updateTable(int id, Integer capacity, Status status) throws SQLException {
        tableDAO.updateTable(id, capacity, status);
    }

    // ================= DELETE TABLE =================
    public void deleteTable(int tableNumber) throws SQLException {
        if (tableNumber <= 0) {
            System.out.println("Số bàn không hợp lệ!");
            return;
        }

        tableDAO.deleteTable(tableNumber);
    }

    // ================= GET ALL TABLES =================
    public List<Table> getAllTables() throws SQLException {
        return tableDAO.getAllTables();
    }
}