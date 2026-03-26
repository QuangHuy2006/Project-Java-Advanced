# Restaurant Management System - Project Structure
```text
RestaurantManagement/
│
├── src/
│   └── com/
│       └── restaurant/
│           ├── Main.java
│           │
│           ├── model/
│           │   ├── User.java
│           │   ├── MenuItem.java
│           │   ├── Table.java
│           │   ├── Order.java
│           │   └── OrderDetail.java
│           │
│           ├── dao/
│           │   ├── UserDAO.java
│           │   ├── MenuItemDAO.java
│           │   ├── TableDAO.java
│           │   ├── OrderDAO.java
│           │   └── OrderDetailDAO.java
│           │
│           ├── service/
│           │   ├── AuthService.java
│           │   ├── MenuService.java
│           │   ├── TableService.java
│           │   ├── OrderService.java
│           │   └── ChefService.java
│           │
│           └── utils/
│               ├── DatabaseConnection.java
│               └── Validator.java
└── README.md
