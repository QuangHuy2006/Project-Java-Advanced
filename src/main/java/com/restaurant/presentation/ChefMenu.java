package com.restaurant.presentation;

import java.sql.SQLException;

import com.restaurant.model.Order;
import com.restaurant.model.Order.Status;
import com.restaurant.service.impl.ChefService;
import java.util.Scanner;
import com.restaurant.service.impl.MenuService;

public class ChefMenu {
    static int choice = 0;
    static ChefService chefService = new ChefService();
    static MenuService menuService = new MenuService();
    public static void printMenu(Scanner sc) throws SQLException {
        do {
            System.out.println("\n========================================================");
            System.out.println("       CHEF SYSTEM");
            System.out.println("========================================================");
            System.out.println("1. View Pending Orders");
            System.out.println("2. Update Order Status");
            System.out.println("3. View All Orders by Status");
            System.out.println("4. View Menu Items");
            System.out.println("5. Exit");
            System.out.print("\nNhap lua chon: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    chefService.getPendingOrders();
                    break;
                case 2:
                    System.out.print("Nhap id muon tim kiem: ");
                    int id = Integer.parseInt(sc.nextLine());
                    int miniSecChoice = 0;
                    System.out.println("STATUS");
                    System.out.println("1. Pending");
                    System.out.println("2. Cooking");
                    System.out.println("3. Completed");
                    System.out.println("4. Paid");
                    System.out.println("5. Cancelled");
                    miniSecChoice = Integer.parseInt(sc.nextLine());
                    switch(miniSecChoice){
                        case 1:
                            chefService.updateOrderStatus(id, Status.PENDING);
                            break;
                        case 2:
                            chefService.updateOrderStatus(id, Status.COOKING);
                            break;
                        case 3:
                            chefService.updateOrderStatus(id, Status.COMPLETED);
                            break;
                        case 4:
                            chefService.updateOrderStatus(id, Status.PAID);
                            break;
                        case 5:
                            chefService.updateOrderStatus(id, Status.CANCELLED);
                            break;
                    }
                    break;
                case 3:
                    System.out.print("Nhap trang thai muon tim kiem: ");
                    int miniChoice = 0;
                    System.out.println("STATUS");
                    System.out.println("1. Pending");
                    System.out.println("2. Cooking");
                    System.out.println("3. Completed");
                    System.out.println("4. Paid");
                    System.out.println("5. Cancelled");
                    miniChoice = Integer.parseInt(sc.nextLine());
                    switch(miniChoice){
                        case 1:
                            chefService.getOrdersByStatus(Status.PENDING);
                            break;
                        case 2:
                            chefService.getOrdersByStatus(Status.COOKING);
                            break;
                        case 3:
                            chefService.getOrdersByStatus(Status.COMPLETED);
                            break;
                        case 4:
                            chefService.getOrdersByStatus(Status.PAID);
                            break;
                        case 5:
                            chefService.getOrdersByStatus(Status.CANCELLED);
                            break;
                    }
                    break;
                case 4:
                    menuService.getAllDishes();
                    break;
                case 5:
                    System.out.println("Thoat Chef System...");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 5);
    }
}