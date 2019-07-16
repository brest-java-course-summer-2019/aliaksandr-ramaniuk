package com.epam.brest2019.courses;

import org.junit.Assert;
import org.junit.Test;


public class EmployeeTest {

    Employee employee = new Employee();

    @Test
    public void getEmployeeId() {
        employee.setEmployeeId(10);
        Assert.assertTrue(employee.getEmployeeId().equals(10));
    }

    @Test
    public void getDepartmentId() {
        employee.setDepartmentId(16);
        Assert.assertTrue(employee.getDepartmentId().equals(16));
    }

    @Test
    public void getLogin() {
        employee.setLogin("login");
        Assert.assertTrue(employee.getLogin().equals("login"));
    }


    @Test
    public void getFirstName() {
        employee.setFirstName("FirstName");
        Assert.assertTrue(employee.getFirstName().equals("FirstName"));
    }

    @Test
    public void getLastName() {
        employee.setLastName("LastName");
        Assert.assertTrue(employee.getLastName().equals("LastName"));
    }

    @Test
    public void getPatronicName() {
        employee.setPatronicName("PatronicName");
        Assert.assertTrue(employee.getPatronicName().equals("PatronicName"));
    }

    @Test
    public void getDepartmentName() {
        employee.setDepartmentName("DepartmentName");
        Assert.assertTrue(employee.getDepartmentName().equals("DepartmentName"));
    }
}
