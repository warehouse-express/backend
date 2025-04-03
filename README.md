# Warehouse Management System - Backend

## Overview
This is the backend API for a Warehouse Management System built with Spring Boot. It provides RESTful endpoints for managing products, sellers, buyers, and orders in a marketplace environment.

## Technology Stack
- **Java 17**
- **Spring Boot 3.2.3**
- **Spring Data JPA**
- **Spring Security**
- **H2 Database** 
- **Maven**

## Setup and Installation

### Prerequisites
- JDK 17 or higher
- Maven 3.8+
- MySQL (for production only)

### Steps
1. Clone the repository
   ```
   git clone https://your-repository-url.git
   cd warehouse-backend
   ```

2. Configure database settings
    - For development, the H2 in-memory database is pre-configured

3. Build the project
   ```
   mvn clean install
   ```

4. Run the application
   ```
   mvn spring-boot:run
   ```
    - The API will be available at http://localhost:8085
    - The H2 console will be available at http://localhost:8085/h2-console (for dev profile)
    - The API documentation will be available at http://localhost:8085/swagger-ui.html

## Project Structure

### Key Packages
- `com.inventory.controller` - REST controllers exposing the API endpoints
- `com.inventory.service` - Business logic services
- `com.inventory.model` - Entity models and repositories
- `com.inventory.exception` - Custom exception handlers
- `com.inventory.config` - Configuration classes

### Database Model
The system uses the following entities:
- **User** - Base entity for all users
- **Buyer** - Customer who can place orders
- **Seller** - Merchant who can list products
- **Product** - Items available for purchase
- **Order** - Record of purchases
- **OrderItem** - Line items within orders

## API Endpoints

### Products
- `GET /api/products` - List all products
- `GET /api/products/{id}` - Get product by ID
- `GET /api/products/seller/{sellerId}` - Get products by seller
- `GET /api/products/category/{category}` - Get products by category
- `POST /api/products` - Create a new product
- `PUT /api/products/{id}` - Update a product
- `DELETE /api/products/{id}` - Delete a product

### Sellers
- `GET /api/sellers` - List all sellers
- `GET /api/sellers/{id}` - Get seller by ID
- `GET /api/sellers/{id}/with-products` - Get seller with products
- `POST /api/sellers` - Create a new seller
- `PUT /api/sellers/{id}` - Update a seller
- `DELETE /api/sellers/{id}` - Delete a seller

### Buyers
- `GET /api/buyers` - List all buyers
- `GET /api/buyers/{id}` - Get buyer by ID
- `GET /api/buyers/{id}/with-orders` - Get buyer with orders
- `POST /api/buyers` - Create a new buyer
- `PUT /api/buyers/{id}` - Update a buyer
- `DELETE /api/buyers/{id}` - Delete a buyer

### Orders
- `GET /api/orders` - List all orders
- `GET /api/orders/{id}` - Get order by ID
- `GET /api/orders/number/{orderNumber}` - Get order by order number
- `GET /api/orders/buyer/{buyerId}` - Get orders by buyer
- `POST /api/orders` - Create a new order
- `PUT /api/orders/{id}/status` - Update order status
- `PUT /api/orders/{id}/tracking` - Update tracking information
- `DELETE /api/orders/{id}/cancel` - Cancel an order

## Security
The system uses Spring Security for authentication and authorization. For demonstration purposes, all API endpoints are publicly accessible in the current configuration.

## Testing
Run tests with Maven:
```
mvn test
```

## Data Initialization
Sample data is automatically loaded from `data.sql` when running with the development profile.

## Contributing
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/your-feature`)
5. Create a new Pull Request