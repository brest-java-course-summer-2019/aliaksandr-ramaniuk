package com.epam.brest2019.courses.model;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class DepartmentTest {

    private static final Integer DEPARTMENT_ID = 8;
    private static final String DEPARTMENT_NAME = "department name";
    private static final String DEPARTMENT_ACCESS_RIGHTS = "department access rights";

    Department department = new Department();

    @Test
    public void getDepartmentId() {
        department.setDepartmentId(DEPARTMENT_ID);
        assertTrue(department.getDepartmentId().equals(DEPARTMENT_ID));
    }

    @Test
    public void getDepartmentName() {
        department.setDepartmentName(DEPARTMENT_NAME);
        assertEquals(department.getDepartmentName(), DEPARTMENT_NAME);
    }

    @Test
    public void getDepartmentAccessRights() {
        department.setDepartmentAccessRights(DEPARTMENT_ACCESS_RIGHTS);
        assertEquals(department.getDepartmentAccessRights(), DEPARTMENT_ACCESS_RIGHTS);
    }

    /**
     * Constructor Department Test
     */
    public DepartmentTest() {
        Department departmentTest = new Department(DEPARTMENT_NAME, DEPARTMENT_ACCESS_RIGHTS);

        assertEquals(departmentTest.getDepartmentName(), DEPARTMENT_NAME);
        assertEquals(departmentTest.getDepartmentAccessRights(), DEPARTMENT_ACCESS_RIGHTS);
     }

    @Test
    public void testToString() {
        department.setDepartmentId(DEPARTMENT_ID);
        department.setDepartmentName(DEPARTMENT_NAME);
        department.setDepartmentAccessRights(DEPARTMENT_ACCESS_RIGHTS);
        String expectedResponseDepartment = "Department {departmentId = " + DEPARTMENT_ID + ", departmentName = "
                + DEPARTMENT_NAME + ", departmentAccessRights = " + DEPARTMENT_ACCESS_RIGHTS + "}";
        assertEquals(expectedResponseDepartment, department.toString());
    }
}