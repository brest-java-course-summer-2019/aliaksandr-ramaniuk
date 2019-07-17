package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Department;

import java.util.List;

public interface DepartmentDao {

    Department add(Department department);

    void update(Department department);

    void delete(Integer departmentId);

    List<Department> findAll();

}