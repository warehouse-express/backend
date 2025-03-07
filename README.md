# Warehouse Management System API Documentation

## Overview
This documentation provides comprehensive information about the Warehouse Management System REST API, a full-featured backend application that enables buyers and sellers to interact in a marketplace environment. The API provides endpoints for managing users (buyers and sellers), products, orders, and other core functionalities of an e-commerce platform.

## Table of Contents
1. [Technical Architecture](#technical-architecture)
2. [Database Structure](#database-structure)
3. [Authentication](#authentication)
4. [API Endpoints](#api-endpoints)
    - [User Management](#user-management)
    - [Product Management](#product-management)
    - [Order Management](#order-management)
5. [Data Models](#data-models)
6. [Error Handling](#error-handling)
7. [Development Setup](#development-setup)
8. [Production Deployment](#production-deployment)

## Technical Architecture

### Technology Stack
- **Backend Framework**: Spring Boot 3.2.3
- **Database (Development)**: H2 In-Memory Database
- **Database (Production)**: MySQL
- **Security**: Spring Security & JWT
- **Build Tool**: Maven
- **Java Version**: 17+
- **Documentation**: SpringDoc OpenAPI

### Project Structure
```
com.inventory
├── config/
│   └── SecurityConfig.java
├── controller/
│   ├── dto/
│   │   ├── BuyerDto.java
│   │   ├── SellerDto.java
│   │   ├── ProductDto.java
│   │   ├── OrderDto.java
│   │   └── OrderItemDto.java
│   ├── BuyerController.java
│   ├── SellerController.java
│   ├── ProductController.java
│   └── OrderController.java
├── exception/
│   ├── GlobalExceptionHandler.java
│   ├── UserNotFoundException.java
│   ├── UserAlreadyExistsException.java
│   ├── ProductNotFoundException.java
│   ├── OrderNotFoundException.java
│   └── InsufficientInventoryException.java
├── model/
│   ├── entity/
│   │   ├── BaseEntity.java
│   │   ├── User.java
│   │   ├── Buyer.java
│   │   ├── Seller.java
│   │   ├── Product.java
│   │   ├── Order.java
│   │   └── OrderItem.java
│   └── repository/
│       ├── UserRepository.java
│       ├── BuyerRepository.java
│       ├── SellerRepository.java
│       ├── ProductRepository.java
│       ├── OrderRepository.java
│       └── OrderItemRepository.java
├── service/
│   ├── UserService.java
│   ├── BuyerService.java
│   ├── SellerService.java
│   ├── ProductService.java
│   └── OrderService.java
└── WarehouseApplication.java
```

## Database Structure

### Entity Relationship Diagram

The system is built around the following core entities and relationships:

- **User**: Base class for all users in the system
    - **Buyer**: Extends User, represents customers who purchase products
    - **Seller**: Extends User, represents vendors who sell products

- **Product**: Represents items for sale, each belongs to a Seller

- **Order**: Represents a purchase made by a Buyer
    - **OrderItem**: Represents an item within an order, links to a Product

### Key Relationships
- One-to-Many: Seller to Products
- One-to-Many: Buyer to Orders
- Many-to-Many: Products to Orders (via OrderItems)

## Authentication

The system uses JWT (JSON Web Token) for authentication:

1. **Registration**: Users (buyers/sellers) register through dedicated endpoints
2. **Login**: Users receive a JWT token upon successful authentication
3. **Authorization**: All protected endpoints require a valid JWT token

For development, the security is configured to allow all requests without authentication. In production, proper JWT validation will be enforced.

## API Endpoints

### User Management

#### Buyers API

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| GET | `/api/buyers` | Get all buyers | - | Array of buyers |
| GET | `/api/buyers/{id}` | Get a specific buyer | - | Buyer object |
| POST | `/api/buyers` | Create a new buyer | BuyerDto | Created buyer |
| PUT | `/api/buyers/{id}` | Update a buyer | BuyerDto | Updated buyer |
| DELETE | `/api/buyers/{id}` | Delete a buyer | - | No content |

#### Sellers API

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| GET | `/api/sellers` | Get all sellers | - | Array of sellers |
| GET | `/api/sellers/{id}` | Get a specific seller | - | Seller object |
| POST | `/api/sellers` | Create a new seller | SellerDto | Created seller |
| PUT | `/api/sellers/{id}` | Update a seller | SellerDto | Updated seller |
| DELETE | `/api/sellers/{id}` | Delete a seller | - | No content |

### Product Management

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| GET | `/api/products` | Get all products | - | Array of products |
| GET | `/api/products/{id}` | Get a specific product | - | Product object |
| GET | `/api/products/seller/{sellerId}` | Get products by seller | - | Array of products |
| GET | `/api/products/category/{category}` | Get products by category | - | Array of products |
| POST | `/api/products` | Create a new product | ProductDto | Created product |
| PUT | `/api/products/{id}` | Update a product | ProductDto | Updated product |
| DELETE | `/api/products/{id}` | Delete a product | - | No content |

### Order Management

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| GET | `/api/orders` | Get all orders | - | Array of orders |
| GET | `/api/orders/{id}` | Get a specific order | - | Order object |
| GET | `/api/orders/number/{orderNumber}` | Get order by order number | - | Order object |
| GET | `/api/orders/buyer/{buyerId}` | Get orders by buyer | - | Array of orders |
| POST | `/api/orders` | Create a new order | OrderDto | Created order |
| PUT | `/api/orders/{id}/status` | Update order status | Status (enum) | Updated order |
| PUT | `/api/orders/{id}/tracking` | Update tracking info | Tracking number | Updated order |
| DELETE | `/api/orders/{id}/cancel` | Cancel an order | - | No content |

## Data Models

### BuyerDto
```json
{
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "password": "string",
  "shippingAddress": "string",
  "billingAddress": "string",
  "phoneNumber": "string"
}
```

### SellerDto
```json
{
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "password": "string",
  "companyName": "string",
  "companyDescription": "string",
  "contactPhone": "string",
  "businessAddress": "string",
  "taxId": "string"
}
```

### ProductDto
```json
{
  "name": "string",
  "description": "string",
  "price": 0.0,
  "quantity": 0,
  "imageUrl": "string",
  "category": "string",
  "sellerId": 0
}
```

### OrderDto
```json
{
  "buyerId": 0,
  "shippingAddress": "string",
  "items": [
    {
      "productId": 0,
      "quantity": 0
    }
  ]
}
```

## Error Handling

The API uses standard HTTP status codes and returns error responses in the following format:

```json
{
  "status": 404,
  "message": "User not found with id: 123",
  "timestamp": "2025-03-07T12:00:00.000"
}
```

### Common Error Status Codes

| Status Code | Description |
|-------------|-------------|
| 400 | Bad Request - Invalid input data |
| 401 | Unauthorized - Missing or invalid authentication |
| 403 | Forbidden - Insufficient permissions |
| 404 | Not Found - Resource not found |
| 409 | Conflict - Resource already exists (e.g., email already in use) |
| 500 | Internal Server Error - Unexpected server error |

## Development Setup

### Prerequisites
- Java 17+
- Maven
- Git

### Local Setup
1. Clone the repository
2. Navigate to the project root directory
3. Run `mvn clean install` to build the project
4. Run `mvn spring-boot:run` to start the application
5. The application will be available at http://localhost:8085
6. Access the H2 console at http://localhost:8085/h2-console
    - JDBC URL: `jdbc:h2:mem:warehousedb`
    - Username: `warehouse_user`
    - Password: `warehouse_password`

### Configuration Files
- **application.properties**: Main configuration file
- **application-dev.properties**: Development-specific configurations
- **application-prod.properties**: Production-specific configurations

### Profiles
- **dev**: Default development profile using H2 in-memory database
- **prod**: Production profile configured for MySQL

## Production Deployment

### Database Setup
1. Create a MySQL database named `warehousedb`
2. Update database credentials in `application-prod.properties`

### Environment Variables
Set the following environment variables for production:

- `MYSQL_HOST`: Database hostname
- `MYSQL_PORT`: Database port (default: 3306)
- `MYSQL_DB`: Database name
- `MYSQL_USER`: Database username
- `MYSQL_PASSWORD`: Database password
- `JWT_SECRET`: Secret key for JWT tokens

### Deployment Steps
1. Build the application with `mvn clean package`
2. Deploy the JAR file (`target/warehouse-0.0.1-SNAPSHOT.jar`)
3. Run with production profile: `java -jar warehouse-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod`

