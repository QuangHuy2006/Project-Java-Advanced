package com.restaurant.presentation;

import com.restaurant.model.Table;
import com.restaurant.service.impl.TableService;
import com.restaurant.model.Table.Status;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TableMenu {

    static int choice = 0;
    static final TableService tableService = new TableService();

    public static void printMenu(Scanner sc) throws SQLException {
        do {
            System.out.println("\n========================================================");
            System.out.println("       TABLE");
            System.out.println("========================================================");
            System.out.println("1. Add Table");
            System.out.println("2. Update Table");
            System.out.println("3. Show Table");
            System.out.println("4. Delete Table");
            System.out.println("5. Exit");
            System.out.print("\nNhap lua chon: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                // ================= ADD =================
                case 1:
                    int tableNumber;
                    while (true) {
                        try {
                            System.out.print("Input table number: ");
                            tableNumber = Integer.parseInt(sc.nextLine());

                            if (tableNumber <= 0) {
                                System.out.println("Phải > 0");
                                continue;
                            }

                            tableService.checkTableNumberExists(tableNumber);
                            break;

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    int capacity;
                    while (true) {
                        try {
                            System.out.print("Input capacity: ");
                            capacity = Integer.parseInt(sc.nextLine());

                            if (capacity <= 0) {
                                System.out.println("Phải > 0");
                                continue;
                            }
                            break;

                        } catch (Exception e) {
                            System.out.println("Phải nhập số!");
                        }
                    }

                    try {
                        tableService.addTable(tableNumber, capacity);
                        System.out.println("Thêm bàn thành công!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                // ================= UPDATE =================
                case 2:
                    int updateNumber;
                    Table oldTable;

                    // Nhập số bàn cần update
                    while (true) {
                        try {
                            System.out.print("Input table number: ");
                            updateNumber = Integer.parseInt(sc.nextLine());

                            oldTable = tableService.getExistTable(updateNumber);
                            break;

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    int newCapacity = 1;
                    while (true) {
                        try {
                            System.out.print("Input new capacity (Enter để giữ nguyên): ");
                            String input = sc.nextLine().trim();

                            if (input.isEmpty()) {
                                break;  // giữ nguyên capacity cũ
                            }

                            int tempCapacity = Integer.parseInt(input);

                            if (tempCapacity <= 0) {
                                System.out.println("Phải > 0");
                                continue;
                            }
                            newCapacity = tempCapacity;
                            break;

                        } catch (Exception e) {
                            System.out.println("Phải nhập số hợp lệ!");
                        }

                    }

                    // ================= Cập nhật Status =================
                    Status newStatus = null;   // null nghĩa là giữ nguyên
                    System.out.print("Enter 1 để thay đổi trạng thái (Enter để giữ nguyên): ");
                    String inputStatus = sc.nextLine().trim();

                    if (inputStatus.equals("1")) {
                        // Cycle trạng thái: AVAILABLE → OCCUPIED → RESERVED → AVAILABLE
                        switch (oldTable.getStatus()) {
                            case AVAILABLE:
                                newStatus = Status.OCCUPIED;
                                break;
                            case OCCUPIED:
                                newStatus = Status.RESERVED;
                                break;
                            case RESERVED:
                                newStatus = Status.AVAILABLE;
                                break;
                        }
                        System.out.println("Trạng thái sẽ thay đổi thành: " + newStatus);
                    }else{
                        newStatus = oldTable.getStatus();
                    }

                    // Gọi update (chỉ update capacity và status, KHÔNG update table_number)
                    try {
                        tableService.updateTable(updateNumber, newCapacity, newStatus);
                        System.out.println("Update bàn thành công!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                // ================= SHOW =================
                case 3:
                    List<Table> list = tableService.getAllTables();

                    if (list.isEmpty()) {
                        System.out.println("Không có bàn nào!");
                    } else {
                        System.out.println("===== DANH SÁCH BÀN =====");
                        for (Table t : list) {
                            System.out.println(t);
                        }
                    }
                    break;

                // ================= DELETE =================
                case 4:
                    int deleteNumber;

                    while (true) {
                        try {
                            System.out.print("Input table number: ");
                            deleteNumber = Integer.parseInt(sc.nextLine());

                            tableService.getExistTable(deleteNumber);  // kiểm tra tồn tại
                            break;

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    try {
                        tableService.deleteTable(deleteNumber);
                        System.out.println("Xóa bàn thành công!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("Thoát...");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (choice != 5);
    }
}