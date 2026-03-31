package com.restaurant.service;

import com.restaurant.dao.MenuItemDAO;
import com.restaurant.dao.impl.MenuItemsImpl;
import com.restaurant.model.MenuItem;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface MenuServiceInterface {

    boolean addDishes(String dishesName, String description, BigDecimal price, String category) throws SQLException;

    boolean updateDishes(int id, String dishesName, String description, BigDecimal price, String category, MenuItemsImpl.Status status) throws SQLException;

    boolean deleteDishes(int id) throws SQLException;

    MenuItem getExistDishes(int id) throws SQLException;

    List<MenuItem> getAllDishes() throws SQLException;
}
