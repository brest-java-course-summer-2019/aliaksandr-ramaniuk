package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Employee;

import org.junit.After;
import org.junit.Before;
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
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})

public class EmployeeDaoJdbcImplTest {

    @Autowired
    EmployeeDao employeeDao;

    private Employee employee;

    @Before
    public void changes() {
        LocalDate localDate = LocalDate.of(2019, 06, 06);
        employee = new Employee(1, "loginTest", "lastNameTest", "firstNameTest", "patronicNameTest", localDate);
        employee = employeeDao.add(employee);
    }

    @After
    public void cleanChanges() {
        employeeDao.delete(employee.getEmployeeId());
    }


    @Test
    public void findAll() {
        List<Employee> employees = employeeDao.findAll();

        assertNotNull(employees);
        assertTrue(employees.size() > 0);
    }

    @Test
    public void findByDepartmentId() throws Exception {
        List<Employee> employees = employeeDao.findByDepartmentId(3);
        assertNotNull(employeeDao);
        assertTrue(employees.size() > 0);
        assertEquals(employees.size(), 3);
    }

    @Test
    public void findById() {
        LocalDate testLocalDate = LocalDate.of(2019, 06, 06);

        Employee testEmployee = employeeDao.findById(employee.getEmployeeId()).get();
        assertNotNull(employeeDao);
        assertEquals(testEmployee.getEmployeeId(), employee.getEmployeeId());
        assertTrue(testEmployee.getDepartmentId().equals(1));
        assertEquals(testEmployee.getLogin(), "loginTest");
        assertEquals(testEmployee.getLastName(), "lastNameTest");
        assertEquals(testEmployee.getFirstName(), "firstNameTest");
        assertEquals(testEmployee.getPatronicName(), "patronicNameTest");
        assertEquals(testEmployee.getLocalDate(), testLocalDate);
    }

    @Test
    public void add() {
        LocalDate localDate = LocalDate.of(2019, 07, 07);

        List<Employee> employees = employeeDao.findAll();
        int sizeBefore = employees.size();
        Employee employee = new Employee(1, "login002", "lastName002", "firstName002", "patronicName002", localDate);
        Employee newEmployee = employeeDao.add(employee);
        assertNotNull(newEmployee.getEmployeeId());
        assertEquals(newEmployee.getLogin(), employee.getLogin());
        assertEquals(newEmployee.getLastName(), employee.getLastName());
        assertEquals(newEmployee.getFirstName(), employee.getFirstName());
        assertEquals(newEmployee.getPatronicName(), employee.getPatronicName());
        assertEquals(newEmployee.getLocalDate(), employee.getLocalDate());
        assertEquals(newEmployee.getDepartmentId(), employee.getDepartmentId());
        assertTrue((sizeBefore + 1) == employeeDao.findAll().size());
    }

    @Test
    public void update() throws Exception {
        LocalDate newLocalDate = LocalDate.of(2019, 07, 17);

        employee.setLogin("newLogin");
        employee.setFirstName("newFirstName");
        employee.setLastName("newLastName");
        employee.setPatronicName("newPatronicName");
        employee.setDepartmentId(2);
        employee.setLocalDate(newLocalDate);

        employeeDao.update(employee);

        Employee updateEmployee = employeeDao.findById(employee.getEmployeeId()).get();
        assertEquals(updateEmployee.getEmployeeId(), employee.getEmployeeId());
        assertEquals(updateEmployee.getLogin(), employee.getLogin());
        assertEquals(updateEmployee.getLastName(), employee.getLastName());
        assertEquals(updateEmployee.getFirstName(), employee.getFirstName());
        assertEquals(updateEmployee.getPatronicName(), employee.getPatronicName());
        assertEquals(updateEmployee.getLocalDate(), employee.getLocalDate());
        assertEquals(updateEmployee.getDepartmentId(), employee.getDepartmentId());
    }

    @Test
    public void delete() {
        LocalDate localDate = LocalDate.of(2019, 07, 07);

        Employee employee = new Employee(1, "login002", "firstName002", "lastName002", "patronicName002", localDate);
        employeeDao.add(employee);
        List<Employee> employees = employeeDao.findAll();
        int sizeBefore = employees.size();
        employeeDao.delete(employee.getEmployeeId());
        assertTrue((sizeBefore - 1) == employeeDao.findAll().size());
    }

    /*   @Test
       public void filterEmployee(){
         / List<Employee> employees = employeeDao.filterEmployee("Нехайчик");

           assertNotNull(employees);
           assertTrue(employees.size() > 0);


       }

   */
    @Test
    public void totalCountOfEmployees() {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.queryForObject(


        @Test
        public void findByDepartmentId () throws Exception {
            List<Employee> employees = employeeDao.findByDepartmentId(3);
            assertNotNull(employeeDao);
            assertTrue(employees.size() > 0);
            assertEquals(employees.size(), 3);
        }


    }