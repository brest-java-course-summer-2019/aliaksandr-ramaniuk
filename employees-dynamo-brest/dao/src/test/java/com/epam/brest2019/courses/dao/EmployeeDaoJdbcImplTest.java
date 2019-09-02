package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Employee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void findAll() {
        List<Employee> employees = employeeDao.findAll();

        assertNotNull(employees);
        assertTrue(employees.size() > 0);
    }

    @Test
    public void findByDepartmentId() {
        List<Employee> employees = employeeDao.findByDepartmentId(6);
        assertNotNull(employeeDao);
        assertTrue(employees.size() > 0);
        assertEquals(employees.size(), 3);
    }

    @Test
    public void findById() {
        int departmentId = 1;
        LocalDate testLocalDate = LocalDate.of(2019, 01, 01);

        Employee testEmployee = employeeDao.findById(departmentId).get();
        assertNotNull(employeeDao);
        assertTrue(testEmployee.getDepartmentId().equals(departmentId));
        assertEquals(testEmployee.getLogin(), "romanukalex");
        assertEquals(testEmployee.getLastName(), "РОМАНЮК");
        assertEquals(testEmployee.getFirstName(), "АЛЕКСАНДР");
        assertEquals(testEmployee.getPatronicName(), "НИКОЛАЕВИЧ");
        assertEquals(testEmployee.getLocalDate(), testLocalDate);
    }

    @Test
    public void add() {
        int departmentId = 3;

        LocalDate localDate = LocalDate.of(2019, 07, 07);

        List<Employee> employees = employeeDao.findAll();
        int sizeBeforeAdd = employees.size();
        Employee employee = new Employee(departmentId, LOGIN_ADD, LAST_NAME, FIRST_NAME, PATRONIC_NAME, localDate);
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
        int employeeId = 2;
        int departmentId = 2;

        LocalDate newLocalDate = LocalDate.of(2019, 07, 17);

        Employee employee = employeeDao.findById(employeeId).get();

        employee.setLogin(LOGIN_UPDATE);
        employee.setFirstName(FIRST_NAME);
        employee.setLastName(LAST_NAME);
        employee.setPatronicName(PATRONIC_NAME);
        employee.setDepartmentId(departmentId);
        employee.setLocalDate(newLocalDate);

        employeeDao.update(employee);

        Employee updateEmployee = employeeDao.findById(employee.getEmployeeId()).get();
        assertEquals(updateEmployee.getEmployeeId(), employee.getEmployeeId());
        assertEquals(updateEmployee.getLogin(), employee.getLogin());
        assertEquals(updateEmployee.getLastName(), employee.getLastName());
        assertEquals(updateEmployee.getFirstName(), employee.getFirstName());
        assertEquals(updateEmployee.getPatronicName(), employee.getPatronicName());
        assertEquals(updateEmployee.getLocalDate(), employee.getLocalDate());
        assertEquals(updateEmployee.getDepartmentId(), employee.getDepartmentId());
    }

    @Test
    public void delete() {
        int departmentId = 1;

        LocalDate localDate = LocalDate.of(2019, 07, 07);

        Employee employee = new Employee(departmentId, LOGIN_DELETE, LAST_NAME, FIRST_NAME, PATRONIC_NAME, localDate);
        employeeDao.add(employee);
        List<Employee> employees = employeeDao.findAll();
        int sizeBefore = employees.size();
        employeeDao.delete(employee.getEmployeeId());
        assertTrue((sizeBefore - 1) == employeeDao.findAll().size());
    }

    @Test
    public void totalCountOfEmployees() {
        int totalCountOfEmployees = employeeDao.totalCountOfEmployees();

        assertNotNull(totalCountOfEmployees);
 //       assertEquals(totalCountOfEmployees, 11);
    }

    @Test
    public void filterEmployee() {
        int CountOfEmployees = 2;
        List<Employee> employees = employeeDao.filterEmployee("РО");

        assertNotNull(employees);
        assertEquals(employees.size(), CountOfEmployees);
    }

    @Test
    public void filterEmployeeByDate() {
        LocalDate localDate1 = LocalDate.of(2019, 01, 9);
        LocalDate localDate2 = LocalDate.of(2019, 01, 10);

        List<Employee> employees = employeeDao.filterEmployeeByDate(localDate1, localDate2);

        assertNotNull(employees);
        assertEquals(employees.size(), 3);
    }
}