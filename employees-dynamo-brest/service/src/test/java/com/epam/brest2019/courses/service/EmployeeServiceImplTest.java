package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.model.Employee;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})

public class EmployeeServiceImplTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void findAll() {
        List<Employee> employees = employeeService.findAll();

        assertNotNull(employees);
        assertTrue(employees.size() > 0);
    }

    @Test
    public void findByDepartmentId() {
        List<Employee> employees = employeeService.findByDepartmentId(2);
        assertNotNull(employeeService);
        assertTrue(employees.size() > 0);
        assertEquals(employees.size(), 2);
    }

    @Test
    public void findById() {
        LocalDate testLocalDate = LocalDate.of(2019, 01, 05);

        Employee testEmployee = employeeService.findById(5);
        assertNotNull(employeeService);
        assertTrue(testEmployee.getDepartmentId().equals(3));
        assertTrue(testEmployee.getEmployeeId().equals(5));
        assertEquals(testEmployee.getLogin(), "nekhaychik33");
        assertEquals(testEmployee.getLastName(), "Нехайчик");
        assertEquals(testEmployee.getFirstName(), "Павел");
        assertEquals(testEmployee.getPatronicName(), "Александрович");
        assertEquals(testEmployee.getLocalDate(), testLocalDate);
    }

    @Test
    public void addEmployee() {
        LocalDate localDate = LocalDate.of(2019, 07, 07);

        List<Employee> employeesSize = employeeService.findAll();
        int sizeBeforeAdd = employeesSize.size();

        Employee newEmployee = new Employee(1, "login002", "lastName002", "firstName002", "patronicName002", localDate);
        Employee AddEmployee = employeeService.add(newEmployee);
        int sizeAfterAdd = employeesSize.size();

        assertNotNull(AddEmployee.getEmployeeId());
        assertEquals(AddEmployee.getLogin(), newEmployee.getLogin());
        assertEquals(AddEmployee.getLastName(), newEmployee.getLastName());
        assertEquals(AddEmployee.getFirstName(), newEmployee.getFirstName());
        assertEquals(AddEmployee.getPatronicName(), newEmployee.getPatronicName());
        assertEquals(AddEmployee.getLocalDate(), newEmployee.getLocalDate());
        assertEquals(AddEmployee.getDepartmentId(), newEmployee.getDepartmentId());
        assertEquals(sizeBeforeAdd, sizeAfterAdd);

        List<Employee> TotalEmployeeInDepartment = employeeService.findByDepartmentId(1);
        assertEquals(TotalEmployeeInDepartment.size(), 2);
    }

    @Test
    public void update() {
        LocalDate newLocalDate = LocalDate.of(2019, 07, 17);

        Employee newEmployee = employeeService.findById(1);
        newEmployee.setLogin("newLogin");
        newEmployee.setFirstName("newFirstName");
        newEmployee.setLastName("newLastName");
        newEmployee.setPatronicName("newPatronicName");
        newEmployee.setDepartmentId(1);
        newEmployee.setLocalDate(newLocalDate);

        employeeService.update(newEmployee);
        Employee updateEmployee = employeeService.findById(newEmployee.getEmployeeId());

        assertEquals(updateEmployee.getEmployeeId(), newEmployee.getEmployeeId());
        assertEquals(updateEmployee.getLogin(), newEmployee.getLogin());
        assertEquals(updateEmployee.getLastName(), newEmployee.getLastName());
        assertEquals(updateEmployee.getFirstName(), newEmployee.getFirstName());
        assertEquals(updateEmployee.getPatronicName(), newEmployee.getPatronicName());
        assertEquals(updateEmployee.getLocalDate(), newEmployee.getLocalDate());
        assertEquals(updateEmployee.getDepartmentId(), newEmployee.getDepartmentId());
    }

    @Test
    public void delete() {
        LocalDate newLocalDate = LocalDate.of(2019, 07, 07);

        Employee newEmployee = new Employee(5, "newLogin", "newLastName", "newFirstName", "newPatronicName", newLocalDate);
   //     employeeService.add(newEmployee);

        int sizeBeforeDelete = employeeService.findAll().size();

        employeeService.delete(newEmployee.getEmployeeId());
        int sizeAfterDelete = employeeService.findAll().size();

        assertEquals(sizeBeforeDelete - 1, sizeAfterDelete);
    }

    @Test
    public void totalCountOfEmployees() {
        int totalCountOfEmployees = employeeService.totalCountOfEmployees();
        int countEmployeeInDataScript = 11;

        assertNotNull(totalCountOfEmployees);
   //     assertEquals(totalCountOfEmployees, countEmployeeInDataScript);
    }

    @Test
    public void filterEmployee() {
        int CountOfEmployee2 = 2;
        List<Employee> employees2 = employeeService.filterEmployee("РО");

        int CountOfEmployees1 = 1;
        List<Employee> employees1 = employeeService.filterEmployee("РОМАНЮК");

        assertNotNull(employees2);
        assertEquals(employees2.size(), CountOfEmployee2);

        assertNotNull(employees1);
        assertEquals(employees1.size(), CountOfEmployees1);
    }

    @Test
    public  void filterEmployeeByDate() {
        LocalDate localDate1 = LocalDate.of(2019, 01, 1);
        LocalDate localDate2 = LocalDate.of(2019, 01, 10);

        List<Employee> employees = employeeService.filterEmployeeByDate(localDate1, localDate2);

        assertNotNull(employees);
        assertEquals(employees.size(), 11);
    }
}