package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Employee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
@Rollback
public class EmployeeDaoJdbcImplTest {

    private static final String LOGIN_ADD = "login add";
    private static final String LOGIN_UPDATE = "login update";
    private static final String LOGIN_DELETE = "login delete";
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME = "firstName";
    private static final String PATRONIC_NAME = "patronicName";
    public static final Integer EMPLOYEE_ID_2 = 2;
    public static final Integer DEPARTMENT_ID_1 = 1;
    public static final Integer DEPARTMENT_ID_2 = 2;
    public static final Integer DEPARTMENT_ID_3 = 3;

    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void findAll() {
        List<Employee> employees = employeeDao.findAll();

        assertNotNull(employees);
        assertTrue(employees.size() > 0);
    }

    @Test
    public void findById() {
        Employee testEmployee = employeeDao.findById(DEPARTMENT_ID_1).get();
        assertNotNull(employeeDao);
        assertTrue(testEmployee.getDepartmentId().equals(DEPARTMENT_ID_1));
        assertEquals(testEmployee.getLogin(), "romanukalex");
        assertEquals(testEmployee.getLastName(), "RAMANIUK");
        assertEquals(testEmployee.getFirstName(), "ALIAKSANDR");
        assertEquals(testEmployee.getPatronicName(), "NIKOLAEVICH");
    }

    @Test
    public void add() {
        LocalDate localDate = LocalDate.of(2019, 07, 07);

        List<Employee> employees = employeeDao.findAll();
        int sizeBeforeAdd = employees.size();
        Employee employee = new Employee(DEPARTMENT_ID_3, LOGIN_ADD, LAST_NAME, FIRST_NAME, PATRONIC_NAME, localDate);
        Employee newEmployee = employeeDao.add(employee);

        List<Employee> employeesAfterAdd = employeeDao.findAll();
        int sizeAfterAdd = employeesAfterAdd.size();
        assertNotNull(newEmployee.getEmployeeId());
        assertEquals(newEmployee.getLogin(), employee.getLogin());
        assertEquals(newEmployee.getLastName(), employee.getLastName());
        assertEquals(newEmployee.getFirstName(), employee.getFirstName());
        assertEquals(newEmployee.getPatronicName(), employee.getPatronicName());
        assertEquals(newEmployee.getLocalDate(), employee.getLocalDate());
        assertEquals(newEmployee.getDepartmentId(), employee.getDepartmentId());
        assertTrue(sizeAfterAdd - sizeBeforeAdd == 1);
    }

    @Test
    public void update() {
        Employee employee = employeeDao.findById(EMPLOYEE_ID_2).get();

        employee.setLogin(LOGIN_UPDATE);
        employee.setFirstName(FIRST_NAME);
        employee.setLastName(LAST_NAME);
        employee.setPatronicName(PATRONIC_NAME);
        employee.setDepartmentId(DEPARTMENT_ID_2);

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
    public void delete() {
        LocalDate localDate = LocalDate.of(2019, 07, 07);

        Employee employee = new Employee(DEPARTMENT_ID_1, LOGIN_DELETE, LAST_NAME, FIRST_NAME, PATRONIC_NAME, localDate);
        employeeDao.add(employee);
        List<Employee> employees = employeeDao.findAll();
        int sizeBefore = employees.size();
        employeeDao.delete(employee.getEmployeeId());
        assertTrue((sizeBefore - 1) == employeeDao.findAll().size());
    }

    @Test
    public void filterEmployee() {
        int CountOfEmployees = 3;
        List<Employee> employees = employeeDao.filterEmployee("RA");

        assertNotNull(employees);
        assertEquals(employees.size(), CountOfEmployees);
    }

    @Test
    public void filterEmployeeByDate() {
        LocalDate localDateStart = LocalDate.of(2019, 9, 9);
        LocalDate localDateEnd = LocalDate.of(2019, 9, 10);

        List<Employee> employees = employeeDao.filterEmployeeByDate(localDateStart, localDateEnd);

        assertNotNull(employees);
        assertEquals(employees.size(), 2);
    }
}