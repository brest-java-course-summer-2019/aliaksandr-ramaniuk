package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Department;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DepartmentDaoJdbcImplTest {

    DepartmentDao departmentDao = new DepartmentDaoJdbcImpl();

    @Test
    void findAll() {
        List<Department> departments = departmentDao.findAll();
        Assert.assertNotNull(departments);
        Assert.assertTrue(departments.size() > 0);
    }

}