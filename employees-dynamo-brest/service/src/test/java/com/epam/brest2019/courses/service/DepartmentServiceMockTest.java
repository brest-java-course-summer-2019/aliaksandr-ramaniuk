package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.dao.DepartmentDao;
import com.epam.brest2019.courses.model.Department;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceMockTest {

    @Mock
    private DepartmentDao departmentDao;

    @Captor
    private ArgumentCaptor<Department> departmentCaptor;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @AfterEach
    void after() {
        Mockito.verifyNoMoreInteractions(departmentDao);
    }

    @Test
    void findAll() {
        Mockito.when(departmentDao.findAll()).thenReturn(Collections.singletonList(testMethod()));
        List<Department> resultTest = departmentService.findAll();

        assertNotNull(resultTest);
        assertTrue(resultTest.size() > 0);
        assertEquals(1, resultTest.size());

        Mockito.verify(departmentDao).findAll();
    }


    @Test
    public void findById() {
        int departmentId = 1;

        Mockito.when(departmentDao.findById(departmentId)).thenReturn(Optional.of(testMethod()));
        Department department = departmentService.findById(departmentId);

        assertNotNull(department);
        assertEquals("departmentNameTest", department.getDepartmentName());

        Mockito.verify(departmentDao).findById(departmentId);
    }

    @Test
    public void add() {

        Department departmentAdd = new Department("NAME", "read");

        Mockito.when(departmentDao.add(departmentAdd)).thenReturn(departmentAdd);

        Department department = departmentService.add(departmentAdd);

        assertNotNull(department);
        assertEquals("NAME", department.getDepartmentName());

        Mockito.verify(departmentDao, Mockito.times(1)).add(departmentAdd);

    }

    @Test
    public void update() {
        departmentService.update(testMethod());

        Mockito.verify(departmentDao).update(departmentCaptor.capture());

        Department department = departmentCaptor.getValue();
        assertNotNull(department);
        assertEquals("departmentNameTest", department.getDepartmentName());
        assertTrue(department.getDepartmentId().equals(1));
    }

    @Test
    void delete() {
        int departmentId = 1;
        departmentService.delete(departmentId);
        Mockito.verify(departmentDao).delete(departmentId);
    }

    @Test
    void findAllCountEmployeesInDepartment() {

        Mockito.when(departmentDao.findAllCountEmployeesInDepartment()).thenReturn(Collections.singletonList(testMethod()));
        List<Department> resultTest = departmentService.findAllCountEmployeesInDepartment();

        assertNotNull(resultTest);
        assertTrue(resultTest.size() > 0);
        assertEquals(resultTest.size(), 1);

        Mockito.verify(departmentDao).findAllCountEmployeesInDepartment();
    }

    private Department testMethod() {
        Department department = new Department();
        department.setDepartmentName("departmentNameTest");
        department.setDepartmentId(1);
        return department;
    }
}
