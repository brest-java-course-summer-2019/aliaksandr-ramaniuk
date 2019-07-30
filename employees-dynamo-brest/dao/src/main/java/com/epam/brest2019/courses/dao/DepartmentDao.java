package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Department;

import java.util.List;
import java.util.Optional;

/**
 * Department DAO Interface.
 */
public interface DepartmentDao {

    /**
     * Persist new department.
     */
    Department add(Department department);

    /**
     * Update department.
     */
    void update(Department department);

    /**
     * Delete department with specified id.
     */
    void delete(Integer departmentId);

    /**
     * Get departments.
     */
    List<Department> findAll();

    /**
     * Get Department By Id.
     */
    Optional<Department> findById(Integer departmentId);

}