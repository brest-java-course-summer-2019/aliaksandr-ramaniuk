package com.epam.brest2019.courses.web_app.consumers;

import com.epam.brest2019.courses.model.Department;
import com.epam.brest2019.courses.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;
import org.springframework.web.client.RestTemplate;


import java.net.URI;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:app-context-test.xml",
        "classpath:/spring/applicationContext.xml"})
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

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
                mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void findById() throws Exception {

        Mockito.when(departmentService.findById(DEPARTMENT_ID_1)).thenReturn(createDepartmentForTest(DEPARTMENT_ID_1));

        mockServer.expect(
                requestTo(new URI( "http://localhost:8095/departments/" + DEPARTMENT_ID_1)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(createDepartmentForTest(DEPARTMENT_ID_1)))
                );

        Department department = departmentService.findById(DEPARTMENT_ID_1);

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
