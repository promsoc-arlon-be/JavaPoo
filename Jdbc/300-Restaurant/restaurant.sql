use restaurant;

# Delete table in reverse order to satisfy integrity constraints
DROP table if exists menu_plat ;
DROP table if exists menu ;
DROP table if exists platprincipal ;
DROP table if exists entree ;
DROP table if exists dessert ;
DROP table if exists plat ;

# Create table according to dependency rules
CREATE TABLE menu (
     id MEDIUMINT NOT NULL AUTO_INCREMENT,
     name CHAR(30) NOT NULL,
     PRIMARY KEY (id)
);

CREATE TABLE plat (
     id MEDIUMINT NOT NULL AUTO_INCREMENT,
     name CHAR(30) NOT NULL,
     PRIMARY KEY (id)
);

CREATE TABLE entree (
     id MEDIUMINT NOT NULL AUTO_INCREMENT,
     plat_id MEDIUMINT NOT NULL,
     PRIMARY KEY (id),
     FOREIGN KEY (plat_id) REFERENCES plat(id)
);

CREATE TABLE platprincipal (
     id MEDIUMINT NOT NULL AUTO_INCREMENT,
     plat_id MEDIUMINT NOT NULL,
     PRIMARY KEY (id),
     FOREIGN KEY (plat_id) REFERENCES plat(id)
);

CREATE TABLE dessert (
     id MEDIUMINT NOT NULL AUTO_INCREMENT,
     plat_id MEDIUMINT NOT NULL,
     anniversary TINYINT(1) NOT NULL DEFAULT 0,
     PRIMARY KEY (id),
     FOREIGN KEY (plat_id) REFERENCES plat(id)
);

CREATE TABLE menu_plat (
     id MEDIUMINT NOT NULL AUTO_INCREMENT,
     menu_id MEDIUMINT NOT NULL,
     plat_id MEDIUMINT NOT NULL,
     PRIMARY KEY (id),
     FOREIGN KEY (menu_id) REFERENCES menu(id),
     FOREIGN KEY (plat_id) REFERENCES plat(id),
     CONSTRAINT menu_plat_unique UNIQUE (menu_id, plat_id)
);
