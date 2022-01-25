CREATE DATABASE IF NOT EXISTS formality;

USE formality;

CREATE TABLE IF NOT EXISTS texts (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNSIGNED,
    text TEXT COLLATE utf8_unicode_ci 
) engine=InnoDB CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS formality_result (
     id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
     text_id INT UNSIGNED,
     formality DOUBLE,
     informality DOUBLE,
     FOREIGN KEY (text_id) REFERENCES texts(id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS formality_evaluation (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    text_id INT UNSIGNED,
    user_id INT UNSIGNED,
    formality TINYINT,
    informality TINYINT,
    FOREIGN KEY (text_id) REFERENCES texts(id)
) engine=InnoDB;
ALTER DATABASE formality CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

ALTER TABLE texts CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE formality_result CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE formality_evaluation CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;