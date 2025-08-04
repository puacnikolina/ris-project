# Development of Information Systems Project

This Spring Boot application is developed as part of the **"Development of Information Systems"** project. The primary goal is to showcase a web application built using the Spring Boot framework with a connection to a relational database using the Java Persistence API (JPA).

The application concept is designed as a **Music store** 💿🎧.

## Architecture & Technologies

- **Spring Boot 3.5.4** with Java 21
- **Spring Security** with role-based authentication (ADMIN/USER roles)
- **Spring Data JPA** for database operations
- **MySQL** database
- **JSP** for the view layer
- **JasperReports** for PDF generation
- **Maven** for dependency management

## Features Implemented

1. **User Management**: Registration, login, role-based access control
2. **Product Catalog**: CRUD operations for products with image upload support
3. **Shopping Cart**: Session-based cart functionality
4. **Order Management**: Order processing, status updates, and delivery date calculation
5. **Wishlist**: User wishlist functionality
6. **Admin Panel**: Product management, user management, and order overview
7. **Statistics**: Revenue tracking, user/product/order counts
8. **PDF Reports**: Order receipt generation using JasperReports
9. **Artist & Category Management**: Music store specific features
10. **File Upload**: Image handling for products

## EER Diagram

<img width="979" height="657" alt="RIS_ProjectModel" src="https://github.com/user-attachments/assets/a08063dd-0f15-4514-b7b9-254d1ff02102" />

## Database Setup

To set up the initial data in the database, add the following rows to the `role` and `category` tables:

INSERT INTO role (idRole, name) VALUES
(1, 'ADMIN'),
(2, 'USER');

INSERT INTO category (idCategory, name) VALUES
(1, 'CD'),
(2, 'Vinyl'),
(3, 'Tape');

## Notes

- This project currently does **not** include CSS styling or advanced UI design.
- The application can be further extended and improved in the future with enhanced UI/UX, additional features, and performance optimizations.
