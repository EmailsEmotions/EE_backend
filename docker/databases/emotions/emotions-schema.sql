CREATE DATABASE IF NOT EXISTS emotions;

USE emotions;

CREATE TABLE IF NOT EXISTS texts (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    text TEXT
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS formality_result (
     id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
     text_id INT UNSIGNED,
     happy DOUBLE,
     sad DOUBLE,
     FOREIGN KEY (text_id) REFERENCES texts(id)
) engine=InnoDB;
