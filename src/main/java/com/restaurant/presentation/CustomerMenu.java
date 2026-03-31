package com.restaurant.presentation;

import com.restaurant.model.User;
import com.restaurant.service.impl.CustomerService;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerMenu {

    static int choice = 0;
    static CustomerService ser = new CustomerService();
    public static void printMenu(Scanner sc, User user) throws SQLException {
        do {
            System.out.println("\n========================================================");
            System.out.println("       CUSTOMER SYSTEM - Welcome " + user.getUsername());
            System.out.println("========================================================");
            System.out.println("1. View Menu");
            System.out.println("2. Search Dishes");
            System.out.println("3. Create New Order");
            System.out.println("4. Add Dishes to Order");
            System.out.println("5. View Current Order");
            System.out.println("6. Update Order Item Quantity");
            System.out.println("7. Remove Order Item");
            System.out.println("8. Checkout (Pay)");
            System.out.println("9. View My Order History");
            System.out.println("10. Exit");
            System.out.print("\nNhap lua chon: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so!");
                continue;
            }

            switch (choice) {
                case 1:
                    ser.viewMenu();
                    break;
                case 2:
                    ser.searchDishes(sc);
                    break;
                case 3:
                    ser.createNewOrder(sc, user);
                    break;
                case 4:
                    ser.addDishesToOrder(sc, user);
                    break;
                case 5:
                    ser.viewCurrentOrder();
                    break;
                case 6:
                    ser.updateOrderItemQuantity(sc, user);
                    break;
                case 7:
                    ser.removeOrderItem(sc, user);
                    break;
                case 8:
                    ser.checkout(user);
                    break;
                case 9:
                    ser.viewOrderHistory(user);
                    break;
                case 10:
                    System.out.println("Thoat Customer System...");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 10);
    }
}