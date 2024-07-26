CREATE DATABASE shopapp;
use shopapp;

-- USERS
CREATE TABLE users (
	id INT PRIMARY KEY AUTO_INCREMENT,
    fullname VARCHAR(100) DEFAULT '',
    phone_number VARCHAR(10) NOT NULL,
    address VARCHAR(200) DEFAULT '',
    password VARCHAR(100) NOT NULL DEFAULT '', -- ENCRYPTED PASSWORD STRING
    created_at DATETIME,
    updated_at DATETIME,
    is_active TINYINT(1) DEFAULT 1,
    date_of_birth DATE,
    facebook_account_id INT DEFAULT 0,
    google_account_id INT DEFAULT 0
);

-- ROLES
CREATE TABLE roles(
	id INT PRIMARY KEY,
	name VARCHAR(20) NOT NULL
);
-- Add the role_id column
ALTER TABLE users ADD COLUMN role_id INT;
-- Add the foreign key constraint
ALTER TABLE users ADD CONSTRAINT FK_ROLE_USER_ID FOREIGN KEY (role_id) REFERENCES roles(id);


-- TOKENS
CREATE TABLE tokens (
	id INT PRIMARY KEY AUTO_INCREMENT,
    token VARCHAR(255) UNIQUE NOT NULL,
    token_type VARCHAR(50) NOT NULL,
    expiration_date DATETIME,
    revoked TINYINT(1) DEFAULT 1,
    expired TINYINT(1) DEFAULT 1,
    user_id int, -- foreign key,
    CONSTRAINT FK_TOKEN_USER_ID FOREIGN KEY (user_id) REFERENCES users(id)
);

-- SOCIAL ACCOUNTS
CREATE TABLE social_accounts (
	id INT PRIMARY KEY AUTO_INCREMENT,
    provider VARCHAR(20) NOT NULL COMMENT 'Social NetWork Name',
    provider_id VARCHAR(50) NOT NULL,
    email VARCHAR(150) NOT NULL COMMENT 'User Email',
    name VARCHAR(100) NOT NULL COMMENT 'User Name',
    user_id int, -- foreign key,
    CONSTRAINT FK_SOCIAL_USER_ID FOREIGN KEY (user_id) REFERENCES users(id)
);

-- CATEGORIES
CREATE TABLE categories (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL DEFAULT '' COMMENT 'Category Name. Eg: electronics'
);


-- PRODUCTS
CREATE TABLE products (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(350) COMMENT 'Product Name',
  price FLOAT NOT NULL CHECK (price >= 0),
  thumbnail VARCHAR(300) DEFAULT '',
  description LONGTEXT,
  created_at DATETIME,
  updated_at DATETIME,
  category_id INT,
  CONSTRAINT FK_CATEGORY_ID FOREIGN KEY (category_id) REFERENCES categories(id)
);
-- Alter table products
ALTER TABLE products AUTO_INCREMENT = 1;
-- Add discount column to table products
ALTER TABLE products ADD COLUMN discount INT;

-- PRODUCT IMAGES
CREATE TABLE product_images (
	id INT PRIMARY KEY AUTO_INCREMENT,
	product_id INT,
	CONSTRAINT FK_PRODUCT_IMAGE_PRODUCT_ID FOREIGN KEY (product_id) REFERENCES products(id),
	CONSTRAINT FK_PRODUCT_IMAGE_PRODUCT_ID_DELETE FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
	image_url VARCHAR(300)
);


-- ORDERS
CREATE TABLE orders(
	id INT PRIMARY KEY AUTO_INCREMENT,
	user_id INT,
	fullname VARCHAR(100) DEFAULT '',
	email VARCHAR(100) DEFAULT '',
	phone_number VARCHAR(20) NOT NULL,
	address VARCHAR(200) NOT NULL,
	note VARCHAR(100) DEFAULT '',
	order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
	status VARCHAR(20),
	total_money FLOAT CHECK (total_money >= 0),
	CONSTRAINT FK_ORDER_USER_ID FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Add column shipping_method
ALTER TABLE orders ADD COLUMN shipping_method varchar(100);
-- Add column shipping_address
ALTER TABLE orders ADD COLUMN shipping_address varchar(200);
-- Add column shipping_date
ALTER TABLE orders ADD COLUMN shipping_date DATE;
-- Add column tracking_number
ALTER TABLE orders ADD COLUMN tracking_number varchar(100);
-- Add column payment_method
ALTER TABLE orders ADD COLUMN payment_method varchar(100);
-- DELETE AN ORDER -> SOFT DELETE -> ADD ACTIVE FIELD
ALTER TABLE orders ADD COLUMN active TINYINT(1);
-- ORDER STATUS
ALTER TABLE orders
MODIFY COLUMN status ENUM('pending', 'processing', 'shipped', 'delivered', 'cancelled')
COMMENT 'Order Status';

-- ORDERS DETAILS
CREATE TABLE order_details(
	id INT PRIMARY KEY AUTO_INCREMENT,
	order_id INT,
	CONSTRAINT FK_ORDER_DETAIL_ORDER_ID FOREIGN KEY (order_id) REFERENCES orders(id),
	product_id INT,
	CONSTRAINT FK_PRODUCT_DETAIL_ORDER_ID FOREIGN KEY (product_id) REFERENCES products(id),
	price FLOAT CHECK(price>=0),
	number_of_products INT CHECK(number_of_products > 0),
	total_money FLOAT CHECK(total_money>=0),
	color VARCHAR(20) DEFAULT ''
);


-- Add temp user
INSERT INTO users(fullname, phone_number, address, date_of_birth, password)
VALUES('Nguyen Van A', '0123456789', 'address 123', '1999-12-22', 'hash_password');


-- Add roles
INSERT into roles(id, name)
values
	(1, "user"),
	(2, "admin");



