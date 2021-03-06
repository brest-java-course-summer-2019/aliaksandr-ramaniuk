package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Department;

import java.util.List;
import java.util.Optional;

/**
 * Department DAO Interface.
 */
public interface DepartmentDao {

    /**
     * Find all departments.
     *
     * @return departments list of all departments.
     */
    List<Department> findAll();

    /**
     * Find Department by departmentId.
     *
     * @param departmentId department id.
     * @return department list with departmentId.
     */
    Optional<Department> findById(Integer departmentId);

    /**
     * Add new department.
     *
     * @param department department.
     * @return department.
     */
    Department add(Department department);

    /**
     * Update department.
     *
     * @param department department.
     * @return department.
     */
    void update(Department department);

    /**
     * Delete department with specified id departmentId.
     *
     * @param departmentId department id.
     */
    void delete(Integer departmentId);

    /**
     * Find all departments with the count of employees in each department.
     *
     * @return list of all departments with the count of employees in each department.
     */
    List<Department> findAllCountEmployeesInDepartment();

}