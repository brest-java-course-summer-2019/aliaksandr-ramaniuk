package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Employee;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})

public class EmployeeDaoJdbcImplTest {

    @Autowired
    EmployeeDao employeeDao;

    private Employee employee;

    @Before
    public void changes() {
        employee = new Employee(1, "loginTest", "firstNameTest", "lastNameTest", "patronicNameTest");
        employee = employeeDao.add(employee);
    }

    @After
    public void cleanChanges() {
        employeeDao.delete(employee.getEmployeeId());
    }


    @Test
    public void findAllEmployees() {
        List<Employee> employees = employeeDao.findAll();
        assertNotNull(employees);
        assertTrue(employees.size() > 0);
    }

    @Test
    public void findByDepartmentId() throws Exception {
        List<Employee> employees = employeeDao.findByDepartmentId(1);
        assertNotNull(employeeDao);
        assertTrue(employees.size() > 0);
    }

    @Test
    public void findByEmployeeId() throws Exception {

 }

    @Test
    public void addEmployee() {
        List<Employee> employees = employeeDao.findAll();
        int sizeBefore = employees.size();
        Employee employee = new Employee(1, "login002", "firstName002", "lastName002", "patronicName002");
        Employee newEmployee = employeeDao.add(employee);
        assertNotNull(newEmployee.getEmployeeId());
        assertTrue(newEmployee.getLogin().equals(employee.getLogin()));
        assertTrue(newEmployee.getFirstName().equals(employee.getFirstName()));
        assertTrue(newEmployee.getLastName().equals(employee.getLastName()));
        assertTrue(newEmployee.getPatronicName().equals(employee.getPatronicName()));
        assertTrue(newEmployee.getDepartmentId().equals(employee.getDepartmentId()));
        assertTrue((sizeBefore + 1) == employeeDao.findAll().size());
    }

    @Test
    public void update() throws Exception {

        employee.setLogin("newLogin");
        employee.setFirstName("newFirstName");
        employee.setLastName("newLastName");
        employee.setPatronicName("newPatronicName");
        employeeDao.update(employee);
        Employee updateEmployee = employeeDao.findById(employee.getEmployeeId()).get();
        assertTrue(updateEmployee.getEmployeeId().equals(employee.getEmployeeId()));
        assertTrue(updateEmployee.getLogin().equals(employee.getLogin()));
        assertTrue(updateEmployee.getFirstName().equals(employee.getFirstName()));
        assertTrue(updateEmployee.getLastName().equals(employee.getLastName()));
        assertTrue(updateEmployee.getPatronicName().equals(employee.getPatronicName()));
        assertTrue(updateEmployee.getDepartmentId().equals(employee.getDepartmentId()));


    }

    @Test
    public void delete() throws Exception {
        Employee employee = new Employee(1, "login002", "firstName002", "lastName002", "patronicName002");
        employeeDao.add(employee);
        List<Employee> employees = employeeDao.findAll();
        int sizeBefore = employees.size();
        employeeDao.delete(employee.getEmployeeId());
        assertTrue((sizeBefore - 1) == employeeDao.findAll().size());
    }

}