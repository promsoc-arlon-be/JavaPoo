
-- Create DB and user

-- sudo mysql -h localhost  -u root -p

show databases;

drop database restaurant;
drop user restaurant;

create database restaurant;
create user restaurant;
GRANT ALL ON *.* TO 'restaurant'@localhost IDENTIFIED BY 'Java-Bien!';
GRANT ALL ON *.* TO 'restaurant'@'%' IDENTIFIED BY 'Java-Bien!';
GRANT ALL privileges ON `restaurant`.* TO 'restaurant'@localhost;
flush privileges;

-- Populate the DB

-- mysql -u restaurant -p
-- source restaurant.sql

