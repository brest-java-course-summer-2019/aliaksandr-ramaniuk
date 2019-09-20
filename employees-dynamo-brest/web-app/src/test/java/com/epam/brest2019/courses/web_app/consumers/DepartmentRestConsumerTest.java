package com.epam.brest2019.courses.web_app.consumers;

import com.epam.brest2019.courses.model.Department;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class DepartmentRestConsumerTest {

    @Mock
    private RestTemplate mockRestTemplate;

    private DepartmentRestConsumer departmentRestConsumerTest;

    private Department department;

    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String DEPARTMENT_ACCESS_RIGHTS = "departmentAccessRights";
    private static final Integer DEPARTMENT_ID_1 = 1;


    @BeforeEach
    public void setUp() {
        initMocks(this);
        departmentRestConsumerTest = new DepartmentRestConsumer("url", mockRestTemplate);
    }


    @Test
    public void findAll() throws Exception {
        List<Department> departments = Arrays.asList();
        Mockito.when(mockRestTemplate.getForEntity("url/", List.class))
                .thenReturn(new ResponseEntity<>(new ArrayList(), HttpStatus.OK));

        List<Department> department = departmentRestConsumerTest.findAll();

        Assert.assertEquals(departments, department);
    }


    @Test
    public void findById() throws Exception {

        Department departments = createDepartmentForTest(DEPARTMENT_ID_1);

        Mockito.when(mockRestTemplate.getForEntity("url/" + DEPARTMENT_ID_1, Department.class))
                .thenReturn(new ResponseEntity<>(createDepartmentForTest(DEPARTMENT_ID_1), HttpStatus.OK));

        Department department = departmentRestConsumerTest.findById(DEPARTMENT_ID_1);

        assertEquals(departments.getDepartmentId(), department.getDepartmentId());
        assertEquals(departments.getDepartmentName(), department.getDepartmentName());
        assertEquals(departments.getDepartmentAccessRights(), department.getDepartmentAccessRights());
    }


    @Test
    public void add() throws Exception {

        createDepartmentForTest(DEPARTMENT_ID_1);
        Mockito.when(mockRestTemplate.postForEntity("url", department, Department.class))
                .thenReturn(new ResponseEntity<>(createDepartmentForTest(DEPARTMENT_ID_1), HttpStatus.OK));

        departmentRestConsumerTest.add(department);
    }


    @Test
    public void update() throws Exception {

        createDepartmentForTest(DEPARTMENT_ID_1);
        departmentRestConsumerTest.update(department);

        Mockito.verify(mockRestTemplate).put("url", department);
    }


    @Test
    public void delete() throws Exception {

        departmentRestConsumerTest.delete(DEPARTMENT_ID_1);

        Mockito.verify(mockRestTemplate).delete("url/" + DEPARTMENT_ID_1);
    }


    @Test
    public void findAllCountEmployeesInDepartment() throws Exception {
        List<Department> departments = Arrays.asList();
        Mockito.when(mockRestTemplate.getForEntity("url/with_total_count_employees", List.class))
                .thenReturn(new ResponseEntity<>(new ArrayList(), HttpStatus.OK));

        List<Department> department = departmentRestConsumerTest.findAllCountEmployeesInDepartment();

        Assert.assertEquals(departments, department);
    }

    private Department createDepartmentForTest(int departmentId) {
        Department department = new Department();
        department.setDepartmentId(departmentId);
        department.setDepartmentName(DEPARTMENT_NAME + departmentId);
        department.setDepartmentAccessRights(DEPARTMENT_ACCESS_RIGHTS + departmentId);
        return department;
    }

}