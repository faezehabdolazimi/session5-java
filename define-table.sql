CREATE TABLE employees (  
    employee_id INT PRIMARY KEY AUTO_INCREMENT,  
    name VARCHAR(100) NOT NULL,  
    employee_number VARCHAR(20) NOT NULL UNIQUE,  
    salary DECIMAL(10, 2) NOT NULL,  
    birth_date DATE NOT NULL  
); 

CREATE TABLE departments (  
    name VARCHAR(100) NOT NULL  
);
