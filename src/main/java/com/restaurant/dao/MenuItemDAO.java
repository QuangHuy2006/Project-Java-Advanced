package com.restaurant.dao;
import com.restaurant.dao.impl.MenuItemsImpl.Status;
import com.restaurant.model.MenuItem;

import java.math.BigDecimal;
import java.util.List;

public interface MenuItemDAO {

    void addDishes(String dishesName, String description, BigDecimal price, String category);

    void updateDishes(int id, String dishesName, String description, BigDecimal price, String category, Status status);

    void deleteDishes(int id);

    MenuItem getDishesById(int id);

    List<MenuItem> showAllDishes();
}