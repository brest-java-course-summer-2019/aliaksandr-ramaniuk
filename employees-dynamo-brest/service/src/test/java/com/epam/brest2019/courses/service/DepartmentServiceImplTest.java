package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.model.Department;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@ExtendWith(SpringExtension.class)
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

  /*  @Test
    public void findById() {
        List<Department> departments = departmentService.findAll();
        Assertions.assertNotNull(departments);

        int id = departments.get(0).getDepartmentId();

        Optional<Department> department = departmentService.findById(id);
        Assertions.assertTrue(department.isPresent());
    }
    */

}
