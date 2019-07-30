package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.model.Department;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})

public class DepartmentServiceImplTest {

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void findAll() {
       List<Department> departments = departmentService.findAll();
        assertNotNull(departments);
        assertFalse(departments.isEmpty());
    }

    @Test
    public void findById() {
        List<Department> departments = departmentService.findAll();
        Assertions.assertNotNull(departments);

        int id = departments.get(0).getDepartmentId();

        Optional<Department> department = departmentService.findById(id);
        Assertions.assertTrue(department.isPresent());
    }
}
