package com.epam.brest2019.courses.rest_app;

import com.epam.brest2019.courses.model.Employee;
import com.epam.brest2019.courses.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class EmployeeRestControllerTest {

    private static final String LOGIN = "login";
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME = "firstName";
    private static final String PATRONIC_NAME = "patronicName";

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


    @Test
    public void findAll() throws Exception {

        Mockito.when(employeeService.findAll()).thenReturn(Arrays.asList(createEmployeeForTest(1), createEmployeeForTest(2)));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/employees")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].departmentId", Matchers.is(1)))
                .andExpect(jsonPath("$[0].employeeId", Matchers.is(1)))
                .andExpect(jsonPath("$[0].login", Matchers.is(LOGIN+1)))
                .andExpect(jsonPath("$[0].lastName", Matchers.is(LAST_NAME+1)))
                .andExpect(jsonPath("$[0].firstName", Matchers.is(FIRST_NAME+1)))
                .andExpect(jsonPath("$[0].patronicName", Matchers.is(PATRONIC_NAME+1)))
                .andExpect(jsonPath("$[1].departmentId", Matchers.is(2)))
                .andExpect(jsonPath("$[1].employeeId", Matchers.is(2)))
                .andExpect(jsonPath("$[1].login", Matchers.is(LOGIN+2)))
                .andExpect(jsonPath("$[1].lastName", Matchers.is(LAST_NAME+2)))
                .andExpect(jsonPath("$[1].firstName", Matchers.is(FIRST_NAME+2)))
                .andExpect(jsonPath("$[1].patronicName", Matchers.is(PATRONIC_NAME+2)))
         ;

        Mockito.verify(employeeService).findAll();
        Mockito.verifyNoMoreInteractions(employeeService);

    }

    @Test
    public void findById() throws Exception {
        int employeeId = 1;
/*
        Mockito.when(employeeService.findById(employeeId)).thenReturn(createEmployeeForTest(employeeId));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/employees/{id}", employeeId)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId"). value(employeeId))
                .andExpect(jsonPath("$.login", Matchers.is(LOGIN+employeeId)))
                .andExpect(jsonPath("$.lastName", Matchers.is(LAST_NAME+employeeId)))
                .andExpect(jsonPath("$.firstName", Matchers.is(FIRST_NAME+employeeId)))
                .andExpect(jsonPath("$.patronicName", Matchers.is(PATRONIC_NAME+employeeId)))
        ;

        Mockito.verify(employeeService).findById(employeeId);
*/
    }


    @Test
    public void add() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/employee_Add")
                .content(asJsonString(new Employee ()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn().getResponse()
        ;
    }

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void update() throws Exception {
        int employeeId = 1;

        Employee employee = createEmployeeForTest(employeeId);
        String json = new ObjectMapper().writeValueAsString(employee);
   /*
        mockMvc.perform(MockMvcRequestBuilders
                .put("/employee_Edit")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isAccepted())
        ;


    */
    }

    @Test
    public void delete() throws Exception {
        int employeeId = 1;

        Mockito.when(employeeService.findById(createEmployeeForTest(employeeId).getDepartmentId())).thenReturn(createEmployeeForTest(employeeId));
        doNothing().when(employeeService).delete(createEmployeeForTest(employeeId).getDepartmentId());

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/employees/{id}", employeeId))
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;

        Mockito.verify(employeeService).delete(employeeId);
        Mockito.verify(employeeService, times(1)).delete(employeeId);
        Mockito.verifyNoMoreInteractions(employeeService);
    }

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
