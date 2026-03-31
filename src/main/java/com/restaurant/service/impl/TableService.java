package com.restaurant.service.impl;

import com.restaurant.dao.TableDAO;
import com.restaurant.dao.impl.TableImpl;
import com.restaurant.model.Table;
import com.restaurant.model.Table.Status;
import com.restaurant.service.TableServiceInterface;

import java.sql.SQLException;
import java.util.List;

public class TableService implements TableServiceInterface {

    private final TableDAO tableDAO = new TableImpl();

    @Override
    public void addTable(int tableNumber, int capacity) throws SQLException {
        tableDAO.addTable(tableNumber, capacity);
    }

    @Override
    public void checkTableNumberExists(int tableNumber) throws SQLException {
        tableDAO.checkTableNumberExists(tableNumber);
    }

    @Override
    public Table getExistTable(int tableNumber) throws SQLException {
        Table table = tableDAO.getExistTable(tableNumber);

        if (table == null) {
            throw new RuntimeException("Bàn số " + tableNumber + " không tồn tại!");
        }

        return table;
    }

    @Override
    public void updateTable(int id, Integer capacity, Status status) throws SQLException {
        tableDAO.updateTable(id, capacity, status);
    }

    @Override
    public void deleteTable(int tableNumber) throws SQLException {
        if (tableNumber <= 0) {
            System.out.println("Số bàn không hợp lệ!");
            return;
        }

        tableDAO.deleteTable(tableNumber);
    }

    @Override
    public List<Table> getAllTables() throws SQLException {
        return tableDAO.getAllTables();
    }

    @Override
    public List<Table> getAvailableTables() throws SQLException{
        return tableDAO.getAvailableTables();
    }
}