
# How to

* Create the DB with create-db.sh
* Create the schema with create-db.sh
* Run the  Java class
* Display the data with display-data.sql

# Sample execution:

```
Creating clients

May 02, 2020 9:14:24 PM org.hibernate.Version logVersion
INFO: HHH000412: Hibernate Core {5.4.0.Final}
May 02, 2020 9:14:24 PM org.hibernate.cfg.Environment <clinit>
INFO: HHH000206: hibernate.properties not found
May 02, 2020 9:14:25 PM org.hibernate.annotations.common.reflection.java.JavaReflectionManager <clinit>
INFO: HCANN000001: Hibernate Commons Annotations {5.1.0.Final}
May 02, 2020 9:14:26 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using Hibernate built-in connection pool (not for production use!)
May 02, 2020 9:14:26 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: using driver [org.mariadb.jdbc.Driver] at URL [jdbc:mysql://127.0.0.1:3306/phoneDb]
May 02, 2020 9:14:26 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {password=****, user=phoneDb}
May 02, 2020 9:14:26 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
May 02, 2020 9:14:26 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH000115: Hibernate connection pool size: 20 (min=1)
May 02, 2020 9:14:26 PM org.hibernate.dialect.Dialect <init>
INFO: HHH000400: Using dialect: org.hibernate.dialect.MariaDB103Dialect

Records Saved Successfully To The Database

Hibernate: insert into user_table (created_by, created_date, user_name, user_id) values (?, ?, ?, ?)
Hibernate: insert into user_table (created_by, created_date, user_name, user_id) values (?, ?, ?, ?)
Hibernate: insert into user_table (created_by, created_date, user_name, user_id) values (?, ?, ?, ?)
Hibernate: insert into user_table (created_by, created_date, user_name, user_id) values (?, ?, ?, ?)
Hibernate: insert into user_table (created_by, created_date, user_name, user_id) values (?, ?, ?, ?)
Creating products and packs.

May 02, 2020 9:14:28 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using Hibernate built-in connection pool (not for production use!)
May 02, 2020 9:14:28 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: using driver [org.mariadb.jdbc.Driver] at URL [jdbc:mysql://127.0.0.1:3306/phoneDb]
May 02, 2020 9:14:28 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {password=****, user=phoneDb}
May 02, 2020 9:14:28 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
May 02, 2020 9:14:28 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH000115: Hibernate connection pool size: 20 (min=1)
May 02, 2020 9:14:28 PM org.hibernate.dialect.Dialect <init>
INFO: HHH000400: Using dialect: org.hibernate.dialect.MariaDB103Dialect

Records Saved Successfully To The Database.

Hibernate: insert into product_table (created_by, created_date, pack_id, product_name, user_id, product_id) values (?, ?, ?, ?, ?, ?)
Hibernate: insert into product_table (created_by, created_date, pack_id, product_name, user_id, product_id) values (?, ?, ?, ?, ?, ?)
Hibernate: insert into product_table (created_by, created_date, pack_id, product_name, user_id, product_id) values (?, ?, ?, ?, ?, ?)
Hibernate: insert into product_table (created_by, created_date, pack_id, product_name, user_id, product_id) values (?, ?, ?, ?, ?, ?)
Hibernate: insert into product_table (created_by, created_date, pack_id, product_name, user_id, product_id) values (?, ?, ?, ?, ?, ?)
Hibernate: insert into pack_table (created_by, created_date, pack_name, user_id, pack_id) values (?, ?, ?, ?, ?)
Hibernate: insert into pack_table (created_by, created_date, pack_name, user_id, pack_id) values (?, ?, ?, ?, ?)
Hibernate: insert into pack_table (created_by, created_date, pack_name, user_id, pack_id) values (?, ?, ?, ?, ?)
Hibernate: insert into pack_table (created_by, created_date, pack_name, user_id, pack_id) values (?, ?, ?, ?, ?)
Hibernate: insert into pack_table (created_by, created_date, pack_name, user_id, pack_id) values (?, ?, ?, ?, ?)
Creating user's products.

May 02, 2020 9:14:29 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using Hibernate built-in connection pool (not for production use!)
May 02, 2020 9:14:29 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: using driver [org.mariadb.jdbc.Driver] at URL [jdbc:mysql://127.0.0.1:3306/phoneDb]
May 02, 2020 9:14:29 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {password=****, user=phoneDb}
May 02, 2020 9:14:29 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
May 02, 2020 9:14:29 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH000115: Hibernate connection pool size: 20 (min=1)
May 02, 2020 9:14:29 PM org.hibernate.dialect.Dialect <init>
INFO: HHH000400: Using dialect: org.hibernate.dialect.MariaDB103Dialect

Records Saved Successfully To The Database.

Hibernate: insert into user_table (created_by, created_date, user_name, user_id) values (?, ?, ?, ?)
Hibernate: insert into product_table (created_by, created_date, pack_id, product_name, user_id, product_id) values (?, ?, ?, ?, ?, ?)
Hibernate: insert into product_table (created_by, created_date, pack_id, product_name, user_id, product_id) values (?, ?, ?, ?, ?, ?)
Hibernate: insert into product_table (created_by, created_date, pack_id, product_name, user_id, product_id) values (?, ?, ?, ?, ?, ?)
Hibernate: insert into product_table (created_by, created_date, pack_id, product_name, user_id, product_id) values (?, ?, ?, ?, ?, ?)
Hibernate: insert into product_table (created_by, created_date, pack_id, product_name, user_id, product_id) values (?, ?, ?, ?, ?, ?)
Hibernate: update product_table set created_by=?, created_date=?, pack_id=?, product_name=?, user_id=? where product_id=?
Hibernate: update product_table set created_by=?, created_date=?, pack_id=?, product_name=?, user_id=? where product_id=?
Hibernate: update product_table set created_by=?, created_date=?, pack_id=?, product_name=?, user_id=? where product_id=?
Hibernate: update product_table set created_by=?, created_date=?, pack_id=?, product_name=?, user_id=? where product_id=?
Hibernate: update product_table set created_by=?, created_date=?, pack_id=?, product_name=?, user_id=? where product_id=?
Creating user's products.

May 02, 2020 9:14:29 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl configure
WARN: HHH10001002: Using Hibernate built-in connection pool (not for production use!)
May 02, 2020 9:14:29 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001005: using driver [org.mariadb.jdbc.Driver] at URL [jdbc:mysql://127.0.0.1:3306/phoneDb]
May 02, 2020 9:14:29 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001001: Connection properties: {password=****, user=phoneDb}
May 02, 2020 9:14:29 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl buildCreator
INFO: HHH10001003: Autocommit mode: false
May 02, 2020 9:14:29 PM org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl$PooledConnections <init>
INFO: HHH000115: Hibernate connection pool size: 20 (min=1)
May 02, 2020 9:14:29 PM org.hibernate.dialect.Dialect <init>
INFO: HHH000400: Using dialect: org.hibernate.dialect.MariaDB103Dialect

Records Saved Successfully To The Database.

Hibernate: insert into user_table (created_by, created_date, user_name, user_id) values (?, ?, ?, ?)
Hibernate: insert into pack_table (created_by, created_date, pack_name, user_id, pack_id) values (?, ?, ?, ?, ?)
Hibernate: insert into product_table (created_by, created_date, pack_id, product_name, user_id, product_id) values (?, ?, ?, ?, ?, ?)
Hibernate: insert into product_table (created_by, created_date, pack_id, product_name, user_id, product_id) values (?, ?, ?, ?, ?, ?)
Hibernate: insert into product_table (created_by, created_date, pack_id, product_name, user_id, product_id) values (?, ?, ?, ?, ?, ?)
Hibernate: insert into product_table (created_by, created_date, pack_id, product_name, user_id, product_id) values (?, ?, ?, ?, ?, ?)
Hibernate: insert into product_table (created_by, created_date, pack_id, product_name, user_id, product_id) values (?, ?, ?, ?, ?, ?)
Hibernate: update pack_table set created_by=?, created_date=?, pack_name=?, user_id=? where pack_id=?
Hibernate: update product_table set created_by=?, created_date=?, pack_id=?, product_name=?, user_id=? where product_id=?
Hibernate: update product_table set created_by=?, created_date=?, pack_id=?, product_name=?, user_id=? where product_id=?
Hibernate: update product_table set created_by=?, created_date=?, pack_id=?, product_name=?, user_id=? where product_id=?
Hibernate: update product_table set created_by=?, created_date=?, pack_id=?, product_name=?, user_id=? where product_id=?
Hibernate: update product_table set created_by=?, created_date=?, pack_id=?, product_name=?, user_id=? where product_id=?

```