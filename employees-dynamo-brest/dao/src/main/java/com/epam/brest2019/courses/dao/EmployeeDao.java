package com.epam.brest2019.courses.dao;


import com.epam.brest2019.courses.model.Employee;

import java.util.List;

public interface EmployeeDao {

    Employee add(Employee employee);

    void update(Employee employee);

    void delete(Integer employeeId);

    List<Employee> findAll();

}