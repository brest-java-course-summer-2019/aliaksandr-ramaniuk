package com.epam.brest2019.courses.dao;

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
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
@Rollback
public class DepartmentDaoJdbcImplTest {

    private static final String DEPARTMENT = "ТРЕНЕР";
    private static final String NEW_DEPARTMENT = "NEW DEPARTMENT";
    private static final String UPDATE_DEPARTMENT = "UPDATE DEPARTMENT";
    private static final String ACCESS_RIGHTS = "read";

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
        assertEquals(testFindDepartmentById.getDepartmentName(), DEPARTMENT);
    }

    @Test
    public void add() {
        int sizeBeforeAdd = departmentDao.findAll().size();

        Department testAddDepartment = new Department();
        testAddDepartment.setDepartmentName(NEW_DEPARTMENT);
        testAddDepartment.setDepartmentAccessRights(ACCESS_RIGHTS);

        Department newDepartment = departmentDao.add(testAddDepartment);
        int sizeAfterAdd = departmentDao.findAll().size();

        assertNotNull(newDepartment.getDepartmentId());
        assertEquals(sizeBeforeAdd + 1, sizeAfterAdd);
    }

    @Test
    public void update() {
        Department testNewDepartment = new Department(NEW_DEPARTMENT, ACCESS_RIGHTS);
        departmentDao.add(testNewDepartment);
        testNewDepartment.setDepartmentName(UPDATE_DEPARTMENT);
        departmentDao.update(testNewDepartment);

        Department testUpdateDepartment = departmentDao.findById(testNewDepartment.getDepartmentId()).get();
        assertEquals(testNewDepartment.getDepartmentId(), testUpdateDepartment.getDepartmentId());
        assertEquals(testNewDepartment.getDepartmentName(), testUpdateDepartment.getDepartmentName());
    }

    @Test
    public void delete() {
        Department testNewDepartment = new Department(NEW_DEPARTMENT, ACCESS_RIGHTS);
        departmentDao.add(testNewDepartment);

        int sizeBeforeDelete = departmentDao.findAll().size();
        departmentDao.delete(testNewDepartment.getDepartmentId());
        int sizeAfterDelete = departmentDao.findAll().size();

        assertEquals(sizeBeforeDelete - 1, sizeAfterDelete);
    }

    @Test
    public void findAllCountEmployeesInDepartment() {
        List<Department> departments = departmentDao.findAllCountEmployeesInDepartment();

        assertNotNull(departments);
        assertTrue(departments.size() > 0);
    }
}
