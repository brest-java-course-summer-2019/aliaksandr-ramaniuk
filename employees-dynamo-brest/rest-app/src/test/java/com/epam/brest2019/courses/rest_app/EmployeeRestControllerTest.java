package com.epam.brest2019.courses.rest_app;

import com.epam.brest2019.courses.model.Employee;
import com.epam.brest2019.courses.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class EmployeeRestControllerTest {
  //  private static final String DEPARTMENT_NAME = "departmentName";
    //private static final String DEPARTMENT_ACCESS_RIGHTS = " departmentAccessRights";

    @Autowired
    private EmployeeRestController employeeRestController;

    @Autowired
    private EmployeeService employeeService;

    ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @BeforeEach
    void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @AfterEach
    void after() {
        Mockito.reset(employeeService);
    }


    private Employee createEmployeeForTest(int employeeId) {
        Employee employee = new Employee();
        employee.setDepartmentName(DEPARTMENT_NAME + departmentId);
        department.setDepartmentId(departmentId);
        department.setDepartmentAccessRights(DEPARTMENT_ACCESS_RIGHTS);
        return department;
    }
}
