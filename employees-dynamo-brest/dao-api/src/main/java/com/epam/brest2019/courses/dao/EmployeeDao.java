package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Employee DAO Interface.
 */

public interface EmployeeDao {

    /**
     * Get all employees.
     *
     * @return employees list of all departments.
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

    /**
     * Get the number of employees in all departments.
     *
     * @return totalCountEmployeesInAllDepartments total count employees in all departments.
     */
    int totalCountOfEmployees();

    /**
     * Get filter employees by last name.
     * @param lastName last name.
     * @return employees.
     */
    List<Employee> filterEmployee(String lastName);

    /**
     * Get filter employees by date.
     * @param localDates local date first value.
     * @param localDates local date second value.
     * @return employees.
     */
    List<Employee> filterEmployeeByDate(LocalDate...  localDates);

}

