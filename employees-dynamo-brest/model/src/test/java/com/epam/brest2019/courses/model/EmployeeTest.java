package com.epam.brest2019.courses.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EmployeeTest {

    Employee employee = new Employee();

    @Test
    public void getEmployeeId() {
        employee.setEmployeeId(10);
        assertTrue(employee.getEmployeeId().equals(10));
    }

    @Test
    public void getDepartmentId() {
        employee.setDepartmentId(16);
        assertTrue(employee.getDepartmentId().equals(16));
    }

    @Test
    public void getLogin() {
        employee.setLogin("login");
        assertEquals(employee.getLogin(),"login");
    }


    @Test
    public void getFirstName() {
        employee.setFirstName("FirstName");
        assertEquals(employee.getFirstName(), "FirstName");
    }

    @Test
    public void getLastName() {
        employee.setLastName("LastName");
        assertEquals(employee.getLastName(),"LastName");
    }

    @Test
    public void getPatronicName() {
        employee.setPatronicName("PatronicName");
        assertEquals(employee.getPatronicName(),"PatronicName");
    }

    /**
     * Constructor Employee Test
     */
    public EmployeeTest() {
        employee = new Employee(11,"loginTest","LastNameTest", "FirstNameTest", "PatronicNameTest");
        assertTrue(employee.getDepartmentId().equals(11));
        assertEquals(employee.getLogin(),"loginTest");
        assertEquals(employee.getLastName(), "LastNameTest");
        assertEquals(employee.getFirstName(), "FirstNameTest");
        assertEquals(employee.getPatronicName(), "PatronicNameTest");
    }

    @Test
    public void testToString() {
        employee.setDepartmentId(12);
        employee.setEmployeeId(12);
        employee.setLogin("login");
        employee.setLastName("LastName");
        employee.setFirstName("FirstName");
        employee.setPatronicName("PatronicName");
        String expectedResponseEmployee = "Employee {departmentId = 12, employeeId = 12, login = login, lastName = LastName, firstName = FirstName, patronicName = PatronicName}";
        assertEquals(expectedResponseEmployee, employee.toString());
    }
}
