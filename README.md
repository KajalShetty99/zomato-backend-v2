
# Food Delivery Backend System (Zomato/Swiggy-inspired)

A backend system for a food delivery platform, built to replicate the core
functionality of apps like Zomato and Swiggy — user and restaurant management,
menus, cart, orders, coupons, and wallet-based transactions.

## Tech stack

- **Java** with **Spring Boot**
- **MySQL** for persistent storage
- **Spring Data JPA** for the data access layer
- **Postman** for API testing

## Features

- User registration and login
- Restaurant registration
- Menu management (add/view items)
- Cart (add/update/remove items)
- Place order
- Cancel order (with wallet refund)
- Coupon system (discount codes with expiry and minimum order value)
- Favourite restaurants
- User wallet (balance, refunds)
- Restaurant transactions (order settlements)

## Architecture

The project follows a standard layered architecture:

Client (Postman)
|
Controller layer   -> handles HTTP requests
|
Service layer      -> business logic (coupon rules, wallet updates, validations)
|
Repository layer   -> Spring Data JPA interfaces
|
MySQL database

## Database design

Core entities: `User`, `Restaurant`, `Menu`, `Cart`, `Orders`, `OrderItem`,
`Coupon`, `Favourite`, `Wallet`, and `RestaurantTransaction`.

Key relationships:
- A user has one wallet, many cart items, many favourite restaurants, and many orders
- A restaurant has many menu items, receives many orders, and generates many transactions
- An order contains many order items and optionally applies one coupon

## Getting started

### Prerequisites
- Java 17+
- Maven
- MySQL

### Setup
1. Clone the repository
 git clone https://github.com/KajalShetty99/zomato-backend-v2.git
2.. Create a MySQL database and update the credentials in
   `src/main/resources/application.properties`
3. Run the application
 ./mvnw spring-boot:run

4. The API will be available at `http://localhost:8080`

## API testing

All endpoints were tested using Postman. Sample request/response pairs are
available in the `/postman` folder (if included) or can be reconstructed from
the controller classes under `src/main/java/com/zomato/zomato/controller`.

## Roadmap / possible next steps

- JWT-based authentication and role-based authorization
- Delivery partner module with order assignment
- Order status tracking as a proper state machine
- Ratings and reviews for restaurants
- Admin module for restaurant approval and analytics

## Author

**Kajal Shetty**
