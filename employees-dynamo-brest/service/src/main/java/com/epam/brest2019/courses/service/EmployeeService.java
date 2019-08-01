package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.model.Employee;

import java.util.List;
import java.util.Optional;

/**
 * Employee Service Interface.
 */

public interface EmployeeService {

    /**
     * Find all employees.
     *
     * @return employees List of all employees.
     */
    List<Employee> findAll();


    /**
     * Find all employees with specified department id (departmentId).
     *
     * @param departmentId department id.
     * @return departmentId list of employees by department id.
     */
    List<Employee> findByDepartmentId(Integer departmentId);


    /**
     * Find employee with specified id (employeeId).
     *
     * @param employeeId employee id.
     * @return employeeId employee by id.
     */
    Optional<Employee> findById(Integer employeeId);


    /**
     * Add new employee.
     *
     * @param employee Department.
     * @return employee.
     */
    Employee add(Employee employee);


    /**
     * Update employee.
     *
     * @param employee Employee.
     */
    void update(Employee employee);

    /**
     * Delete employee with specified id (employeeId).
     *
     * @param employeeId employee id.
     */
    void delete(Integer employeeId);
}