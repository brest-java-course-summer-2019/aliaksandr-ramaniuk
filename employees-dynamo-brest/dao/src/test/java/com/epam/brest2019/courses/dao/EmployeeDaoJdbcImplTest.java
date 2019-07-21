package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Employee;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})

public class EmployeeDaoJdbcImplTest {

    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void findAll() {
        List<Employee> employees = employeeDao.findAll();
        Assert.assertNotNull(employees);
        Assert.assertTrue(employees.size() > 0);
    }

    @Test
    public void addEmployee() {
        Employee testEmployee = new Employee();
        testEmployee.setLogin("Test");
        testEmployee.setLastName("Test");
        testEmployee.setFirstName("Test");
        testEmployee.setPatronicName("Test");
        testEmployee.setDepartmentId(2);
        testEmployee.setDepartmentName("Тренер");
        Employee newEmployee = employeeDao.add(testEmployee);
        Assert.assertNotNull(newEmployee.getEmployeeId());
    }

}