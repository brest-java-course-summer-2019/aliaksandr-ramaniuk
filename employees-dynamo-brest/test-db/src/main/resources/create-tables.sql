DROP TABLE IF EXISTS department;

CREATE TABLE department (
    department_id INT NOT NULL AUTO_INCREMENT,
    department_name VARCHAR(40) NOT NULL UNIQUE,
    department_access_rights VARCHAR(40) NOT NULL,
    PRIMARY KEY (department_id)
);

DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
    employee_id INT NOT NULL AUTO_INCREMENT,
    login VARCHAR(40) NOT NULL UNIQUE,
    first_name VARCHAR(40) NOT NULL,
    last_name  VARCHAR(40) NOT NULL,
    patronic_name  VARCHAR(40) NULL,
    local_date  DATE NOT NULL,
    department_id INT NOT NULL,
    PRIMARY KEY (employee_id),
        CONSTRAINT employee_to_department_foreign_key
        FOREIGN KEY (department_id)
        REFERENCES department (department_id)
);