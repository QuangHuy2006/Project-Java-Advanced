package com.restaurant.presentation;

import java.sql.SQLException;
import java.util.Scanner;
import com.restaurant.model.User;
import com.restaurant.service.impl.AuthService;


public class Main {
    private static final AuthService authService = new AuthService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        while (true) {
            printMainMenu();
            int choice = getChoice(1, 3);

            switch (choice) {
                case 1:
                    printMenuByRole(login().getRole());
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("\n   Cam on ban da su dung he thong! Hen gap lai!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("   Lua chon khong hop le!");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n========================================================");
        System.out.println("       RESTAURANT MANAGEMENT SYSTEM");
        System.out.println("========================================================");
        System.out.println("1. DANG NHAP");
        System.out.println("2. DANG KY (Khach hang)");
        System.out.println("3. THOAT");
        System.out.print("\nNhap lua chon: ");
    }

    private static void printMenuByRole(String role) throws SQLException {
        switch (role){
            case "ADMIN":
                AdminMenu.printMenu(scanner);
                break;
            case "CHEF":
                ChefMenu.printMenu(scanner);
                break;
            case "MANGER":
                ManagerMenu.printMenu(scanner);
                break;
            case "CUSTOMER":
                CustomerMenu.printMenu(scanner);
                break;
        }
    }

    private static int getChoice(int min, int max) {
        while (true) {
            try {
                int choice = scanner.nextInt();

                scanner.nextLine();
                if (choice >= min && choice <= max) {
                    return choice;
                }
                System.out.printf("Vui long nhap so tu %d den %d: ", min, max);
            } catch (Exception e) {
                System.out.print("Vui long nhap so hop le: ");
                scanner.nextLine();
            }
        }
    }

    private static User login() throws SQLException {
        System.out.println("\n==================== DANG NHAP ====================");
        System.out.print("Ten dang nhap: ");
        String username = scanner.nextLine();

        System.out.print("Mat khau: ");
        String password = scanner.nextLine();

        User user = authService.login(username, password);

        if (user == null) {
            System.out.println("\nSai ten dang nhap hoac mat khau!");
            System.out.print("\nNhan Enter de tiep tuc...");
            scanner.nextLine();

        }

        if (!user.getStatus().equals("ACTIVE")) {
            System.out.println("\nTai khoan da bi khoa! Vui long lien he quan ly.");
            System.out.print("\nNhan Enter de tiep tuc...");
            scanner.nextLine();

        }

        System.out.println("\nDang nhap thanh cong! Chao mung " + user.getFullName() + "!");
        System.out.print("\nNhan Enter de tiep tuc...");
        scanner.nextLine();
        return user;
    }

    private static void register() throws SQLException {
        System.out.println("\n==================== DANG KY ====================");
        System.out.print("Ten dang nhap: ");
        String username = scanner.nextLine();

        if (authService.isUsernameExists(username)) {
            System.out.println("\nTen dang nhap da ton tai!");
            System.out.print("\nNhan Enter de tiep tuc...");
            scanner.nextLine();
            return;
        }

        System.out.print("Mat khau: ");
        String password = scanner.nextLine();

        if (password.length() < 6) {
            System.out.println("\nMat khau phai co it nhat 6 ky tu!");
            System.out.print("\nNhan Enter de tiep tuc...");
            scanner.nextLine();
            return;
        }

        System.out.print("Ho va ten: ");
        String fullName = scanner.nextLine();

        System.out.print("So dien thoai: ");
        String phone = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setFullName(fullName);
        newUser.setPhone(phone);
        newUser.setEmail(email);
        newUser.setRole("CUSTOMER");
        newUser.setStatus("ACTIVE");

        boolean result = authService.register(newUser);

        if (result) {
            System.out.println("\nDang ky thanh cong! Vui long dang nhap.");
        } else {
            System.out.println("\nDang ky that bai! Vui long thu lai.");
        }

        System.out.print("\nNhan Enter de tiep tuc...");
        scanner.nextLine();
    }
}