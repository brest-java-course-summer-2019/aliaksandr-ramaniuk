package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Department;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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
    public void getDepartmentById() {
        Department testGetDepartmentById = departmentDao.findById(2).get();
        assertNotNull(testGetDepartmentById);
        assertTrue(testGetDepartmentById.getDepartmentId().equals(2));
        assertTrue(testGetDepartmentById.getDepartmentName().equals(DEVELOPMENT));
    }

    @Test
    public void addDepartment() {
        Department testAddDepartment = new Department();
            testAddDepartment.setDepartmentName("Группа поддержки");

        Department newDepartment = departmentDao.add(testAddDepartment);
        Assert.assertNotNull(newDepartment.getDepartmentId());
    }

    @Test
    public void updateDepartment() {
      Department testNewDepartment = new Department(COACH);
      testNewDepartment = departmentDao.add(testNewDepartment);
      testNewDepartment.setDepartmentName(NEW_COACH);
      departmentDao.update(testNewDepartment);

      Department testUpdateDepartment = departmentDao.findById(testNewDepartment.getDepartmentId()).get();
      assertTrue(testNewDepartment.getDepartmentId().equals(testUpdateDepartment.getDepartmentId()));
      assertTrue(testNewDepartment.getDepartmentName().equals(testUpdateDepartment.getDepartmentName()));



    }

    @Test
    public void deleteDepartment() {
       Department testNewDepartment = new Department(COACH);
       testNewDepartment = departmentDao.add(testNewDepartment);

        List<Department> departments = departmentDao.findAll();
        int sizeBefore = departments.size();
        departmentDao.delete(testNewDepartment.getDepartmentId());
        assertTrue((sizeBefore - 1) == departmentDao.findAll().size());
    }

}
