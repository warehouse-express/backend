-- Clear existing data (optional, useful during development)
-- DELETE FROM order_items;
-- DELETE FROM orders;
-- DELETE FROM products;
-- DELETE FROM sellers;
-- DELETE FROM buyers;
-- DELETE FROM users;

-- Insert Users (Buyers)
INSERT INTO users (id, first_name, last_name, email, password, role, active, created_at, updated_at)
VALUES (1001, 'John', 'Doe', 'john.doe@example.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'BUYER', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO users (id, first_name, last_name, email, password, role, active, created_at, updated_at)
VALUES (1002, 'Jane', 'Smith', 'jane.smith@example.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'BUYER', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Insert Buyers
INSERT INTO buyers (user_id, shipping_address, billing_address, phone_number)
VALUES (1001, '123 Main St, City, Country', '123 Main St, City, Country', '+1234567890');

INSERT INTO buyers (user_id, shipping_address, billing_address, phone_number)
VALUES (1002, '456 Oak St, City, Country', '456 Oak St, City, Country', '+0987654321');

-- Insert Users (Sellers)
INSERT INTO users (id, first_name, last_name, email, password, role, active, created_at, updated_at)
VALUES (2001, 'Bob', 'Johnson', 'bob@company.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'SELLER', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO users (id, first_name, last_name, email, password, role, active, created_at, updated_at)
VALUES (2002, 'Alice', 'Williams', 'alice@company.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'SELLER', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Insert Sellers
INSERT INTO sellers (user_id, company_name, company_description, contact_phone, business_address, tax_id)
VALUES (2001, 'Tech Solutions Inc.', 'Provider of high-quality tech products', '+1122334455', '789 Business Ave, City, Country', 'TX12345678');

INSERT INTO sellers (user_id, company_name, company_description, contact_phone, business_address, tax_id)
VALUES (2002, 'Home Essentials Ltd.', 'Quality home products for everyday use', '+5566778899', '101 Commerce St, City, Country', 'TX87654321');

-- Insert Products
-- Auto assign IDs to prevent conflicts when creating IDs
INSERT INTO products (name, description, price, quantity, image_url, category, seller_id, status, created_at, updated_at)
VALUES ('Laptop Pro X', 'High-performance laptop with 16GB RAM and 512GB SSD', 1299.99, 15, 'laptop.jpg', 'Electronics', 2001, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, image_url, category, seller_id, status, created_at, updated_at)
VALUES ('Smartphone Z20', 'Latest smartphone with 128GB storage and triple camera', 899.99, 25, 'smartphone.jpg', 'Electronics', 2001, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, image_url, category, seller_id, status, created_at, updated_at)
VALUES ('Coffee Maker Deluxe', 'Programmable coffee maker with thermal carafe', 89.99, 30, 'coffee-maker.jpg', 'Home Appliances', 2002, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, image_url, category, seller_id, status, created_at, updated_at)
VALUES ('Kitchen Mixer', 'Professional grade stand mixer with multiple attachments', 199.99, 12, 'mixer.jpg', 'Home Appliances', 2002, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, image_url, category, seller_id, status, created_at, updated_at)
VALUES ('Wireless Headphones', 'Noise-cancelling bluetooth headphones with 30-hour battery life', 149.99, 20, 'headphones.jpg', 'Electronics', 2001, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Insert Orders
INSERT INTO orders (id, order_number, buyer_id, total_amount, status, placed_at, shipping_address, created_at, updated_at)
VALUES (1, 'ORD10001', 1001, 1299.99, 'DELIVERED', '2023-01-15 10:30:00', '123 Main St, City, Country', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO orders (id, order_number, buyer_id, total_amount, status, placed_at, shipping_address, created_at, updated_at)
VALUES (2, 'ORD10002', 1002, 289.98, 'SHIPPED', '2023-02-20 14:45:00', '456 Oak St, City, Country', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO orders (id, order_number, buyer_id, total_amount, status, placed_at, shipping_address, created_at, updated_at)
VALUES (3, 'ORD10003', 1001, 899.99, 'PENDING', '2023-03-05 09:15:00', '123 Main St, City, Country', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Insert Order Items
INSERT INTO order_items (id, order_id, product_id, quantity, price, product_name, product_description, created_at, updated_at)
VALUES (1, 1, 1, 1, 1299.99, 'Laptop Pro X', 'High-performance laptop with 16GB RAM and 512GB SSD', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO order_items (id, order_id, product_id, quantity, price, product_name, product_description, created_at, updated_at)
VALUES (2, 2, 3, 2, 89.99, 'Coffee Maker Deluxe', 'Programmable coffee maker with thermal carafe', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO order_items (id, order_id, product_id, quantity, price, product_name, product_description, created_at, updated_at)
VALUES (3, 2, 4, 0.5, 199.99, 'Kitchen Mixer', 'Professional grade stand mixer with multiple attachments', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO order_items (id, order_id, product_id, quantity, price, product_name, product_description, created_at, updated_at)
VALUES (4, 3, 2, 1, 899.99, 'Smartphone Z20', 'Latest smartphone with 128GB storage and triple camera', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());