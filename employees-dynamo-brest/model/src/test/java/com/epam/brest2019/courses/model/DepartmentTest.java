package com.epam.brest2019.courses.model;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class DepartmentTest {

    Department department = new Department();

    @Test
    public void getDepartmentId() {
        department.setDepartmentId(5);
        assertTrue(department.getDepartmentId().equals(5));
    }

    @Test
    public void getDepartmentName() {
        department.setDepartmentName("Игрок");
        assertEquals(department.getDepartmentName(), "Игрок");
    }

    @Test
    public void getCountEmployeesInDepartment() {
        department.setCountEmployeesInDepartment(10);
        assertTrue(department.getCountEmployeesInDepartment().equals(10));
    }

    /**
     * Constructor Department Test
     */
    public DepartmentTest() {
        Department departmentTest = new Department("Тест конструктора");
        assertEquals(departmentTest.getDepartmentName(), "Тест конструктора");
     }

    @Test
    public void testToString() {
        department.setDepartmentId(8);
        department.setDepartmentName("Тест конструктора");
        department.setCountEmployeesInDepartment(3);
        String expectedResponseDepartment = "Department {departmentId = 8, departmentName = Тест конструктора, countEmployeesInDepartment = 3}";
        assertEquals(expectedResponseDepartment, department.toString());
    }
}