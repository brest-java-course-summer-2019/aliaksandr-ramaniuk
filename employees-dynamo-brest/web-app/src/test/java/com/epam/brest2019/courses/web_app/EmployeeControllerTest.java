package com.epam.brest2019.courses.web_app;

import com.epam.brest2019.courses.model.Employee;
import com.epam.brest2019.courses.service.EmployeeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
public class EmployeeControllerTest {

    private static final String LOGIN = "login";
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME = "firstName";
    private static final String PATRONIC_NAME = "patronicName";

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private EmployeeService employeeService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();
    }
/*
     @Test
    public void findAll() throws Exception {
         Mockito.when(employeeService.findAll()).thenReturn(Arrays.asList(createEmployeeForTest(1), createEmployeeForTest(2)));


         Mockito.verify(employeeService).findAll();
         Mockito.verifyNoMoreInteractions(employeeService);
    }
*/
    private Employee createEmployeeForTest(int employeeId) {
        Employee employee = new Employee();

        employee.setDepartmentId(employeeId);
        employee.setEmployeeId(employeeId);
        employee.setLogin(LOGIN + employeeId);
        employee.setLastName(LAST_NAME + employeeId);
        employee.setFirstName(FIRST_NAME + employeeId);
        employee.setPatronicName(PATRONIC_NAME + employeeId);

        return employee;
    }
}
