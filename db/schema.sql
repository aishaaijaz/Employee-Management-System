CREATE DATABASE IF NOT EXISTS emp_db;
USE emp_db;

CREATE TABLE IF NOT EXISTS employees (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    department  VARCHAR(50),
    salary      DOUBLE,
    email       VARCHAR(100)
);
