package com.epam.brest2019.courses.model;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EmployeeTest {


    private static final Integer DEPARTMENT_ID = 8;
    private static final String DEPARTMENT_NAME = "DEPARTMEN NAME";
    private static final Integer EMPLOYEE_ID = 10;
    private static final String EMPLOYEE_LOGIN = "login";
    private static final String EMPLOYEE_FIRST_NAME = "first name";
    private static final String EMPLOYEE_LAST_NAME = "last name";
    private static final String EMPLOYEE_PATRONIC_NAME = "patronic name";

    Employee employee = new Employee();

    @Test
    public void getEmployeeId() {
        employee.setEmployeeId(EMPLOYEE_ID);
        assertTrue(employee.getEmployeeId().equals(EMPLOYEE_ID));
    }

    @Test
    public void getDepartmentId() {
        employee.setDepartmentId(DEPARTMENT_ID);
        assertTrue(employee.getDepartmentId().equals(DEPARTMENT_ID));
    }

    @Test
    public void getDepartmentName() {
        employee.setDepartmentName(DEPARTMENT_NAME);
        assertTrue(employee.getDepartmentName().equals(DEPARTMENT_NAME));
    }


    @Test
    public void getLogin() {
        employee.setLogin(EMPLOYEE_LOGIN);
        assertEquals(employee.getLogin(), EMPLOYEE_LOGIN);
    }

    @Test
    public void getFirstName() {
        employee.setFirstName(EMPLOYEE_FIRST_NAME);
        assertEquals(employee.getFirstName(), EMPLOYEE_FIRST_NAME);
    }

    @Test
    public void getLastName() {
        employee.setLastName(EMPLOYEE_LAST_NAME);
        assertEquals(employee.getLastName(), EMPLOYEE_LAST_NAME);
    }

    @Test
    public void getPatronicName() {
        employee.setPatronicName(EMPLOYEE_PATRONIC_NAME);
        assertEquals(employee.getPatronicName(), EMPLOYEE_PATRONIC_NAME);
    }

    @Test
    public void getLocalDate() {
        LocalDate localDate = LocalDate.of(2019, 01, 01);
        LocalDate localDateTest = LocalDate.of(2019, 01, 01);

        employee.setLocalDate(localDate);
        assertEquals(employee.getLocalDate(), localDateTest);
    }




    /**
     * Constructor Employee Test
     */

    public EmployeeTest() {
        LocalDate localDate = LocalDate.of(2019, 02, 02);
        employee = new Employee(DEPARTMENT_ID, EMPLOYEE_LOGIN, EMPLOYEE_LAST_NAME, EMPLOYEE_FIRST_NAME, EMPLOYEE_PATRONIC_NAME, localDate);
        assertTrue(employee.getDepartmentId().equals(DEPARTMENT_ID));
        assertEquals(employee.getLogin(), EMPLOYEE_LOGIN);
        assertEquals(employee.getLastName(), EMPLOYEE_LAST_NAME);
        assertEquals(employee.getFirstName(), EMPLOYEE_FIRST_NAME);
        assertEquals(employee.getPatronicName(), EMPLOYEE_PATRONIC_NAME);
        assertEquals(employee.getLocalDate(), localDate);
    }

    @Test
    public void testToString() {
        LocalDate localDate = LocalDate.of(2019, 03, 03);
        employee.setDepartmentId(DEPARTMENT_ID);
        employee.setEmployeeId(EMPLOYEE_ID);
        employee.setLogin(EMPLOYEE_LOGIN);
        employee.setLastName(EMPLOYEE_LAST_NAME);
        employee.setFirstName(EMPLOYEE_FIRST_NAME);
        employee.setPatronicName(EMPLOYEE_PATRONIC_NAME);
        employee.setLocalDate(localDate);
        String expectedResponseEmployee = "Employee {departmentId = " + DEPARTMENT_ID + ", employeeId = " + EMPLOYEE_ID
                + ", login = " + EMPLOYEE_LOGIN + ", lastName = " + EMPLOYEE_LAST_NAME + ", firstName = " + EMPLOYEE_FIRST_NAME
                + ", patronicName = " + EMPLOYEE_PATRONIC_NAME + ", localDate = 2019-03-03}";
        assertEquals(expectedResponseEmployee, employee.toString());
    }
}