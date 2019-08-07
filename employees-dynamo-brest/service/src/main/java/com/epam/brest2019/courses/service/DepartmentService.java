package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.model.Department;

import java.util.List;

/**
 * Department Service Interface.
 */
public interface DepartmentService {

    /**
     * Find all departments.
     *
     * @return departments List of all departments.
     */
    List<Department> findAll();

    /**
     * Find Department by id (departmentId).
     *
     * @param departmentId department id (departmentId).
     * @return departmentId department by id.
     */
    Department findById(Integer departmentId);

    /**
     * Add new department.
     *
     * @param department Department.
     * @return department.
     */
//    Department add(Department department);

    /**
     * Update department.
     *
     * @param department department.
     * @return department.
     */
    void update(Department department);

    /**
     * Delete department with specified id (departmentId).
     *
     * @param departmentId department id.
     */
    void delete(Integer departmentId);

    /**
     * Find all departments with the count of employees in each department.
     *
     * @return findAllCountEmployeesInDepartment list of all departments with the count of employees in each department.
     */
    List<Department> findAllCountEmployeesInDepartment();

}