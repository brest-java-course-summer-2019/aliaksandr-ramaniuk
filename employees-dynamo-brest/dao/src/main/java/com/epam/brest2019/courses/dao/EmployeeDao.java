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
     *
     * @return employees List of all departments.
     */
    List<Employee> findAll();

    /**
     * Get all employees with specified department id.
     *
     * @param departmentId department id.
     * @return departmentId.
     */
    List<Employee> findByDepartmentId(Integer departmentId);

    /**
     * Get employee with specified id.
     *
     * @param employeeId employee id.
     * @return employeeId.
     */
    Optional<Employee> findById(Integer employeeId);

    /**
     * Persist new employee.
     *
     * @param employee employee.
     * @return employee.
     */
    Employee add(Employee employee);

    /**
     * Update employee.
     *
     * @param employee employee.
     */
    void update(Employee employee);

    /**
     * Delete employee with specified id.
     *
     * @param employeeId employee id.
     */
    void delete(Integer employeeId);


}