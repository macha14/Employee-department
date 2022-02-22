--liquibase formatted sql

--changeset ujjwal:1
CREATE TABLE employee (
    emp_id INT PRIMARY KEY,
    emp_name VARCHAR(40),
    salary INT,
    emp_address VARCHAR(200),
    dept_id INT
)