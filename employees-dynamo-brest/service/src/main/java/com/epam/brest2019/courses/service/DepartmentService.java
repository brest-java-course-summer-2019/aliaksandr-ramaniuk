package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.model.Department;

import java.util.List;
import java.util.Optional;

/**
 * Department Service Interface.
 */
public interface DepartmentService {

    /**
     * Get all departments.
     */
    List<Department> findAll();

    /**
     * Get Department By Id.
     */
    Optional<Department> findById(Integer departmentId);

    /**
     * Update department.
     */
    void update(Department department);

    /**
     * Delete department with specified id.
     */
    void delete(Integer departmentId);

}