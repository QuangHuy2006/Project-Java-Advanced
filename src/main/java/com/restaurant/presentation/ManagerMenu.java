package com.restaurant.presentation;

import java.sql.SQLException;
import java.util.Scanner;

public class ManagerMenu {
    static int choice = 0;

    public static void printMenu(Scanner sc) throws SQLException {
        do {
            System.out.println("\n========================================================");
            System.out.println("       MANAGER SYSTEM");
            System.out.println("========================================================");
            System.out.println("1. Menu Items Management");
            System.out.println("2. Tables Management");
            System.out.println("3. Orders Management");
            System.out.println("4. Exit");
            System.out.print("\nNhap lua chon: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    MenuItemsMenu.printMenu(sc);
                    break;
                case 2:
                    TableMenu.printMenu(sc);
                    break;
                case 3:
                    OrderMenu.printMenu(sc);
                    break;
                case 4:
                    System.out.println("Thoat Manager System...");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 4);
    }
}