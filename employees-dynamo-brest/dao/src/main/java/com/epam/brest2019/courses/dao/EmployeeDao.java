package com.epam.brest2019.courses.dao;


import com.epam.brest2019.courses.model.Employee;

import java.util.List;
import java.util.Optional;

/**
 * Employee DAO Interface.
 */

public interface EmployeeDao {

    /**
     * Get all employees.
     */

    List<Employee> findAll();

    /**
     * Get all employees with specified department id.
     */
    List<Employee> findByDepartmentId(Integer departmentId);

    /**
     * Get employee with specified id.
     */
    Optional<Employee> findById(Integer employeeId);

    /**
     * Persist new employee.
     */

    Employee add(Employee employee);


    /**
     * Update employee.
     */
    void update(Employee employee);

    /**
     * Delete employee with specified id.
     */
    void delete(Integer employeeId);


}