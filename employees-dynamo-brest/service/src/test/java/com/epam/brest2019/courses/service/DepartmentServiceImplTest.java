package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.model.Department;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})
public class DepartmentServiceImplTest {

    private static final String DEVELOPMENT = "Тренер";
    private static final String COACH = "Тренерский штаб вратарей";

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
        Department testFindById = departmentService.findById(2).get();
        assertNotNull(testFindById);
        assertTrue(testFindById.getDepartmentId().equals(2));
        assertEquals(testFindById.getDepartmentName(), DEVELOPMENT);
     }

    @Test
    public void update() {
        Department testNewDepartment = new Department();
        testNewDepartment.setDepartmentId(2);
        testNewDepartment.setDepartmentName(COACH);

        departmentService.update(testNewDepartment);

        Department testUpdateDepartment = departmentService.findById(testNewDepartment.getDepartmentId()).get();
        assertEquals(testNewDepartment.getDepartmentId(), testUpdateDepartment.getDepartmentId());
        assertEquals(COACH, testUpdateDepartment.getDepartmentName());
    }

    @Test
    public void delete() {
        List<Department> departments = departmentService.findAll();
        int sizeBefore = departments.size();

        departmentService.delete(2);
        assertTrue((sizeBefore - 1) == departmentService.findAll().size());
    }
}
