
-- Generated Synthetic Data

-- Insert Users (Buyers)
INSERT INTO users (id, first_name, last_name, email, password, role, active, created_at, updated_at)
VALUES (1001, 'John', 'Doe', 'john.doe@example.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'BUYER', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO users (id, first_name, last_name, email, password, role, active, created_at, updated_at)
VALUES (1002, 'Jane', 'Smith', 'jane.smith@example.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'BUYER', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO users (id, first_name, last_name, email, password, role, active, created_at, updated_at)
VALUES (1003, 'Kyan', 'Nassouti', 'kyan.nassouti@example.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'BUYER', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO users (id, first_name, last_name, email, password, role, active, created_at, updated_at)
VALUES (1004, 'Jad', 'Haidar', 'jad.haidar@example.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'BUYER', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO users (id, first_name, last_name, email, password, role, active, created_at, updated_at)
VALUES (1005, 'William', 'Liu', 'william.liu@example.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'BUYER', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO users (id, first_name, last_name, email, password, role, active, created_at, updated_at)
VALUES (1006, 'Michael', 'Abou Zeid', 'michael.abouzeid@example.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'BUYER', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Insert Buyers
INSERT INTO buyers (user_id, shipping_address, billing_address, phone_number)
VALUES (1001, '123 Main St, City, Country', '123 Main St, City, Country', '+1234567890');

INSERT INTO buyers (user_id, shipping_address, billing_address, phone_number)
VALUES (1002, '456 Oak St, City, Country', '456 Oak St, City, Country', '+0987654321');

INSERT INTO buyers (user_id, shipping_address, billing_address, phone_number)
VALUES (1003, '789 Pine St, New York, USA', '789 Pine St, New York, USA', '+1122334455');

INSERT INTO buyers (user_id, shipping_address, billing_address, phone_number)
VALUES (1004, '321 Cedar Ave, Paris, France', '321 Cedar Ave, Paris, France', '+3344556677');

INSERT INTO buyers (user_id, shipping_address, billing_address, phone_number)
VALUES (1005, '654 Maple Rd, Toronto, Canada', '654 Maple Rd, Toronto, Canada', '+4455667788');

INSERT INTO buyers (user_id, shipping_address, billing_address, phone_number)
VALUES (1006, '987 Birch Blvd, Sydney, Australia', '987 Birch Blvd, Sydney, Australia', '+5566778899');

-- Insert Users (Sellers)
INSERT INTO users (id, first_name, last_name, email, password, role, active, created_at, updated_at)
VALUES (2001, 'Bob', 'Johnson', 'bob@company.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'SELLER', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO users (id, first_name, last_name, email, password, role, active, created_at, updated_at)
VALUES (2002, 'Alice', 'Williams', 'alice@company.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'SELLER', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO users (id, first_name, last_name, email, password, role, active, created_at, updated_at)
VALUES (2003, 'Boran', 'Dal', 'boran.dal@business.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'SELLER', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO users (id, first_name, last_name, email, password, role, active, created_at, updated_at)
VALUES (2004, 'Eren', 'Solak', 'eren.solak@business.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'SELLER', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO users (id, first_name, last_name, email, password, role, active, created_at, updated_at)
VALUES (2005, 'Oguzhan', 'Cakir', 'oguzhan.cakir@business.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'SELLER', true, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Insert Sellers
INSERT INTO sellers (user_id, company_name, company_description, contact_phone, business_address, tax_id)
VALUES (2001, 'Tech Solutions Inc.', 'Provider of high-quality tech products', '+1122334455', '789 Business Ave, City, Country', 'TX12345678');

INSERT INTO sellers (user_id, company_name, company_description, contact_phone, business_address, tax_id)
VALUES (2002, 'Home Essentials Ltd.', 'Quality home products for everyday use', '+5566778899', '101 Commerce St, City, Country', 'TX87654321');

INSERT INTO sellers (user_id, company_name, company_description, contact_phone, business_address, tax_id)
VALUES (2003, 'Boran''s Sporting Goods', 'Premium sports equipment and athletic wear', '+9988776655', '222 Sports Blvd, Berlin, Germany', 'TX98765432');

INSERT INTO sellers (user_id, company_name, company_description, contact_phone, business_address, tax_id)
VALUES (2004, 'Eren''s Fashion Outlet', 'Trendy and affordable fashion for all seasons', '+8877665544', '333 Fashion Ave, Milan, Italy', 'TX76543210');

INSERT INTO sellers (user_id, company_name, company_description, contact_phone, business_address, tax_id)
VALUES (2005, 'Oguzhan''s Gourmet Foods', 'Specialty food products from around the world', '+7766554433', '444 Culinary Way, Istanbul, Turkey', 'TX65432109');

-- Insert Products
-- Electronics
INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Laptop Pro X', 'High-performance laptop with 16GB RAM and 512GB SSD', 1299.99, 15, 'Electronics', 2001, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Smartphone Z20', 'Latest smartphone with 128GB storage and triple camera', 899.99, 25, 'Electronics', 2001, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Wireless Headphones', 'Noise-cancelling bluetooth headphones with 30-hour battery life', 149.99, 20, 'Electronics', 2001, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Ultra HD Smart TV', '65-inch 4K display with smart connectivity', 1499.99, 10, 'Electronics', 2001, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Wireless Gaming Mouse', 'Ergonomic design with programmable buttons', 79.99, 30, 'Electronics', 2001, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Home Appliances
INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Coffee Maker Deluxe', 'Programmable coffee maker with thermal carafe', 89.99, 30, 'Home Appliances', 2002, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Kitchen Mixer', 'Professional grade stand mixer with multiple attachments', 199.99, 12, 'Home Appliances', 2002, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Air Purifier', 'HEPA filtration system for cleaner air', 249.99, 15, 'Home Appliances', 2002, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Smart Thermostat', 'Energy-efficient temperature control system', 129.99, 20, 'Home Appliances', 2002, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Robot Vacuum', 'Automated cleaning with smart mapping technology', 349.99, 8, 'Home Appliances', 2002, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Sports Equipment
INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Professional Basketball', 'Competition-grade indoor/outdoor basketball', 59.99, 35, 'Sports', 2003, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Carbon Fiber Tennis Racket', 'Lightweight design for enhanced play', 189.99, 15, 'Sports', 2003, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Yoga Mat Premium', 'Extra thick non-slip surface for comfortable practice', 45.99, 40, 'Sports', 2003, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Mountain Bike', 'All-terrain bicycle with 21 speeds', 699.99, 7, 'Sports', 2003, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Fitness Tracker', 'Monitors heart rate, steps, and sleep quality', 129.99, 25, 'Sports', 2003, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Fashion
INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Designer Jeans', 'Premium denim with modern fit', 89.99, 45, 'Fashion', 2004, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Winter Coat', 'Waterproof and insulated for extreme cold', 199.99, 20, 'Fashion', 2004, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Leather Boots', 'Handcrafted genuine leather with comfort insole', 159.99, 15, 'Fashion', 2004, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Silk Dress Shirt', 'Formal attire for special occasions', 79.99, 30, 'Fashion', 2004, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Canvas Sneakers', 'Casual footwear for everyday use', 49.99, 50, 'Fashion', 2004, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Gourmet Foods
INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Organic Olive Oil', 'Extra virgin olive oil from Mediterranean groves', 24.99, 40, 'Food', 2005, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Truffle Collection', 'Assorted chocolate truffles in gift box', 39.99, 25, 'Food', 2005, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Aged Balsamic Vinegar', '25-year aged traditional balsamic', 59.99, 15, 'Food', 2005, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Specialty Coffee Beans', 'Single-origin coffee beans, freshly roasted', 19.99, 35, 'Food', 2005, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO products (name, description, price, quantity, category, seller_id, status, created_at, updated_at)
VALUES ('Artisanal Cheese Selection', 'Premium cheese assortment from local farms', 44.99, 20, 'Food', 2005, 'ACTIVE', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Insert Orders
INSERT INTO orders (order_number, buyer_id, total_amount, status, placed_at, shipping_address, tracking_number, created_at, updated_at)
VALUES ('64EC64D40D', 1001, 1299.99, 'DELIVERED', '2023-01-15 10:30:00', '123 Main St, City, Country', 'B3212349', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO orders (order_number, buyer_id, total_amount, status, placed_at, shipping_address, tracking_number, created_at, updated_at)
VALUES ('7291B37160', 1002, 289.98, 'SHIPPED', '2023-02-20 14:45:00', '456 Oak St, City, Country', 'L32592398', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO orders (order_number, buyer_id, total_amount, status, placed_at, shipping_address, tracking_number, created_at, updated_at)
VALUES ('6D13CD88AD', 1001, 899.99, 'PENDING', '2023-03-05 09:15:00', '123 Main St, City, Country', 'N23120937', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO orders (order_number, buyer_id, total_amount, status, placed_at, shipping_address, tracking_number, created_at, updated_at)
VALUES ('AB12CD34EF', 1003, 1649.98, 'PROCESSING', '2023-04-10 11:20:00', '789 Pine St, New York, USA', NULL, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO orders (order_number, buyer_id, total_amount, status, placed_at, shipping_address, tracking_number, created_at, updated_at)
VALUES ('GH56IJ78KL', 1004, 339.98, 'SHIPPED', '2023-04-15 13:40:00', '321 Cedar Ave, Paris, France', 'P98765432', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO orders (order_number, buyer_id, total_amount, status, placed_at, shipping_address, tracking_number, created_at, updated_at)
VALUES ('MN90OP12QR', 1005, 889.97, 'DELIVERED', '2023-04-20 15:30:00', '654 Maple Rd, Toronto, Canada', 'Q87654321', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO orders (order_number, buyer_id, total_amount, status, placed_at, shipping_address, tracking_number, created_at, updated_at)
VALUES ('ST34UV56WX', 1006, 429.96, 'PENDING', '2023-04-25 10:15:00', '987 Birch Blvd, Sydney, Australia', NULL, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO orders (order_number, buyer_id, total_amount, status, placed_at, shipping_address, tracking_number, created_at, updated_at)
VALUES ('YZ78AB90CD', 1003, 349.99, 'PROCESSING', '2023-04-28 14:25:00', '789 Pine St, New York, USA', NULL, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO orders (order_number, buyer_id, total_amount, status, placed_at, shipping_address, tracking_number, created_at, updated_at)
VALUES ('EF12GH34IJ', 1005, 249.98, 'CANCELLED', '2023-04-30 09:45:00', '654 Maple Rd, Toronto, Canada', NULL, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

-- Insert Order Items
INSERT INTO order_items (order_id, product_id, quantity, price, product_name, product_description, created_at, updated_at)
VALUES (1, 1, 1, 1299.99, 'Laptop Pro X', 'High-performance laptop with 16GB RAM and 512GB SSD', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO order_items (order_id, product_id, quantity, price, product_name, product_description, created_at, updated_at)
VALUES (2, 3, 2, 89.99, 'Coffee Maker Deluxe', 'Programmable coffee maker with thermal carafe', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO order_items (order_id, product_id, quantity, price, product_name, product_description, created_at, updated_at)
VALUES (2, 4, 0.5, 199.99, 'Kitchen Mixer', 'Professional grade stand mixer with multiple attachments', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO order_items (order_id, product_id, quantity, price, product_name, product_description, created_at, updated_at)
VALUES (3, 2, 1, 899.99, 'Smartphone Z20', 'Latest smartphone with 128GB storage and triple camera', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO order_items (order_id, product_id, quantity, price, product_name, product_description, created_at, updated_at)
VALUES (4, 4, 1, 1499.99, 'Ultra HD Smart TV', '65-inch 4K display with smart connectivity', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO order_items (order_id, product_id, quantity, price, product_name, product_description, created_at, updated_at)
VALUES (4, 5, 1, 149.99, 'Wireless Headphones', 'Noise-cancelling bluetooth headphones with 30-hour battery life', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO order_items (order_id, product_id, quantity, price, product_name, product_description, created_at, updated_at)
VALUES (5, 11, 1, 189.99, 'Carbon Fiber Tennis Racket', 'Lightweight design for enhanced play', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO order_items (order_id, product_id, quantity, price, product_name, product_description, created_at, updated_at)
VALUES (5, 12, 1, 149.99, 'Yoga Mat Premium', 'Extra thick non-slip surface for comfortable practice', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO order_items (order_id, product_id, quantity, price, product_name, product_description, created_at, updated_at)
VALUES (6, 16, 1, 159.99, 'Winter Coat', 'Waterproof and insulated for extreme cold', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO order_items (order_id, product_id, quantity, price, product_name, product_description, created_at, updated_at)
VALUES (6, 17, 1, 159.99, 'Leather Boots', 'Handcrafted genuine leather with comfort insole', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO order_items (order_id, product_id, quantity, price, product_name, product_description, created_at, updated_at)
VALUES (6, 6, 1, 569.99, 'Mountain Bike', 'All-terrain bicycle with 21 speeds', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO order_items (order_id, product_id, quantity, price, product_name, product_description, created_at, updated_at)
VALUES (7, 21, 1, 24.99, 'Organic Olive Oil', 'Extra virgin olive oil from Mediterranean groves', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO order_items (order_id, product_id, quantity, price, product_name, product_description, created_at, updated_at)
VALUES (7, 22, 1, 39.99, 'Truffle Collection', 'Assorted chocolate truffles in gift box', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO order_items (order_id, product_id, quantity, price, product_name, product_description, created_at, updated_at)
VALUES (7, 23, 1, 59.99, 'Aged Balsamic Vinegar', '25-year aged traditional balsamic', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO order_items (order_id, product_id, quantity, price, product_name, product_description, created_at, updated_at)
VALUES (7, 24, 1, 19.99, 'Specialty Coffee Beans', 'Single-origin coffee beans, freshly roasted', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO order_items (order_id, product_id, quantity, price, product_name, product_description, created_at, updated_at)
VALUES (8, 8, 1, 349.99, 'Robot Vacuum', 'Automated cleaning with smart mapping technology', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO order_items (order_id, product_id, quantity, price, product_name, product_description, created_at, updated_at)
VALUES (9, 25, 1, 129.99, 'Artisanal Cheese Selection', 'Premium cheese assortment from local farms', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO order_items (order_id, product_id, quantity, price, product_name, product_description, created_at, updated_at)
VALUES (9, 14, 1, 119.99, 'Fitness Tracker', 'Monitors heart rate, steps, and sleep quality', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());