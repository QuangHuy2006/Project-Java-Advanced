package com.restaurant.presentation;

import com.restaurant.model.User;
import com.restaurant.service.impl.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserMenu {

    static int choice = 0;
    static final UserService userService = new UserService();

    public static void printMenu(Scanner sc) throws SQLException {
        do {
            System.out.println("\n========================================================");
            System.out.println("       USER MANAGEMENT");
            System.out.println("========================================================");
            System.out.println("1. Show All Users");
            System.out.println("2. Search User by ID");
            System.out.println("3. Search User by Username");
            System.out.println("4. Create New User");
            System.out.println("5. Update User Role");
            System.out.println("6. Disable/Enable User");
            System.out.println("7. Exit");
            System.out.print("\nNhap lua chon: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so!");
                continue;
            }

            switch (choice) {
                case 1:
                    showAllUsers();
                    break;
                case 2:
                    searchUserById(sc);
                    break;
                case 3:
                    searchUserByUsername(sc);
                    break;
                case 4:
                    createUser(sc);
                    break;
                case 5:
                    updateUserRole(sc);
                    break;
                case 6:
                    toggleUserStatus(sc);
                    break;
                case 7:
                    System.out.println("Thoat User Management...");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 7);
    }

    private static void showAllUsers() throws SQLException {
        System.out.println("\n----- DANH SACH NGUOI DUNG -----");
        List<User> users = userService.getAllUsers();

        if (users.isEmpty()) {
            System.out.println("Khong co nguoi dung nao!");
        } else {
            System.out.println("ID  | Ten dang nhap      | Vai tro     | Trang thai");
            System.out.println("----|--------------------|-------------|------------");
            for (User u : users) {
                    System.out.printf("%-3d | %-18s | %-11s | %s\n",
                            u.getId(), u.getUsername(), u.getRole(), u.getStatus());
            }
        }

        System.out.print("\nNhan Enter de tiep tuc...");
        new Scanner(System.in).nextLine();
    }

    private static void searchUserById(Scanner sc) throws SQLException {
        System.out.println("\n----- TIM KIEM NGUOI DUNG THEO ID -----");
        System.out.print("Nhap ID: ");
        try {
            int id = Integer.parseInt(sc.nextLine());
            User user = userService.getUserById(id);
            if (user != null) {
                printUserDetail(user);
            } else {
                System.out.println("Khong tim thay nguoi dung voi ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("ID phai la so!");
        }

        System.out.print("\nNhan Enter de tiep tuc...");
        sc.nextLine();
    }

    private static void searchUserByUsername(Scanner sc) throws SQLException {
        System.out.println("\n----- TIM KIEM NGUOI DUNG THEO TEN -----");
        System.out.print("Nhap ten dang nhap: ");
        String username = sc.nextLine();

        User user = userService.getUserByUsername(username);
        if (user != null) {
            printUserDetail(user);
        } else {
            System.out.println("Khong tim thay nguoi dung voi ten: " + username);
        }

        System.out.print("\nNhan Enter de tiep tuc...");
        sc.nextLine();
    }

    private static void printUserDetail(User user) {
        System.out.println("\nThong tin nguoi dung:");
        System.out.println("ID: " + user.getId());
        System.out.println("Ten dang nhap: " + user.getUsername());
        System.out.println("Vai tro: " + user.getRole());
        System.out.println("Trang thai: " + user.getStatus());
    }

    private static void createUser(Scanner sc) throws SQLException {
        System.out.println("\n----- TAO NGUOI DUNG MOI -----");

        System.out.print("Ten dang nhap: ");
        String username = sc.nextLine();

        if (userService.getUserByUsername(username) != null) {
            System.out.println("Ten dang nhap da ton tai!");
            System.out.print("\nNhan Enter de tiep tuc...");
            sc.nextLine();
            return;
        }

        System.out.print("Mat khau: ");
        String password = sc.nextLine();

        if (password.length() < 6) {
            System.out.println("Mat khau phai co it nhat 6 ky tu!");
            System.out.print("\nNhan Enter de tiep tuc...");
            sc.nextLine();
            return;
        }

        System.out.println("Chon vai tro:");
        System.out.println("1. MANAGER");
        System.out.println("2. CHEF");
        System.out.println("3. CUSTOMER");
        System.out.print("Nhap lua chon: ");
        String roleChoice = sc.nextLine();

        String role;
        switch (roleChoice) {
            case "1":
                role = "MANAGER";
                break;
            case "2":
                role = "CHEF";
                break;
            case "3":
                role = "CUSTOMER";
                break;
            default:
                System.out.println("Lua chon khong hop le!");
                return;
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRole(role);
        newUser.setStatus("ACTIVE");

        boolean result = userService.createUser(newUser);

        if (result) {
            System.out.println("Tao nguoi dung thanh cong!");
        } else {
            System.out.println("Tao nguoi dung that bai!");
        }

        System.out.print("\nNhan Enter de tiep tuc...");
        sc.nextLine();
    }

    private static void updateUserRole(Scanner sc) throws SQLException {
        System.out.println("\n----- CAP NHAT VAI TRO NGUOI DUNG -----");
        System.out.print("Nhap ID nguoi dung: ");

        try {
            int userId = Integer.parseInt(sc.nextLine());
            User user = userService.getUserById(userId);

            if (user == null) {
                System.out.println("Khong tim thay nguoi dung!");
                System.out.print("\nNhan Enter de tiep tuc...");
                sc.nextLine();
                return;
            }

            System.out.printf("\nNguoi dung: %s\n", user.getUsername());
            System.out.printf("Vai tro hien tai: %s\n", user.getRole());

            System.out.println("\nChon vai tro moi:");
            System.out.println("1. MANAGER");
            System.out.println("2. CHEF");
            System.out.println("3. CUSTOMER");
            System.out.print("Nhap lua chon: ");
            String roleChoice = sc.nextLine();

            String newRole;
            switch (roleChoice) {
                case "1":
                    newRole = "MANAGER";
                    break;
                case "2":
                    newRole = "CHEF";
                    break;
                case "3":
                    newRole = "CUSTOMER";
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
                    return;
            }

            boolean result = userService.updateUserRole(userId, newRole);
            if (result) {
                System.out.println("Cap nhat vai tro thanh cong!");
            } else {
                System.out.println("Cap nhat that bai!");
                System.out.println("Khong the cap nhat admin!");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID phai la so!");
        }

        System.out.print("\nNhan Enter de tiep tuc...");
        sc.nextLine();
    }

    private static void toggleUserStatus(Scanner sc) throws SQLException {
        System.out.println("\n----- KICH HOAT / VO HIEU HOA NGUOI DUNG -----");
        System.out.print("Nhap ID nguoi dung: ");

        try {
            int userId = Integer.parseInt(sc.nextLine());
            User user = userService.getUserById(userId);

            if (user == null) {
                System.out.println("Khong tim thay nguoi dung!");
                System.out.print("\nNhan Enter de tiep tuc...");
                sc.nextLine();
                return;
            }

            System.out.printf("\nNguoi dung: %s\n", user.getUsername());
            System.out.printf("Trang thai hien tai: %s\n", user.getStatus());

            String newStatus = user.getStatus().equals("ACTIVE") ? "INACTIVE" : "ACTIVE";
            System.out.printf("Ban co muon %s nguoi dung nay? (y/n): ",
                    newStatus.equals("ACTIVE") ? "kich hoat" : "vo hieu hoa");

            String confirm = sc.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                user.setStatus(newStatus);
                boolean result = userService.updateUser(user);
                if (result) {
                    System.out.println("Cap nhat trang thai thanh cong!");
                } else {
                    System.out.println("Cap nhat that bai!");
                    System.out.println("Khong the cap nhat admin");
                }
            } else {
                System.out.println("Huy thao tac!");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID phai la so!");
        }

        System.out.print("\nNhan Enter de tiep tuc...");
        sc.nextLine();
    }
}