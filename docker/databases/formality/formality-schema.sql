CREATE DATABASE IF NOT EXISTS formality;

USE formality;

CREATE TABLE IF NOT EXISTS texts (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNSIGNED,
    text TEXT
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS formality_result (
     id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
     text_id INT UNSIGNED,
     formality DOUBLE,
     informality DOUBLE,
     FOREIGN KEY (text_id) REFERENCES texts(id)
) engine=InnoDB;
