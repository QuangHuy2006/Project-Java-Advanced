package com.restaurant.dao;

import com.restaurant.model.Table;
import com.restaurant.model.Table.Status;
import java.sql.SQLException;
import java.util.List;

public interface TableDAO {

    void addTable(int tableNumber, int capacity) throws SQLException;

    void checkTableNumberExists(int tableNumber) throws SQLException;

    Table getExistTable(int id) throws SQLException;

    void updateTable(int id, int newCapacity, Status newStatus) throws SQLException;

    void deleteTable(int id) throws SQLException;

    List<Table> getAllTables() throws SQLException;
}