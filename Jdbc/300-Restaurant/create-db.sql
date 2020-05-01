
-- mysql -h localhost  -u root -p
show databases;
create database restaurant;
CREATE USER `restaurant`@'localhost' IDENTIFIED BY '123-Passw0rd';
grant ALL on restaurant.* to restaurant@localhost;
flush privileges;

-- mysql -u restaurant -p
-- source restaurant.sql

