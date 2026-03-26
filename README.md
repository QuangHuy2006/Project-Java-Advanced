# Restaurant Management System - Project Structure
```text
RestaurantManagement/
│
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ ├── com/restaurant/
│ │ │ │ ├── Main.java # Entry point
│ │ │ │ │
│ │ │ │ ├── model/ # Entity classes
│ │ │ │ │ ├── User.java
│ │ │ │ │ ├── Role.java # Enum: MANAGER, CHEF, CUSTOMER
│ │ │ │ │ ├── MenuItem.java
│ │ │ │ │ ├── Table.java
│ │ │ │ │ ├── Order.java
│ │ │ │ │ ├── OrderDetail.java
│ │ │ │ │ ├── Review.java
│ │ │ │ │ └── Invoice.java
│ │ │ │ │
│ │ │ │ ├── dao/ # Data Access Objects
│ │ │ │ │ ├── UserDAO.java
│ │ │ │ │ ├── MenuItemDAO.java
│ │ │ │ │ ├── TableDAO.java
│ │ │ │ │ ├── OrderDAO.java
│ │ │ │ │ ├── OrderDetailDAO.java
│ │ │ │ │ ├── ReviewDAO.java
│ │ │ │ │ └── InvoiceDAO.java
│ │ │ │ │
│ │ │ │ ├── service/ # Business logic
│ │ │ │ │ ├── AuthService.java
│ │ │ │ │ ├── MenuService.java
│ │ │ │ │ ├── TableService.java
│ │ │ │ │ ├── OrderService.java
│ │ │ │ │ ├── ChefService.java
│ │ │ │ │ ├── InvoiceService.java
│ │ │ │ │ └── ReviewService.java
│ │ │ │ │
│ │ │ │ ├── controller/ # Handle user input
│ │ │ │ │ ├── AuthController.java
│ │ │ │ │ ├── ManagerController.java
│ │ │ │ │ ├── CustomerController.java
│ │ │ │ │ └── ChefController.java
│ │ │ │ │
│ │ │ │ ├── view/ # UI/Console display
│ │ │ │ │ ├── MainMenu.java
│ │ │ │ │ ├── AuthView.java
│ │ │ │ │ ├── ManagerView.java
│ │ │ │ │ ├── CustomerView.java
│ │ │ │ │ └── ChefView.java
│ │ │ │ │
│ │ │ │ ├── utils/ # Utility classes
│ │ │ │ │ ├── DatabaseConnection.java
│ │ │ │ │ ├── PasswordUtils.java # Hash password
│ │ │ │ │ ├── InputValidator.java
│ │ │ │ │ ├── TableFormatter.java # Format table display
│ │ │ │ │ └── Constants.java
│ │ │ │ │
│ │ │ │ └── enums/ # Enum types
│ │ │ │ ├── OrderStatus.java # PENDING, COOKING, READY, SERVED
│ │ │ │ ├── TableStatus.java # AVAILABLE, OCCUPIED
│ │ │ │ ├── ItemType.java # FOOD, DRINK
│ │ │ │ └── UserStatus.java # ACTIVE, INACTIVE
│ │ │ │
│ │ └── resources/
│ │ ├── db/
│ │ │ └── schema.sql # Database schema
│ │ ├── config/
│ │ │ └── db.properties # Database config
│ │ └── log4j.properties # Logging config
│ │
│ └── test/ # Unit tests
│ └── java/
│ └── com/restaurant/
│ ├── dao/
│ └── service/
│
├── lib/ # External libraries
│ └── mysql-connector-java-8.0.33.jar
│
├── database/
│ └── init.sql # Initial data
│
├── README.md
└── .gitignore
