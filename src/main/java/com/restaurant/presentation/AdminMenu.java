package com.restaurant.presentation;
import java.sql.SQLException;
import java.util.Scanner;
public class AdminMenu {
    static int choice = 0;
    public static void printMenu(Scanner sc) throws SQLException {
        do{
            System.out.println("\n========================================================");
            System.out.println("       ADMIN SYSTEM");
            System.out.println("========================================================");
            System.out.println("1. Menu Items Management");
            System.out.println("2. Tables Management");
            System.out.println("3. Users Management");
            System.out.println("4. Chef Management");
            System.out.println("5. Manager Management");
            System.out.println("6. Exit");
            System.out.print("\nNhap lua chon: ");
            choice = Integer.parseInt(sc.nextLine());
            switch(choice){
                case 1:
                    MenuItemsMenu.printMenu(sc);
                    break;
                case 2:
                    TableMenu.printMenu(sc);
                break;
            }
        }while(choice != 6);
    }
}
