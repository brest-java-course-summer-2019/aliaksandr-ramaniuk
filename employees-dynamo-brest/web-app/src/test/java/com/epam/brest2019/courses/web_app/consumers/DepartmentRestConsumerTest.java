package com.epam.brest2019.courses.web_app.consumers;

import com.epam.brest2019.courses.model.Department;
import com.epam.brest2019.courses.service.DepartmentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.util.ArrayList;
import java.util.List;

//@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
public class DepartmentRestConsumerTest {

    private static final String DEPARTMENT_ID = "departmentId";
    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String DEPARTMENT_ACCESS_RIGHTS = "departmentAccessRights";
    private static final Integer DEPARTMENT_ID_1 = 1;
    private String url = "/departments";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentRestConsumer departmentRestConsumer;

    @Bean
        public RestTemplate restTemplate() {
        return new RestTemplate();

    }

    @Test
    public void findById() {
        Mockito.when(restTemplate.getForEntity(url + "/" + DEPARTMENT_ID_1, Department.class))
          .thenReturn(new ResponseEntity<>(createDepartmentForTest(DEPARTMENT_ID_1), HttpStatus.OK));

        Department department = departmentRestConsumer.findById(DEPARTMENT_ID_1);

        Assert.assertNotNull(department);
        Assert.assertEquals(createDepartmentForTest(DEPARTMENT_ID_1), department);

        Mockito.verify(restTemplate, Mockito.times(1))
                .getForEntity(url + "/" + DEPARTMENT_ID_1, Department.class);
        Mockito.verifyNoMoreInteractions(departmentService);
    }

    private Department createDepartmentForTest(int departmentId) {
        Department department = new Department();
        department.setDepartmentId(departmentId);
        department.setDepartmentName(DEPARTMENT_NAME + departmentId);
        department.setDepartmentAccessRights(DEPARTMENT_ACCESS_RIGHTS + departmentId);
        return department;
    }

    @Test
    public void findAll() {
        Mockito.when(restTemplate.getForEntity(url + "/", List.class))
                .thenReturn(new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK));

        List<Department> department = departmentRestConsumer.findAll();

        Assert.assertNotNull(department);
        Assert.assertEquals(new ArrayList<>(), department);

        Mockito.verify(restTemplate, Mockito.times(1))
                .getForEntity(url + "/", List.class);
        Mockito.verifyNoMoreInteractions(departmentService);
    }
}


/*

import com.epam.brest2019.courses.model.Department;
import com.epam.brest2019.courses.service.DepartmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;
import org.springframework.web.client.RestTemplate;


import java.net.URI;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
public class DepartmentRestConsumerTest {

    private static final String DEPARTMENT = "department";
    private static final String DEPARTMENTS = "departments";
    private static final String DEPARTMENT_ID = "departmentId";
    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String DEPARTMENT_ACCESS_RIGHTS = "departmentAccessRights";
    private static final Integer DEPARTMENT_ID_1 = 1;
    private static final Integer DEPARTMENT_ID_2 = 2;

    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DepartmentService departmentService;

    private MockRestServiceServer mockServer;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
                mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void findById() throws Exception {

        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI( url + "/" + DEPARTMENT_ID_1)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(createDepartmentForTest(DEPARTMENT_ID_1)))
                );

        Department department = departmentService.findById(DEPARTMENT_ID_1);

        assertNotNull(department);
        mockServer.verify();
        assertEquals(createDepartmentForTest(DEPARTMENT_ID_1), department);

    }

    private Department createDepartmentForTest(int departmentId) {
        Department department = new Department();
        department.setDepartmentId(departmentId);
        department.setDepartmentName(DEPARTMENT_NAME + departmentId);
        department.setDepartmentAccessRights(DEPARTMENT_ACCESS_RIGHTS + departmentId);
        return department;
    }
}
*/