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
     * Get employee with specified id.
     *
     * @param employeeId employee id.
     * @return employees list with employeeId.
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
     * Get filter employees by last name.
     * @param lastName last name.
     * @return employees list with filter by last name.
     */
    List<Employee> filterEmployee(String lastName);

    /**
     * Get filter employees by date.
     * @param localDate1 local date first value.
     * @param localDate2 local date second value.
     * @return employees list with filter by date.
     */
    List<Employee> filterEmployeeByDate(LocalDate localDate1, LocalDate localDate2);

}

