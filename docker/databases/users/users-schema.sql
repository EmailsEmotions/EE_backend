CREATE DATABASE IF NOT EXISTS users;

USE users;

CREATE TABLE IF NOT EXISTS users (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username TEXT,
    email TEXT,
    password TEXT
) engine=InnoDB;
