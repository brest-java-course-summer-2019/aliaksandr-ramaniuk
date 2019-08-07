package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.model.Employee;

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
    Employee findById(Integer employeeId);


    /**
     * Add new employee.
     *
     * @param employee Department.
     * @return employee.
     */
  //  Employee add(Employee employee);


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
     * Get the number of employees in all departments.
     *
     * @return totalCountEmployeesInAllDepartments total count employees in all departments.
     */
    int totalCountOfEmployees();

    /**
     * Get filter employee by last name.
     * @param lastName last name.
     * @return employees.
     */
    List<Employee> filterEmployee(String lastName);
}