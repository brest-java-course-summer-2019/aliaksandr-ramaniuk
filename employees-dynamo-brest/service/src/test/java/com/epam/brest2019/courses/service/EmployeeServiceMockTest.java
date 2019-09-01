package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.dao.EmployeeDao;
import com.epam.brest2019.courses.model.Department;
import com.epam.brest2019.courses.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceMockTest {

    @Mock
    private EmployeeDao employeeDao;

    @Captor
    private ArgumentCaptor<Employee> employeeCaptor;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @AfterEach
    void after() {
        Mockito.verifyNoMoreInteractions(employeeDao);
    }

    @Test
    public void findAll() {

        Mockito.when(employeeDao.findAll()).thenReturn(Collections.singletonList(testMethod()));
        List<Employee> employees = employeeService.findAll();

        assertNotNull(employees);
        assertTrue(employees.size() > 0);
        assertEquals(1, employees.size());

        Mockito.verify(employeeDao).findAll();
    }

    @Test
    public void findByDepartmentId() {

        int departmentId = 1;

        Mockito.when(employeeDao.findByDepartmentId(departmentId)).thenReturn(Collections.singletonList(testMethod()));
        List<Employee> employees = employeeService.findByDepartmentId(departmentId);

        assertNotNull(employees);
        assertTrue(employees.size() > 0);
        assertEquals(1, employees.size());

        Mockito.verify(employeeDao).findByDepartmentId(departmentId);
    }


    @Test
    public void findById() {

        int employeeId = 1;

        Mockito.when(employeeDao.findById(employeeId)).thenReturn(Optional.of(testMethod()));

        Employee employees = employeeService.findById(employeeId);

        assertNotNull(employees);
        assertTrue(employees.getEmployeeId().equals(employeeId));
        assertEquals(employees.getLastName(), "LastName");

        Mockito.verify(employeeDao).findById(employeeId);
    }

    @Test
    public void add() {

        Employee employeeAdd = new Employee();
        employeeAdd.setLastName("LastNameAdd");

        Mockito.when(employeeDao.add(employeeAdd)).thenReturn(employeeAdd);

        Employee employee = employeeService.add(employeeAdd);

        assertNotNull(employee);
        assertEquals("LastNameAdd", employee.getLastName());

        Mockito.verify(employeeDao).add(employeeAdd);
    }


    @Test
    public void update() {
        employeeService.update(testMethod());

        Mockito.verify(employeeDao).update(employeeCaptor.capture());

        Employee employees = employeeCaptor.getValue();
        assertNotNull(employees);
        assertEquals("LastName", employees.getLastName());
        assertTrue(employees.getEmployeeId().equals(1));
    }

    @Test
    public void delete() {

        int employeeId = 1;

        employeeService.delete(employeeId);

        Mockito.verify(employeeDao).delete(employeeId);
    }

    @Test
    public void totalCountOfEmployees() {
        int totalCountOfEmployees = 5;

        Mockito.when(employeeDao.totalCountOfEmployees()).thenReturn(totalCountOfEmployees);
        int employeesCount = employeeService.totalCountOfEmployees();

        assertNotNull(employeesCount);
        assertTrue(employeesCount > 0);
        assertEquals(5, employeesCount);

        Mockito.verify(employeeDao).totalCountOfEmployees();
    }

    @Test
    public void filterEmployee() {
        String lastName = "LastName";

        Mockito.when(employeeDao.filterEmployee(lastName)).thenReturn(Collections.singletonList(testMethod()));

        List<Employee> employees = employeeService.filterEmployee(lastName);

        assertNotNull(employees);
        assertTrue(employees.size() > 0);

        Mockito.verify(employeeDao).filterEmployee(lastName);
    }

    @Test
    public  void filterEmployeeByDate() {
        LocalDate localDate1 = LocalDate.of(2019, 01, 1);
        LocalDate localDate2 = LocalDate.of(2019, 01, 10);

        Mockito.when(employeeDao.filterEmployeeByDate(localDate1, localDate2)).thenReturn(Collections.singletonList(testMethod()));

        List<Employee> employees = employeeService.filterEmployeeByDate(localDate1, localDate2);

        assertNotNull(employees);
        assertTrue(employees.size() > 0);

        Mockito.verify(employeeDao).filterEmployeeByDate(localDate1, localDate2);
    }

    private Employee testMethod() {
        LocalDate localDate = LocalDate.of(2019, 01, 07);

        Employee employee = new Employee();
        employee.setLastName("LastName");
        employee.setEmployeeId(1);
        employee.setLocalDate(localDate);
        return employee;
    }
}