package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.model.Department;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})
@Rollback
public class DepartmentServiceImplTest {

    private static final String DEPARTMENT_NAME = "АДМИНИСТРАТОР";
    private static final String DEPARTMENT_NAME_ADD = "NEW DEPARTMENT";
    private static final String DEPARTMENT_ACCESS_RIGHTS_ADMIN = "admin";
    private static final String DEPARTMENT_ACCESS_RIGHTS_READ = "read";

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void findAll() {
        List<Department> departments = departmentService.findAll();

        assertNotNull(departments);
        assertTrue(departments.size() > 0);
    }

    @Test
    public void findById() {
        int departmentId = 1;

        Department testFindById = departmentService.findById(departmentId);
        assertNotNull(testFindById);
        assertTrue(testFindById.getDepartmentId().equals(departmentId));
        assertEquals(testFindById.getDepartmentName(), DEPARTMENT_NAME);
        assertEquals(testFindById.getDepartmentAccessRights(), DEPARTMENT_ACCESS_RIGHTS_ADMIN);
    }

    @Test
    public void add() {
        int sizeBeforeAdd = departmentService.findAll().size();

        Department testAddDepartment = new Department();
        testAddDepartment.setDepartmentName(DEPARTMENT_NAME_ADD);
        testAddDepartment.setDepartmentAccessRights(DEPARTMENT_ACCESS_RIGHTS_READ);

        Department newDepartment = departmentService.add(testAddDepartment);
        int sizeAfterAdd = departmentService.findAll().size();

        assertNotNull(newDepartment.getDepartmentId());
        assertEquals(newDepartment.getDepartmentName(), DEPARTMENT_NAME_ADD);
        assertEquals(newDepartment.getDepartmentAccessRights(), DEPARTMENT_ACCESS_RIGHTS_READ);
        assertEquals(sizeBeforeAdd + 1, sizeAfterAdd);
    }

    @Test
    public void update() {
        int departmentId = 2;

        Department testNewDepartment = new Department();
        testNewDepartment.setDepartmentId(departmentId);
        testNewDepartment.setDepartmentName(DEPARTMENT_NAME_ADD);
        testNewDepartment.setDepartmentAccessRights(DEPARTMENT_ACCESS_RIGHTS_READ);

        departmentService.update(testNewDepartment);

        Department testUpdateDepartment = departmentService.findById(testNewDepartment.getDepartmentId());
        assertEquals(testNewDepartment.getDepartmentId(), testUpdateDepartment.getDepartmentId());
        assertEquals(DEPARTMENT_NAME_ADD, testUpdateDepartment.getDepartmentName());
        assertEquals(DEPARTMENT_ACCESS_RIGHTS_READ, testUpdateDepartment.getDepartmentAccessRights());
    }

    @Test
    public void delete() {
        Department testAddDepartment = new Department();
        testAddDepartment.setDepartmentName(DEPARTMENT_NAME_ADD);
        testAddDepartment.setDepartmentAccessRights(DEPARTMENT_ACCESS_RIGHTS_READ);

        departmentService.add(testAddDepartment);
        int sizeBeforeDelete = departmentService.findAll().size();

        departmentService.delete(testAddDepartment.getDepartmentId());

        int sizeAfterDelete = departmentService.findAll().size();

        assertEquals(sizeBeforeDelete - 1, sizeAfterDelete);
    }

    @Test
    public void findAllCountEmployeesInDepartment() {
        List<Department> departments = departmentService.findAllCountEmployeesInDepartment();

        assertNotNull(departments);
        assertTrue(departments.size() > 0);
        assertEquals(departments.size(), 6);
    }
}