

mysql -u root -p


create database phoneDb default character set utf8 default collate utf8_bin;


GRANT ALL PRIVILEGES ON phoneDb.* to phoneDb@'%' IDENTIFIED BY 'phoneDb-123-Aa&';
GRANT ALL PRIVILEGES ON phoneDb.* to phoneDb@'localhost' IDENTIFIED BY 'phoneDb-123-Aa&';


