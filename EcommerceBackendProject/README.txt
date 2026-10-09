E-COMMERCE BACKEND

This is the backend version of the simple E-Commerce application.

The old console program had:
- View products
- Buy product
- UPI
- Cash on delivery
- Card
- Track order
- Give stars/review

This project keeps the same idea but uses Spring Boot REST API,
MySQL and Hibernate.

TECHNOLOGY
Java 21
Spring Boot
Spring Web
Spring Data JPA
Hibernate
MySQL
Maven

PROJECT STRUCTURE

EcommerceBackendApplication
        |
        +-- controller
        |     ProductController
        |     OrderController
        |
        +-- service
        |     ProductService
        |     OrderService
        |
        +-- repository
        |     ProductRepository
        |     OrderRepository
        |
        +-- entity
              Product
              Order

DATABASE

Create database:

CREATE DATABASE ecommerce;

Open application.properties and change:

spring.datasource.password=YOUR_PASSWORD

to your MySQL password.

POSTMAN

1. Add product

POST http://localhost:8080/products

Body -> raw -> JSON

{
    "name": "Oppo Reno",
    "category": "Mobile",
    "price": 30000,
    "quantity": 10,
    "discount": 5,
    "rating": 0
}

You can add these also:

{
    "name": "iPhone 17 Pro",
    "category": "Mobile",
    "price": 100000,
    "quantity": 5,
    "discount": 0,
    "rating": 0
}

{
    "name": "Samsung S24 Ultra",
    "category": "Mobile",
    "price": 120000,
    "quantity": 4,
    "discount": 10,
    "rating": 0
}

2. View all products

GET http://localhost:8080/products

3. View one product

GET http://localhost:8080/products/1

4. Buy a product

POST

http://localhost:8080/orders/buy?productId=1&quantity=1&payment=UPI&address=Chennai

Payment can be:
UPI
CARD
COD

5. View orders

GET http://localhost:8080/orders

6. View one order

GET http://localhost:8080/orders/1

7. Track order

GET http://localhost:8080/orders/1/track

8. Give delivery stars

POST

http://localhost:8080/orders/1/rating?rating=5

9. Give product rating

POST

http://localhost:8080/products/1/rating?rating=4

NOTE

The payment is only a demo. Do not enter real bank account numbers,
UPI PINs or card details.

The code is intentionally kept simple so it is easy to understand
and explain in a college project or interview.
