CREATE DATABASE UserDB;
USE UserDB;

DROP TABLE users;

CREATE TABLE users (
    id int NOT NULL AUTO_INCREMENT KEY,
    username varchar(120) NOT NULL,
    password varchar(120) NOT NULL
);