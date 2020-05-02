CREATE DATABASE IF NOT EXISTS phoneDb;
 
USE phoneDb;
 
DROP TABLE IF EXISTS user_table;
 
CREATE TABLE user_table (
  user_id int(20) NOT NULL,
  user_name varchar(255) NULL,
  created_by VARCHAR (255) NOT NULL,
  created_date DATE NOT NULL,
  PRIMARY KEY (user_id)
);

DROP TABLE IF EXISTS client_table;
 
CREATE TABLE client_table (
  client_id int(20) NOT NULL,
  client_name varchar(255) NULL,  
  client_firstname varchar(255) NULL,
  created_by VARCHAR (255) NOT NULL,
  created_date DATE NOT NULL,
  PRIMARY KEY (client_id)
);

DROP TABLE IF EXISTS product_table;
 
CREATE TABLE product_table (
  product_id int(20) NOT NULL,
  product_name varchar(255) NULL,  
  product_price varchar(255) NULL,
  created_by VARCHAR (255) NOT NULL,
  created_date DATE NOT NULL,
  user_id int(20) NULL, 
  pack_id int(20) NULL,
  PRIMARY KEY (product_id)
);

DROP TABLE IF EXISTS pack_table;

CREATE TABLE pack_table (
  pack_id int(20) NOT NULL,
  pack_name varchar(255) NULL,  
  pack_price varchar(255) NULL,
  created_by VARCHAR (255) NOT NULL,
  created_date DATE NOT NULL,
  user_id int(20) NULL,
  PRIMARY KEY (pack_id)
);

desc user_table;
desc product_table;
desc pack_table;
