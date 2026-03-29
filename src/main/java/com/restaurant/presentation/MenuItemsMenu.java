package com.restaurant.presentation;

import com.restaurant.dao.MenuItemDAO;
import com.restaurant.dao.impl.MenuItemsImpl;
import com.restaurant.model.MenuItem;
import com.restaurant.service.impl.MenuService;
import com.restaurant.dao.impl.MenuItemsImpl.Status;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuItemsMenu {
    static int choice = 0;
    static final MenuService menuService = new MenuService();
    public static void printMenu(Scanner sc) throws SQLException {
        do{
            System.out.println("\n========================================================");
            System.out.println("       MENU ITEMS");
            System.out.println("========================================================");
            System.out.println("1. Add Dishes");
            System.out.println("2. Update Dishes");
            System.out.println("3. Show Dishes");
            System.out.println("4. Delete Dishes");
            System.out.println("5. Exit");
            System.out.print("\nNhap lua chon: ");
            choice = Integer.parseInt(sc.nextLine());
            switch(choice){
                case 1:
                    String name;
                    boolean flag;
                    do{
                        flag = false;
                        System.out.print("Input dishes name: ");
                        name = sc.nextLine();
                        if (name.trim().isEmpty()) {
                            System.out.println("Tên không được để trống!");
                            flag = true;
                            continue;
                        }
                        List<MenuItem> list = menuService.getAllDishes();
                        for(MenuItem e : list){
                            if(e.getName().equalsIgnoreCase(name)){
                                System.out.println("Tên món ăn đã tồn tại!");
                                flag = true;
                                break;
                            }
                        }
                    }while(flag);

                    System.out.print("Input dishes description: ");
                    String description = sc.nextLine();

                    BigDecimal price;
                    while (true) {
                        try {
                            System.out.print("Input dishes price: ");
                            price = new BigDecimal(sc.nextLine());
                            if (price.compareTo(BigDecimal.ZERO) <= 0) {
                                System.out.println("Giá phải > 0");
                                continue;
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Phải nhập số!");
                        }
                    }

                    String category;
                    while (true) {
                        System.out.print("Input dishes category: ");
                        category = sc.nextLine();
                        if (category.trim().isEmpty()) {
                            System.out.println("Không được để trống!");
                        } else break;
                    }

                    try {
                        menuService.addDishes(name, description, price, category);
                        System.out.println("Thêm thành công!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    int id;
                    while (true) {
                        try {
                            System.out.print("Input dishes id: ");
                            id = Integer.parseInt(sc.nextLine());
                            menuService.getExistDishes(id);
                            break;
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }

                    System.out.print("Input new name (Enter to skip): ");
                    String newName = sc.nextLine();

                    System.out.print("Input new description (Enter to skip): ");
                    String newDescription = sc.nextLine();

                    BigDecimal newPrice = null;
                    while (true) {
                        try {
                            System.out.print("Input new price (Enter to skip): ");
                            String input = sc.nextLine();

                            if (input.isEmpty()) break;

                            newPrice = new BigDecimal(input);

                            if (newPrice.compareTo(BigDecimal.ZERO) <= 0) {
                                System.out.println("Giá phải > 0");
                                continue;
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Phải nhập số!");
                        }
                    }

                    System.out.print("Input new category (Enter to skip): ");
                    String newCategory = sc.nextLine();

                    MenuItem item = menuService.getExistDishes(id);

                    System.out.print("Enter 1 to toggle status (Enter to skip): ");
                    String inputStatus = sc.nextLine();

                    Status newStatus = null;
                    if (inputStatus.equals("1")) {
                        newStatus = (item.getStatus() == Status.AVAILABLE)
                                ? Status.UNAVAILABLE
                                : Status.AVAILABLE;
                    }

                    try {
                        menuService.updateDishes(id, newName, newDescription, newPrice, newCategory, newStatus);
                        System.out.println("Update thành công!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    List<MenuItem> list = menuService.getAllDishes();
                    for(MenuItem e : list){
                        System.out.printf(e.toString());
                    }
                    break;
                case 4:
                    System.out.print("Input dishes id: ");
                    int deleteId = Integer.parseInt(sc.nextLine());
                    menuService.deleteDishes(deleteId);
                    break;
                case 5:
                    break;

            }
        }while(choice != 5);
    }
}
