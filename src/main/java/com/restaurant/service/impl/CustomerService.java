package com.restaurant.service.impl;

import com.restaurant.model.MenuItem;
import com.restaurant.model.Order;
import com.restaurant.model.OrderDetail;
import com.restaurant.model.User;
import com.restaurant.service.CustomerServiceInterface;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CustomerService implements CustomerServiceInterface {
    static int currentOrderId = 0;
    static int currentTableId = 0;

    static final MenuService menuService = new MenuService();
    static final OrderService orderService = new OrderService();
    static final TableService tableService = new TableService();
    @Override
    public void viewMenu() throws SQLException {
        System.out.println("\n===== DANH SACH MON AN =====");
        List<MenuItem> list = menuService.getAllDishes();

        if (list.isEmpty()) {
            System.out.println("Chua co mon an nao!");
        } else {
            System.out.println("ID  | Ten mon an           | Gia (VND)   | Danh muc     | Trang thai");
            System.out.println("----|----------------------|-------------|--------------|------------");
            for (MenuItem e : list) {
                System.out.printf("%-3d | %-20s | %,-12.0f | %-12s | %s\n",
                        e.getId(), e.getName(), e.getPrice(), e.getCategory(), e.getStatus());
            }
        }

        System.out.print("\nNhan Enter de tiep tuc...");
        new Scanner(System.in).nextLine();
    }

    @Override
    public void searchDishes(Scanner sc) throws SQLException {
        System.out.println("\n===== TIM KIEM MON AN =====");
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
                System.out.printf("%-3d | %-20s | %,-12.0f | %-12s | %s\n",
                        e.getId(), e.getName(), e.getPrice(), e.getCategory(), e.getStatus());
            }
        }

        System.out.print("\nNhan Enter de tiep tuc...");
        sc.nextLine();
    }

    @Override
    public void createNewOrder(Scanner sc, User user) throws SQLException {
        if (currentOrderId != 0) {
            System.out.println("\nBan dang co don hang chua thanh toan! Vui long thanh toan truoc.");
            System.out.print("\nNhan Enter de tiep tuc...");
            sc.nextLine();
            return;
        }

        System.out.println("\n===== TAO DON HANG MOI =====");

        System.out.println("Danh sach ban trong:");
        var availableTables = tableService.getAvailableTables();
        if (availableTables.isEmpty()) {
            System.out.println("Khong co ban trong!");
            System.out.print("\nNhan Enter de tiep tuc...");
            sc.nextLine();
            return;
        }

        for (var table : availableTables) {
            System.out.printf("Ban %d - Suc chua: %d nguoi\n",
                    table.getTableNumber(), table.getCapacity());
        }

        System.out.print("\nNhap so ban: ");
        try {
            int tableNumber = Integer.parseInt(sc.nextLine());
            var table = tableService.getExistTable(tableNumber);
            currentTableId = table.getId();

            Order order = new Order();
            order.setUserId(user.getId());
            order.setTableId(currentTableId);
            order.setTotalAmount(0);
            order.setStatus(Order.Status.PENDING);

            boolean result = orderService.createOrder(order);
            if (result) {
                currentOrderId = order.getId();
                System.out.println("Tao don hang thanh cong! Ma don hang: " + currentOrderId);
            } else {
                System.out.println("Tao don hang that bai!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Vui long nhap so hop le!");
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }

        System.out.print("\nNhan Enter de tiep tuc...");
        sc.nextLine();
    }

    @Override
    public void addDishesToOrder(Scanner sc, User user) throws SQLException {
        if (currentOrderId == 0) {
            System.out.println("\nBan chua tao don hang! Vui long tao don hang truoc.");
            System.out.print("\nNhan Enter de tiep tuc...");
            sc.nextLine();
            return;
        }

        System.out.println("\n===== THEM MON VAO DON HANG =====");

        // Hiển thị menu
        List<MenuItem> menu = menuService.getAllDishes();
        System.out.println("Danh sach mon an:");
        for (MenuItem item : menu) {
            if (item.getStatus().toString().equals("AVAILABLE")) {
                System.out.printf("%d. %s - %,.0f VND\n",
                        item.getId(), item.getName(), item.getPrice());
            }
        }

        try {
            System.out.print("\nNhap ID mon an: ");
            int dishId = Integer.parseInt(sc.nextLine());

            // Kiểm tra món tồn tại
            MenuItem dish = menuService.getExistDishes(dishId);
            if (dish.getStatus().toString().equals("UNAVAILABLE")) {
                System.out.println("Mon an nay hien khong kha dung!");
                System.out.print("\nNhan Enter de tiep tuc...");
                sc.nextLine();
                return;
            }

            System.out.print("Nhap so luong: ");
            int quantity = Integer.parseInt(sc.nextLine());

            if (quantity <= 0) {
                System.out.println("So luong phai lon hon 0!");
                System.out.print("\nNhan Enter de tiep tuc...");
                sc.nextLine();
                return;
            }

            // Thêm vào order detail
            OrderDetail detail = new OrderDetail();
            detail.setOrderId(currentOrderId);
            detail.setMenuItemId(dishId);
            detail.setQuantity(quantity);
            detail.setPrice(dish.getPrice().doubleValue());
            detail.setStatus(Order.Status.PENDING);

            boolean result = orderService.addOrderDetail(detail);
            if (result) {
                System.out.println("Them mon thanh cong!");
                updateOrderTotal();
            } else {
                System.out.println("Them mon that bai!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Vui long nhap so hop le!");
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }

        System.out.print("\nNhan Enter de tiep tuc...");
        sc.nextLine();
    }

    @Override
    public void viewCurrentOrder() throws SQLException {
        if (currentOrderId == 0) {
            System.out.println("\nBan chua co don hang nao!");
            System.out.print("\nNhan Enter de tiep tuc...");
            new Scanner(System.in).nextLine();
            return;
        }

        System.out.println("\n===== DON HANG HIEN TAI =====");
        List<OrderDetail> details = orderService.getOrderDetails(currentOrderId);

        if (details.isEmpty()) {
            System.out.println("Don hang trong!");
        } else {
            System.out.println("Ma don hang: " + currentOrderId);
            System.out.println("--------------------------------------------------");
            double total = 0;
            for (OrderDetail od : details) {
                System.out.printf("%-20s x %d = %,.0f VND [%s]\n",
                        od.getMenuItemName(), od.getQuantity(), od.getSubtotal(), od.getStatus());
                total += od.getSubtotal();
            }
            System.out.println("--------------------------------------------------");
            System.out.printf("TONG CONG: %,.0f VND\n", total);
        }

        System.out.print("\nNhan Enter de tiep tuc...");
        new Scanner(System.in).nextLine();
    }

    @Override
    public void updateOrderItemQuantity(Scanner sc, User user) throws SQLException {
        if (currentOrderId == 0) {
            System.out.println("\nBan chua co don hang nao!");
            System.out.print("\nNhan Enter de tiep tuc...");
            sc.nextLine();
            return;
        }

        System.out.println("\n===== CAP NHAT SO LUONG MON =====");
        viewCurrentOrderItems();

        try {
            System.out.print("\nNhap ID chi tiet don hang: ");
            int detailId = Integer.parseInt(sc.nextLine());

            OrderDetail detail = orderService.getOrderDetailById(detailId);
            if (detail == null || detail.getOrderId() != currentOrderId) {
                System.out.println("Khong tim thay chi tiet don hang!");
                System.out.print("\nNhan Enter de tiep tuc...");
                sc.nextLine();
                return;
            }

            System.out.print("Nhap so luong moi: ");
            int newQuantity = Integer.parseInt(sc.nextLine());

            if (newQuantity <= 0) {
                System.out.println("So luong phai lon hon 0!");
                System.out.print("\nNhan Enter de tiep tuc...");
                sc.nextLine();
                return;
            }

            boolean result = orderService.updateOrderDetailQuantity(detailId, newQuantity);
            if (result) {
                System.out.println("Cap nhat thanh cong!");
                updateOrderTotal();
            } else {
                System.out.println("Cap nhat that bai!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Vui long nhap so hop le!");
        }

        System.out.print("\nNhan Enter de tiep tuc...");
        sc.nextLine();
    }

    @Override
    public void removeOrderItem(Scanner sc, User user) throws SQLException {
        if (currentOrderId == 0) {
            System.out.println("\nBan chua co don hang nao!");
            System.out.print("\nNhan Enter de tiep tuc...");
            sc.nextLine();
            return;
        }

        System.out.println("\n===== XOA MON KHOI DON HANG =====");
        viewCurrentOrderItems();

        try {
            System.out.print("\nNhap ID chi tiet don hang can xoa: ");
            int detailId = Integer.parseInt(sc.nextLine());

            OrderDetail detail = orderService.getOrderDetailById(detailId);
            if (detail == null || detail.getOrderId() != currentOrderId) {
                System.out.println("Khong tim thay chi tiet don hang!");
                System.out.print("\nNhan Enter de tiep tuc...");
                sc.nextLine();
                return;
            }

            System.out.print("Ban co chac chan muon xoa mon nay? (y/n): ");
            String confirm = sc.nextLine();

            if (confirm.equalsIgnoreCase("y")) {
                boolean result = orderService.deleteOrderDetail(detailId);
                if (result) {
                    System.out.println("Xoa mon thanh cong!");
                    updateOrderTotal();

                    // Kiểm tra nếu order rỗng thì reset
                    List<OrderDetail> remaining = orderService.getOrderDetails(currentOrderId);
                    if (remaining.isEmpty()) {
                        orderService.updateOrderStatus(currentOrderId, Order.Status.CANCELLED);
                        currentOrderId = 0;
                        currentTableId = 0;
                        System.out.println("Don hang trong nen da bi huy!");
                    }
                } else {
                    System.out.println("Xoa mon that bai!");
                }
            } else {
                System.out.println("Huy thao tac!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Vui long nhap so hop le!");
        }

        System.out.print("\nNhan Enter de tiep tuc...");
        sc.nextLine();
    }

    @Override
    public void checkout(User user) throws SQLException {
        if (currentOrderId == 0) {
            System.out.println("\nBan chua co don hang nao!");
            System.out.print("\nNhan Enter de tiep tuc...");
            new Scanner(System.in).nextLine();
            return;
        }

        System.out.println("\n===== THANH TOAN =====");
        List<OrderDetail> details = orderService.getOrderDetails(currentOrderId);

        if (details.isEmpty()) {
            System.out.println("Don hang trong! Khong the thanh toan.");
            System.out.print("\nNhan Enter de tiep tuc...");
            new Scanner(System.in).nextLine();
            return;
        }

        double total = 0;
        System.out.println("Chi tiet don hang:");
        for (OrderDetail od : details) {
            System.out.printf("%-20s x %d = %,.0f VND\n",
                    od.getMenuItemName(), od.getQuantity(), od.getSubtotal());
            total += od.getSubtotal();
        }
        System.out.println("--------------------------------------------------");
        System.out.printf("TONG TIEN: %,.0f VND\n", total);

        System.out.print("\nXac nhan thanh toan? (y/n): ");
        Scanner sc = new Scanner(System.in);
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("y")) {
            boolean result = orderService.updateOrderStatus(currentOrderId, Order.Status.PAID);
            if (result) {
                System.out.println("Thanh toan thanh cong! Cam on quy khach!");
                currentOrderId = 0;
                currentTableId = 0;
            } else {
                System.out.println("Thanh toan that bai!");
            }
        } else {
            System.out.println("Huy thanh toan!");
        }

        System.out.print("\nNhan Enter de tiep tuc...");
        sc.nextLine();
    }

    @Override
    public void viewOrderHistory(User user) throws SQLException {
        System.out.println("\n===== LICH SU DON HANG =====");
        List<Order> orders = orderService.getOrdersByUserId(user.getId());

        if (orders.isEmpty()) {
            System.out.println("Ban chua co don hang nao!");
        } else {
            System.out.println("ID  | Ban | Tong tien     | Trang thai | Ngay tao");
            System.out.println("----|-----|---------------|------------|------------------");
            for (Order o : orders) {
                System.out.printf("%-3d | %-3d | %,-12.0f | %-10s | %s\n",
                        o.getId(), o.getTableId(), o.getTotalAmount(),
                        o.getStatus(), o.getCreatedAt());
            }
        }

        System.out.print("\nNhap ID don hang de xem chi tiet (0 de bo qua): ");
        Scanner sc = new Scanner(System.in);
        try {
            int orderId = Integer.parseInt(sc.nextLine());
            if (orderId > 0) {
                viewOrderDetail(orderId);
            }
        } catch (NumberFormatException e) {
            System.out.println("Vui long nhap so!");
        }

        System.out.print("\nNhan Enter de tiep tuc...");
        sc.nextLine();
    }

    @Override
    public void viewOrderDetail(int orderId) throws SQLException {
        List<OrderDetail> details = orderService.getOrderDetails(orderId);

        if (details.isEmpty()) {
            System.out.println("Khong co chi tiet don hang!");
        } else {
            System.out.println("\n----- CHI TIET DON HANG #" + orderId + " -----");
            double total = 0;
            for (OrderDetail od : details) {
                System.out.printf("%-20s x %d = %,.0f VND [%s]\n",
                        od.getMenuItemName(), od.getQuantity(), od.getSubtotal(), od.getStatus());
                total += od.getSubtotal();
            }
            System.out.println("--------------------------------------------------");
            System.out.printf("TONG CONG: %,.0f VND\n", total);
        }
    }

    @Override
    public void viewCurrentOrderItems() throws SQLException {
        List<OrderDetail> details = orderService.getOrderDetails(currentOrderId);
        if (details.isEmpty()) {
            System.out.println("Don hang trong!");
        } else {
            System.out.println("Cac mon trong don hang:");
            for (OrderDetail od : details) {
                System.out.printf("ID: %d | %s - So luong: %d - Thanh tien: %,.0f VND\n",
                        od.getId(), od.getMenuItemName(), od.getQuantity(), od.getSubtotal());
            }
        }
    }

    @Override
    public void updateOrderTotal() throws SQLException {
        List<OrderDetail> details = orderService.getOrderDetails(currentOrderId);
        double total = 0;
        for (OrderDetail od : details) {
            total += od.getSubtotal();
        }
        orderService.updateOrderTotal(currentOrderId, total);
    }
}
