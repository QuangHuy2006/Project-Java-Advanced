package com.restaurant.presentation;

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
        do {
            System.out.println("\n========================================================");
            System.out.println("       MENU ITEMS MANAGEMENT");
            System.out.println("========================================================");
            System.out.println("1. Add Dishes");
            System.out.println("2. Update Dishes");
            System.out.println("3. Show All Dishes");
            System.out.println("4. Search Dishes");
            System.out.println("5. Delete Dishes");
            System.out.println("6. Toggle Status (Available/Unavailable)");
            System.out.println("7. Exit");
            System.out.print("\nNhap lua chon: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    addDishes(sc);
                    break;
                case 2:
                    updateDishes(sc);
                    break;
                case 3:
                    showAllDishes();
                    break;
                case 4:
                    searchDishes(sc);
                    break;
                case 5:
                    deleteDishes(sc);
                    break;
                case 6:
                    toggleStatus(sc);
                    break;
                case 7:
                    System.out.println("Thoat Menu Items...");
                    break;
                default:
                    System.out.println("Lua chon khong hop le!");
            }
        } while (choice != 7);
    }

    private static void addDishes(Scanner sc) throws SQLException {
        System.out.println("\n----- THEM MON AN MOI -----");

        String name;
        boolean flag;
        do {
            flag = false;
            System.out.print("Ten mon an: ");
            name = sc.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("Ten khong duoc de trong!");
                flag = true;
                continue;
            }
            List<MenuItem> list = menuService.getAllDishes();
            for (MenuItem e : list) {
                if (e.getName().equalsIgnoreCase(name)) {
                    System.out.println("Ten mon an da ton tai!");
                    flag = true;
                    break;
                }
            }
        } while (flag);

        System.out.print("Mo ta: ");
        String description = sc.nextLine();

        BigDecimal price;
        while (true) {
            try {
                System.out.print("Gia (VND): ");
                price = new BigDecimal(sc.nextLine());
                if (price.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("Gia phai > 0");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Phai nhap so!");
            }
        }

        String category;
        while (true) {
            System.out.print("Danh muc (Mon chinh/Khai vi/Trang mieng/Do uong): ");
            category = sc.nextLine();
            if (category.trim().isEmpty()) {
                System.out.println("Khong duoc de trong!");
            } else break;
        }

        try {
            menuService.addDishes(name, description, price, category);
            System.out.println("Them mon an thanh cong!");
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }

        System.out.print("\nNhan Enter de tiep tuc...");
        sc.nextLine();
    }

    private static void updateDishes(Scanner sc) throws SQLException {
        System.out.println("\n----- CAP NHAT MON AN -----");

        int id;
        MenuItem oldItem;
        while (true) {
            try {
                System.out.print("Nhap ID mon an can cap nhat: ");
                id = Integer.parseInt(sc.nextLine());
                oldItem = menuService.getExistDishes(id);
                break;
            } catch (Exception e) {
                System.out.println("Loi: " + e.getMessage());
            }
        }

        System.out.println("\nThong tin hien tai:");
        System.out.printf("ID: %d | Ten: %s | Gia: %.0f VND | Danh muc: %s | Trang thai: %s\n",
                oldItem.getId(), oldItem.getName(), oldItem.getPrice(),
                oldItem.getCategory(), oldItem.getStatus());

        System.out.print("\nTen moi (Enter de giu nguyen): ");
        String newName = sc.nextLine();
        if (newName.trim().isEmpty()) newName = oldItem.getName();

        System.out.print("Mo ta moi (Enter de giu nguyen): ");
        String newDescription = sc.nextLine();
        if (newDescription.trim().isEmpty()) newDescription = oldItem.getDescription();

        BigDecimal newPrice = oldItem.getPrice();
        while (true) {
            try {
                System.out.print("Gia moi (Enter de giu nguyen): ");
                String input = sc.nextLine();
                if (!input.isEmpty()) {
                    newPrice = new BigDecimal(input);
                    if (newPrice.compareTo(BigDecimal.ZERO) <= 0) {
                        System.out.println("Gia phai > 0");
                        continue;
                    }
                }
                break;
            } catch (Exception e) {
                System.out.println("Phai nhap so!");
            }
        }

        System.out.print("Danh muc moi (Mon chinh/Khai vi/Trang mieng/Do uong)(Enter de giu nguyen): ");
        String newCategory = sc.nextLine();
        if (newCategory.trim().isEmpty()) newCategory = oldItem.getCategory();

        Status newStatus = oldItem.getStatus();
        System.out.print("Doi trang thai? (1: AVAILABLE / 2: UNAVAILABLE / Enter: giu nguyen): ");
        String statusInput = sc.nextLine();
        if (statusInput.equals("1")) {
            newStatus = Status.AVAILABLE;
        } else if (statusInput.equals("2")) {
            newStatus = Status.UNAVAILABLE;
        }

        try {
            menuService.updateDishes(id, newName, newDescription, newPrice, newCategory, newStatus);
            System.out.println("Cap nhat mon an thanh cong!");
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }

        System.out.print("\nNhan Enter de tiep tuc...");
        sc.nextLine();
    }

    private static void showAllDishes() throws SQLException {
        System.out.println("\n----- DANH SACH MON AN -----");
        List<MenuItem> list = menuService.getAllDishes();

        if (list.isEmpty()) {
            System.out.println("Chua co mon an nao!");
        } else {
            System.out.println("ID  | Ten mon an           | Gia (VND)   | Danh muc     | Trang thai");
            System.out.println("----|----------------------|-------------|--------------|------------");
            for (MenuItem e : list) {
                System.out.println(e.toString());
            }
        }

        System.out.print("\nNhan Enter de tiep tuc...");
        new Scanner(System.in).nextLine();
    }

    private static void searchDishes(Scanner sc) throws SQLException {
        System.out.println("\n----- TIM KIEM MON AN -----");
        System.out.print("Nhap ten mon an can tim: ");
        String keyword = sc.nextLine().toLowerCase();

        List<MenuItem> list = menuService.getAllDishes();
        List<MenuItem> results = list.stream()
                .filter(item -> item.getName().toLowerCase().contains(keyword))
                .toList();

        if (results.isEmpty()) {
            System.out.println("Khong tim thay mon an nao!");
        } else {
            System.out.println("\nKet qua tim kiem:");
            System.out.println("ID  | Ten mon an           | Gia (VND)   | Danh muc     | Trang thai");
            System.out.println("----|----------------------|-------------|--------------|------------");
            for (MenuItem e : results) {
                System.out.println(e.toString());
            }
        }

        System.out.print("\nNhan Enter de tiep tuc...");
        sc.nextLine();
    }

    private static void deleteDishes(Scanner sc) throws SQLException {
        System.out.println("\n----- XOA MON AN -----");

        int id;
        while (true) {
            try {
                System.out.print("Nhap ID mon an can xoa: ");
                id = Integer.parseInt(sc.nextLine());
                MenuItem item = menuService.getExistDishes(id);
                System.out.printf("Ban co chac chan muon xoa mon '%s'? (y/n): ", item.getName());
                String confirm = sc.nextLine();
                if (confirm.equalsIgnoreCase("y")) {
                    break;
                } else {
                    System.out.println("Huy xoa!");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Loi: " + e.getMessage());
            }
        }

        menuService.deleteDishes(id);
        System.out.println("Xoa mon an thanh cong!");

        System.out.print("\nNhan Enter de tiep tuc...");
        sc.nextLine();
    }

    private static void toggleStatus(Scanner sc) throws SQLException {
        System.out.println("\n----- DOI TRANG THAI MON AN -----");

        int id;
        while (true) {
            try {
                System.out.print("Nhap ID mon an: ");
                id = Integer.parseInt(sc.nextLine());
                MenuItem item = menuService.getExistDishes(id);
                System.out.printf("Trang thai hien tai: %s\n", item.getStatus());
                System.out.print("Chon trang thai moi (1: AVAILABLE / 2: UNAVAILABLE): ");
                String statusInput = sc.nextLine();

                Status newStatus;
                if (statusInput.equals("1")) {
                    newStatus = Status.AVAILABLE;
                } else if (statusInput.equals("2")) {
                    newStatus = Status.UNAVAILABLE;
                } else {
                    System.out.println("Lua chon khong hop le!");
                    continue;
                }

                menuService.updateDishes(id, item.getName(), item.getDescription(),
                        item.getPrice(), item.getCategory(), newStatus);
                System.out.println("Cap nhat trang thai thanh cong!");
                break;
            } catch (Exception e) {
                System.out.println("Loi: " + e.getMessage());
            }
        }

        System.out.print("\nNhan Enter de tiep tuc...");
        sc.nextLine();
    }
}