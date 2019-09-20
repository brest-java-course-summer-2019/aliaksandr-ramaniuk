package com.epam.brest2019.courses.web_app.consumers;

import com.epam.brest2019.courses.model.Employee;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class EmployeeRestConsumerTest {

    @Mock
    private RestTemplate mockRestTemplate;

    private EmployeeRestConsumer employeeRestConsumerTest;

    private Employee employee;

    private static final String EMPLOYEE_LOGIN = "login";
    private static final String EMPLOYEE_LAST_NAME = "lastName";
    private static final String EMPLOYEE_FIRST_NAME = "firstName";
    private static final String EMPLOYEE_PATRONIC_NAME = "patronicName";
    private static final Integer EMPLOYEE_ID_1 = 1;



    @BeforeEach
    public void setUp() {
        initMocks(this);
        employeeRestConsumerTest = new EmployeeRestConsumer("url", mockRestTemplate);
    }

    @Test
    public void findAll() throws Exception {
        List<Employee> employees = Arrays.asList();
        Mockito.when(mockRestTemplate.getForEntity("url/", List.class))
                .thenReturn(new ResponseEntity<>(new ArrayList(), HttpStatus.OK));

        List<Employee> employee = employeeRestConsumerTest.findAll();

        Assert.assertEquals(employees, employee);
    }


    @Test
    public void findById() throws Exception {

        Employee employees = createEmployeeForTest(EMPLOYEE_ID_1);

        Mockito.when(mockRestTemplate.getForEntity("url/" + EMPLOYEE_ID_1, Employee.class))
                .thenReturn(new ResponseEntity<>(createEmployeeForTest(EMPLOYEE_ID_1), HttpStatus.OK));

        Employee employee = employeeRestConsumerTest.findById(EMPLOYEE_ID_1);

        assertEquals(employees.getEmployeeId(), employee.getEmployeeId());
        assertEquals(employees.getDepartmentId(), employee.getDepartmentId());
    }


    @Test
    public void add() throws Exception {

        createEmployeeForTest(EMPLOYEE_ID_1);
        Mockito.when(mockRestTemplate.postForEntity("url", employee, Employee.class))
                .thenReturn(new ResponseEntity<>(createEmployeeForTest(EMPLOYEE_ID_1), HttpStatus.OK));

        employeeRestConsumerTest.add(employee);
    }


    @Test
    public void update() throws Exception {

        createEmployeeForTest(EMPLOYEE_ID_1);
        employeeRestConsumerTest.update(employee);

        Mockito.verify(mockRestTemplate).put("url", employee);
    }

    @Test
    public void delete() throws Exception {

        employeeRestConsumerTest.delete(EMPLOYEE_ID_1);

        Mockito.verify(mockRestTemplate).delete("url/" + EMPLOYEE_ID_1);
    }


    @Test
    public void filterEmployee() throws Exception {

        List<Employee> employees = Arrays.asList(createEmployeeForTest(EMPLOYEE_ID_1));

        Mockito.when(mockRestTemplate.getForEntity("url2/" + EMPLOYEE_LAST_NAME + EMPLOYEE_ID_1, List.class))
                .thenReturn(new ResponseEntity<>(new ArrayList(), HttpStatus.OK));

        List<Employee> employee = employeeRestConsumerTest.filterEmployee(EMPLOYEE_LAST_NAME+ EMPLOYEE_ID_1);

      }

    @Test
    public void filterEmployeeByDate() throws Exception {
        LocalDate localDateStart = LocalDate.of(2019, 9, 9);
        LocalDate localDateEnd = LocalDate.of(2019, 9, 10);
        List<Employee> employees = Arrays.asList();

        Mockito.when(mockRestTemplate.getForEntity("url/" + localDateStart + "/" + localDateEnd, List.class))
                .thenReturn(new ResponseEntity<>(new ArrayList(), HttpStatus.OK));

        List<Employee> employee = employeeRestConsumerTest.filterEmployeeByDate(localDateStart, localDateEnd);

        Assert.assertEquals(employees, employee);
    }

    private Employee createEmployeeForTest(int employeeId) {
        Employee employee = new Employee();

        employee.setDepartmentId(employeeId);
        employee.setEmployeeId(employeeId);
        employee.setLogin(EMPLOYEE_LOGIN + employeeId);
        employee.setLastName(EMPLOYEE_LAST_NAME + employeeId);
        employee.setFirstName(EMPLOYEE_FIRST_NAME + employeeId);
        employee.setPatronicName(EMPLOYEE_PATRONIC_NAME + employeeId);
        employee.setDepartmentId(employeeId);

        return employee;
    }


}
