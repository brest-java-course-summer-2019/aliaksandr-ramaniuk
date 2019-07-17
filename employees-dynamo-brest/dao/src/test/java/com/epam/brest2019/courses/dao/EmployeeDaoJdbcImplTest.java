package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Employee;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class EmployeeDaoJdbcImplTest {

    EmployeeDao employeeDao = new EmployeeDaoJdbcImpl();

    @Test
    void findAll() {
        List<Employee> employees = employeeDao.findAll();
        Assert.assertNotNull(employees);
        Assert.assertTrue(employees.size() > 0);
    }


}