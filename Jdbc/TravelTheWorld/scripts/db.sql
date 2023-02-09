CREATE DATABASE jpa_demo;
show databases;
### Avoid & in password, because it can make trouble in XML files
### <property name="jakarta.persistence.jdbc.password" value="mypassword-135&"/>
### The entity name must immediately follow the '&' in the entity reference.
# Local
GRANT USAGE ON *.* TO 'jpa_demo'@localhost IDENTIFIED BY 'mypassword-135!';
# Remote
GRANT USAGE ON *.* TO 'jpa_demo'@'%' IDENTIFIED BY 'mypassword-135!';
GRANT ALL privileges ON `jpa_demo`.* TO 'jpa_demo'@localhost;
FLUSH PRIVILEGES;


