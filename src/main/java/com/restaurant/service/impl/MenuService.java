package com.restaurant.service.impl;
import com.restaurant.dao.impl.MenuItemsImpl;
import com.restaurant.dao.MenuItemDAO;
import com.restaurant.dao.impl.MenuItemsImpl.Status;
import com.restaurant.model.MenuItem;
import com.restaurant.service.MenuServiceInterface;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class MenuService implements MenuServiceInterface {

    private final MenuItemDAO menuDAO = new MenuItemsImpl();
    @Override
    public boolean addDishes(String dishesName, String description, BigDecimal price, String category) throws SQLException {
        menuDAO.addDishes(dishesName, description, price, category);
        return true;
    }
    @Override
    public boolean updateDishes(int id, String dishesName, String description, BigDecimal price, String category, Status status) throws SQLException {
        menuDAO.updateDishes(id, dishesName, description, price, category, status);
        return true;
    }
    @Override
    public boolean deleteDishes(int id) throws SQLException{

        if (id <= 0) {
            System.out.println("ID không hợp lệ!");
            return false;
        }

        menuDAO.deleteDishes(id);
        return true;
    }
    @Override
    public MenuItem getExistDishes(int id) throws SQLException {
        MenuItem item = menuDAO.getDishesById(id);

        if (item == null) {
            throw new RuntimeException("Dishes not exist!");
        }

        return item;
    }
    @Override
    public List<MenuItem> getAllDishes() throws SQLException {
        return menuDAO.showAllDishes();
    }
}
