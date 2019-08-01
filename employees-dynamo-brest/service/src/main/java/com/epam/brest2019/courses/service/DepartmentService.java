package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.model.Department;

import java.util.List;
import java.util.Optional;

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
    Optional<Department> findById(Integer departmentId);

    /**
     * Add new department.
     *
     * @param department Department.
     * @return department.
     */
    Department add(Department department);

    /**
     * Update department.
     *
     * @param department department.
     */
    void update(Department department);

    /**
     * Delete department with specified id (departmentId).
     *
     * @param departmentId department id.
     */
    void delete(Integer departmentId);

}