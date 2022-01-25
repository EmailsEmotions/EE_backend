CREATE DATABASE IF NOT EXISTS emotions;

USE emotions;

CREATE TABLE IF NOT EXISTS texts (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNSIGNED,
    text TEXT COLLATE utf8_unicode_ci 
) engine=InnoDB CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS emotions_result (
     id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
     text_id INT UNSIGNED,
     angry DOUBLE,
     fear DOUBLE,
     happy DOUBLE,
     sad DOUBLE,
     surprise DOUBLE,
     FOREIGN KEY (text_id) REFERENCES texts(id)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS emotions_evaluation (
   id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
   text_id INT UNSIGNED,
   user_id INT UNSIGNED,
   angry TINYINT,
   fear TINYINT,
   happy TINYINT,
   sad TINYINT,
   surprise TINYINT,
   FOREIGN KEY (text_id) REFERENCES texts(id)
) engine=InnoDB;

ALTER DATABASE emotions CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

ALTER TABLE texts CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE emotions_evaluation CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE emotions_result CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
