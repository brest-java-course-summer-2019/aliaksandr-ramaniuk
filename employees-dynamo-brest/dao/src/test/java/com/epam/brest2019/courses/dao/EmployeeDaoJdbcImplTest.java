package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Employee;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
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
        employee = new Employee(1, "loginTest", "lastNameTest", "firstNameTest", "patronicNameTest");
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
        Employee testEmployee = employeeDao.findById(employee.getEmployeeId()).get();
        assertNotNull(employeeDao);
        assertEquals(testEmployee.getEmployeeId(),employee.getEmployeeId());
        assertTrue(testEmployee.getDepartmentId().equals(1));
        assertEquals(testEmployee.getLogin(),"loginTest");
        assertEquals(testEmployee.getLastName(), "lastNameTest");
        assertEquals(testEmployee.getFirstName(), "firstNameTest");
        assertEquals(testEmployee.getPatronicName(),"patronicNameTest");
 }

    @Test
    public void addEmployee() {
        List<Employee> employees = employeeDao.findAll();
        int sizeBefore = employees.size();
        Employee employee = new Employee(1, "login002", "lastName002", "firstName002", "patronicName002");
        Employee newEmployee = employeeDao.add(employee);
        assertNotNull(newEmployee.getEmployeeId());
        assertEquals(newEmployee.getLogin(), employee.getLogin());
        assertEquals(newEmployee.getLastName(), employee.getLastName());
        assertEquals(newEmployee.getFirstName(), employee.getFirstName());
        assertEquals(newEmployee.getPatronicName(), employee.getPatronicName());
        assertEquals(newEmployee.getDepartmentId(), employee.getDepartmentId());
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
        assertEquals(updateEmployee.getEmployeeId(), employee.getEmployeeId());
        assertEquals(updateEmployee.getLogin(), employee.getLogin());
        assertEquals(updateEmployee.getLastName(), employee.getLastName());
        assertEquals(updateEmployee.getFirstName(), employee.getFirstName());
        assertEquals(updateEmployee.getPatronicName(), employee.getPatronicName());
        assertEquals(updateEmployee.getDepartmentId(), employee.getDepartmentId());
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