CREATE DATABASE IF NOT EXISTS users;

USE users;

CREATE TABLE IF NOT EXISTS users (
    id          int unsigned auto_increment primary key,
    username    text                 null,
    email       text                 null,
    password    text                 null,
    active      tinyint(1) default 1 null,
    confirmed   tinyint(1) default 0 null,
    accountType smallint   default 1 null,
    canAdmin    tinyint(1) default 0 null
) engine=InnoDB;
