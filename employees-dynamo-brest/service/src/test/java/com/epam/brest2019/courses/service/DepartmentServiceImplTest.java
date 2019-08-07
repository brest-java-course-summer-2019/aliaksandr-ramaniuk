package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.model.Department;

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
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})

public class DepartmentServiceImplTest {

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
        Department testFindById = departmentService.findById(1);
        assertNotNull(testFindById);
        assertTrue(testFindById.getDepartmentId().equals(1));
        assertEquals(testFindById.getDepartmentName(), "Администратор");
    }

   /* @Test
    public void add() {
        int sizeBeforeAdd = departmentService.findAll().size();

        Department testAddDepartment = new Department();
        testAddDepartment.setDepartmentName("Группа поддержки");

        Department newDepartment = departmentService.add(testAddDepartment);
        int sizeAfterAdd = departmentService.findAll().size();

        assertNotNull(newDepartment.getDepartmentId());
        assertEquals(newDepartment.getDepartmentName(), "Группа поддержки");
        assertEquals(sizeBeforeAdd + 1, sizeAfterAdd);
    }
*/
    @Test
    public void update() {
        Department testNewDepartment = new Department();
        testNewDepartment.setDepartmentId(2);
        testNewDepartment.setDepartmentName("Новый Тренер");

        departmentService.update(testNewDepartment);

        Department testUpdateDepartment = departmentService.findById(testNewDepartment.getDepartmentId());
        assertEquals(testNewDepartment.getDepartmentId(), testUpdateDepartment.getDepartmentId());
        assertEquals("Новый Тренер", testUpdateDepartment.getDepartmentName());
    }

    @Test
    public void delete() {
        Department testAddDepartment = new Department();
        testAddDepartment.setDepartmentName("Тест на удаление Department");

       // Department deleteDepartment = departmentService.add(testAddDepartment);
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
