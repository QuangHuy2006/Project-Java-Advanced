package com.restaurant.service;

import com.restaurant.dao.TableDAO;
import com.restaurant.dao.impl.TableImpl;
import com.restaurant.model.Table;

import java.sql.SQLException;
import java.util.List;

public interface TableServiceInterface {
    void addTable(int tableNumber, int capacity) throws SQLException;

    void checkTableNumberExists(int tableNumber) throws SQLException;

    Table getExistTable(int tableNumber) throws SQLException;

    void updateTable(int id, Integer capacity, Table.Status status) throws SQLException;

    void deleteTable(int tableNumber) throws SQLException;

    List<Table> getAllTables() throws SQLException;

    List<Table> getAvailableTables() throws SQLException;
}
