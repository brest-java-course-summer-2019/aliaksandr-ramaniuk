package com.epam.brest2019.courses.dao;

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
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})

public class DepartmentDaoJdbcImplTest {

    private static final String DEVELOPMENT = "Тренер";
    private static final String COACH = "Тренерский штаб вратарей";
    private static final String NEW_COACH = "Новый тренерский штаб вратарей";

    @Autowired
    DepartmentDao departmentDao;

    @Test
    public void findAll() {
        List<Department> departments = departmentDao.findAll();
        assertNotNull(departments);
        assertTrue(departments.size() > 0);
    }

    @Test
    public void findById() {
        Department testFindDepartmentById = departmentDao.findById(2).get();
        assertNotNull(testFindDepartmentById);
        assertTrue(testFindDepartmentById.getDepartmentId().equals(2));
        assertEquals(testFindDepartmentById.getDepartmentName(), DEVELOPMENT);
    }

    @Test
    public void add() {
        int sizeBeforeAdd = departmentDao.findAll().size();

        Department testAddDepartment = new Department();
        testAddDepartment.setDepartmentName("Группа поддержки");

        Department newDepartment = departmentDao.add(testAddDepartment);
        int sizeAfterAdd = departmentDao.findAll().size();

        assertNotNull(newDepartment.getDepartmentId());
        assertEquals(sizeBeforeAdd + 1, sizeAfterAdd);
    }

    @Test
    public void update() {
        Department testNewDepartment = new Department(COACH);
        testNewDepartment = departmentDao.add(testNewDepartment);
        testNewDepartment.setDepartmentName(NEW_COACH);
        departmentDao.update(testNewDepartment);

        Department testUpdateDepartment = departmentDao.findById(testNewDepartment.getDepartmentId()).get();
        assertEquals(testNewDepartment.getDepartmentId(), testUpdateDepartment.getDepartmentId());
        assertEquals(testNewDepartment.getDepartmentName(), testUpdateDepartment.getDepartmentName());
    }

    @Test
    public void delete() {
        Department testNewDepartment = new Department(COACH);
        testNewDepartment = departmentDao.add(testNewDepartment);

        int sizeBeforeDelete = departmentDao.findAll().size();
        departmentDao.delete(testNewDepartment.getDepartmentId());
        int sizeAfterDelete = departmentDao.findAll().size();

        assertEquals(sizeBeforeDelete - 1, sizeAfterDelete);
    }

}
