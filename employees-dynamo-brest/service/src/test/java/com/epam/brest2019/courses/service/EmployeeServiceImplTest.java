package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.model.Employee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})

public class EmployeeServiceImplTest {

    private static final String LOGIN_ADD = "login add";
    private static final String LOGIN_UPDATE = "login update";
    private static final String LOGIN_DELETE = "login delete";
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME = "firstName";
    private static final String PATRONIC_NAME = "patronicName";

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void findAll() {
        List<Employee> employees = employeeService.findAll();

        assertNotNull(employees);
        assertTrue(employees.size() > 0);
    }

    @Test
    public void findByDepartmentId() {
        List<Employee> employees = employeeService.findByDepartmentId(2);
        assertNotNull(employeeService);
        assertTrue(employees.size() > 0);
        assertEquals(employees.size(), 2);
    }

    @Test
    public void findById() {
        int departmentId = 3;
        int employeeId = 5;

        LocalDate testLocalDate = LocalDate.of(2019, 01, 05);

        Employee testEmployee = employeeService.findById(employeeId);
        assertNotNull(employeeService);
        assertTrue(testEmployee.getDepartmentId().equals(departmentId));
        assertTrue(testEmployee.getEmployeeId().equals(employeeId));
        assertEquals(testEmployee.getLogin(), "nekhaychik33");
        assertEquals(testEmployee.getLastName(), "НЕХАЙЧИК");
        assertEquals(testEmployee.getFirstName(), "ПАВЕЛ");
        assertEquals(testEmployee.getPatronicName(), "АЛЕКСАНДРОВИЧ");
        assertEquals(testEmployee.getLocalDate(), testLocalDate);
    }

    @Test
    public void addEmployee() {
        int departmentId = 1;

        LocalDate localDate = LocalDate.of(2019, 07, 07);

        List<Employee> employeesSize = employeeService.findAll();
        int sizeBeforeAdd = employeesSize.size();

        Employee newEmployee = new Employee(departmentId, LOGIN_ADD, LAST_NAME, FIRST_NAME, PATRONIC_NAME, localDate);
        Employee AddEmployee = employeeService.add(newEmployee);
        int sizeAfterAdd = employeesSize.size();

        assertNotNull(AddEmployee.getEmployeeId());
        assertEquals(AddEmployee.getLogin(), newEmployee.getLogin());
        assertEquals(AddEmployee.getLastName(), newEmployee.getLastName());
        assertEquals(AddEmployee.getFirstName(), newEmployee.getFirstName());
        assertEquals(AddEmployee.getPatronicName(), newEmployee.getPatronicName());
        assertEquals(AddEmployee.getLocalDate(), newEmployee.getLocalDate());
        assertEquals(AddEmployee.getDepartmentId(), newEmployee.getDepartmentId());
        assertEquals(sizeBeforeAdd, sizeAfterAdd);

        List<Employee> TotalEmployeeInDepartment = employeeService.findByDepartmentId(1);
        assertEquals(TotalEmployeeInDepartment.size(), 2);
    }

    @Test
    public void update() {
        int departmentId = 1;
        int employeeId = 1;

        LocalDate newLocalDate = LocalDate.of(2019, 07, 17);

        Employee newEmployee = employeeService.findById(employeeId);
        newEmployee.setLogin(LOGIN_UPDATE);
        newEmployee.setFirstName(FIRST_NAME);
        newEmployee.setLastName(LAST_NAME);
        newEmployee.setPatronicName(PATRONIC_NAME);
        newEmployee.setDepartmentId(departmentId);
        newEmployee.setLocalDate(newLocalDate);

        employeeService.update(newEmployee);
        Employee updateEmployee = employeeService.findById(newEmployee.getEmployeeId());

        assertEquals(updateEmployee.getEmployeeId(), newEmployee.getEmployeeId());
        assertEquals(updateEmployee.getLogin(), newEmployee.getLogin());
        assertEquals(updateEmployee.getLastName(), newEmployee.getLastName());
        assertEquals(updateEmployee.getFirstName(), newEmployee.getFirstName());
        assertEquals(updateEmployee.getPatronicName(), newEmployee.getPatronicName());
        assertEquals(updateEmployee.getLocalDate(), newEmployee.getLocalDate());
        assertEquals(updateEmployee.getDepartmentId(), newEmployee.getDepartmentId());
    }

    @Test
    public void delete() {
        int departmentId = 5;

        LocalDate newLocalDate = LocalDate.of(2019, 07, 07);

        Employee newEmployee = new Employee(departmentId, LOGIN_DELETE, LAST_NAME, FIRST_NAME, PATRONIC_NAME, newLocalDate);

        employeeService.add(newEmployee);

        int sizeBeforeDelete = employeeService.findAll().size();

        employeeService.delete(newEmployee.getEmployeeId());
        int sizeAfterDelete = employeeService.findAll().size();

        assertEquals(sizeBeforeDelete - 1, sizeAfterDelete);
    }

    @Test
    public void filterEmployee() {
        int CountOfEmployee2 = 2;
        List<Employee> employees2 = employeeService.filterEmployee("РО");

        int CountOfEmployees1 = 1;
        List<Employee> employees1 = employeeService.filterEmployee("РОМАНЮК");

        assertNotNull(employees2);
        assertEquals(employees2.size(), CountOfEmployee2);

        assertNotNull(employees1);
        assertEquals(employees1.size(), CountOfEmployees1);
    }

    @Test
    public void filterEmployeeByDate() {
        LocalDate localDate1 = LocalDate.of(2019, 01, 1);
        LocalDate localDate2 = LocalDate.of(2019, 01, 10);

        List<Employee> employees = employeeService.filterEmployeeByDate(localDate1, localDate2);

        assertNotNull(employees);
        assertEquals(employees.size(), 11);
    }
}