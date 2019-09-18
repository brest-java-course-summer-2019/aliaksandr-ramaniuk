package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.model.Employee;

import java.time.LocalDate;
import java.util.List;


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
     * Find employee with specified id (employeeId).
     *
     * @param employeeId employee id.
     * @return employeeId employee by id.
     */
    Employee findById(Integer employeeId);


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
     * @return employee.
     */
    void update(Employee employee);


    /**
     * Delete employee with specified id (employeeId).
     *
     * @param employeeId employee id.
     */
    void delete(Integer employeeId);


    /**
     * Get filter employee by last name.
     * @param lastName last name.
     * @return employees.
     */
    List<Employee> filterEmployee(String lastName);


    /**
     * Get filter employees by date.
     * @param localDate1 local date first value.
     * @param localDate2 local date second value.
     * @return employees.
     */
    List<Employee> filterEmployeeByDate(LocalDate localDate1, LocalDate localDate2);
}