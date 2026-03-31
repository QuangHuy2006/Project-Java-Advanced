package com.restaurant.presentation;

import com.restaurant.model.OrderDetail;
import com.restaurant.service.impl.OrderService;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import com.restaurant.model.Order.Status;
import com.restaurant.model.Order;

public class OrderMenu {
    static int choice = 0;
    static final OrderService orderService = new OrderService();

    public static void printMenu(Scanner sc) throws SQLException {
        do {
            System.out.println("\n========================================================");
            System.out.println("       ORDERS MANAGEMENT");
            System.out.println("========================================================");
            System.out.println("1. View All Orders");
            System.out.println("2. View Orders by Status");
            System.out.println("3. Update Order Status");
            System.out.println("4. View Order Details");
            System.out.println("5. View Order By Id");
            System.out.println("6. View Order Details By Status");
            System.out.println("7. Exit");
            System.out.print("\nNhap lua chon: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    List<Order> list = orderService.getAllOrders();
                    for(Order or : list){
                        System.out.println(or.toString());
                    }
                    break;
                case 2:
                    int miniChoice = 0;
                    List<Order> orderByStatus;
                    System.out.println("1. Pending");
                    System.out.println("2. Cooking");
                    System.out.println("3. Completed");
                    System.out.println("4. Paid");
                    System.out.println("5. Cancelled");
                    miniChoice = Integer.parseInt(sc.nextLine());
                    switch(miniChoice){
                        case 1:
                            orderByStatus = orderService.getOrdersByStatus(Status.PENDING, null);
                            for(Order or : orderByStatus){
                                System.out.println(or.toString());
                            }
                            break;
                        case 2:
                            orderByStatus = orderService.getOrdersByStatus(Status.COOKING, null);
                            for(Order or : orderByStatus){
                                System.out.println(or.toString());
                            }
                            break;
                        case 3:
                            orderByStatus = orderService.getOrdersByStatus(Status.COMPLETED, null);
                            for(Order or : orderByStatus){
                                System.out.println(or.toString());
                            }
                            break;
                        case 4:
                            orderByStatus = orderService.getOrdersByStatus(Status.PAID, null);
                            for(Order or : orderByStatus){
                                System.out.println(or.toString());
                            }
                            break;
                        case 5:
                            orderByStatus = orderService.getOrdersByStatus(Status.CANCELLED, null);
                            for(Order or : orderByStatus){
                                System.out.println(or.toString());
                            }
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Nhap id can tim: ");
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
                            orderService.updateOrderStatus(id, Status.PENDING);
                            break;
                        case 2:
                            orderService.updateOrderStatus(id, Status.COOKING);
                            break;
                        case 3:
                            orderService.updateOrderStatus(id, Status.COMPLETED);
                            break;
                        case 4:
                            orderService.updateOrderStatus(id, Status.PAID);
                            break;
                        case 5:
                            orderService.updateOrderStatus(id, Status.CANCELLED);
                            break;
                    }
                    break;
                case 4:
                    orderService.getAllOrderDetails();
                    break;
                case 5:
                    System.out.print("Nhap id can tim kiem: ");
                    int findId = Integer.parseInt(sc.nextLine());
                    System.out.println(orderService.getOrderById(findId).toString());
                    break;
                case 6:
                    int miniThirdChoice = 0;
                    System.out.println("STATUS");
                    System.out.println("1. Pending");
                    System.out.println("2. Cooking");
                    System.out.println("3. Completed");
                    System.out.println("4. Paid");
                    System.out.println("5. Cancelled");
                    miniThirdChoice = Integer.parseInt(sc.nextLine());
                    Status findStatus = switch (miniThirdChoice) {
                        case 1 -> Status.PENDING;
                        case 2 -> Status.COOKING;
                        case 3 -> Status.COMPLETED;
                        case 4 -> Status.PAID;
                        case 5 -> Status.CANCELLED;
                        default -> null;
                    };
                    List<OrderDetail> l = orderService.getOrderDetailsByStatus(findStatus);
                    for(OrderDetail item : l){
                        System.out.println(item.toString());
                    }
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 7);
    }
}