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

import java.util.Arrays;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class EmployeeRestControllerTest {

//    private static final String EMPLOYEES = "employees";
//    private static final String EMPLOYEE_ID = "employeeId";
    private static final String LOGIN = "login";
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME = "firstName";
    private static final String PATRONIC_NAME = "patronicName";
    private static final Integer EMPLOYEE_ID_1 = 1;
    private static final Integer EMPLOYEE_ID_2 = 2;
    private static final Integer DEPARTMENT_ID_1 = 1;
    private static final Integer DEPARTMENT_ID_2 = 2;

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

        Mockito.when(employeeService.findAll()).thenReturn(Arrays.asList(createEmployeeForTest(EMPLOYEE_ID_1), createEmployeeForTest(EMPLOYEE_ID_2)));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/employees")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].departmentId", Matchers.is(DEPARTMENT_ID_1)))
                .andExpect(jsonPath("$[0].employeeId", Matchers.is(EMPLOYEE_ID_1)))
                .andExpect(jsonPath("$[0].login", Matchers.is(LOGIN + EMPLOYEE_ID_1)))
                .andExpect(jsonPath("$[0].lastName", Matchers.is(LAST_NAME + EMPLOYEE_ID_1)))
                .andExpect(jsonPath("$[0].firstName", Matchers.is(FIRST_NAME + EMPLOYEE_ID_1)))
                .andExpect(jsonPath("$[0].patronicName", Matchers.is(PATRONIC_NAME + EMPLOYEE_ID_1)))
                .andExpect(jsonPath("$[1].departmentId", Matchers.is(DEPARTMENT_ID_2)))
                .andExpect(jsonPath("$[1].employeeId", Matchers.is(EMPLOYEE_ID_2)))
                .andExpect(jsonPath("$[1].login", Matchers.is(LOGIN + EMPLOYEE_ID_2)))
                .andExpect(jsonPath("$[1].lastName", Matchers.is(LAST_NAME + EMPLOYEE_ID_2)))
                .andExpect(jsonPath("$[1].firstName", Matchers.is(FIRST_NAME + EMPLOYEE_ID_2)))
                .andExpect(jsonPath("$[1].patronicName", Matchers.is(PATRONIC_NAME + EMPLOYEE_ID_2)))
        ;

        Mockito.verify(employeeService).findAll();
        Mockito.verifyNoMoreInteractions(employeeService);

    }

    @Test
    public void findById() throws Exception {

        Mockito.when(employeeService.findById(EMPLOYEE_ID_1)).thenReturn(createEmployeeForTest(EMPLOYEE_ID_1));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/employees/{employeeId}", EMPLOYEE_ID_1)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentId").value(DEPARTMENT_ID_1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").value(EMPLOYEE_ID_1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.login").value(LOGIN + EMPLOYEE_ID_1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is(LAST_NAME + EMPLOYEE_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is(FIRST_NAME + EMPLOYEE_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.patronicName", Matchers.is(PATRONIC_NAME + EMPLOYEE_ID_1)))
        ;

        Mockito.verify(employeeService).findById(EMPLOYEE_ID_1);
        Mockito.verifyNoMoreInteractions(employeeService);
    }

//    @Test
//    public void findByDepartmentId() throws Exception {
//
//        Mockito.when(employeeService.findByDepartmentId(DEPARTMENT_ID_1)).thenReturn(Arrays.asList(createEmployeeForTest(EMPLOYEE_ID_1)));
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/employees/{departmentId}", DEPARTMENT_ID_1)
//                .accept(MediaType.APPLICATION_JSON_UTF8))
//                .andDo(print())
//                .andExpect(status().isOk())
//         //       .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("$[0].departmentId", Matchers.is(DEPARTMENT_ID_1)))
//                .andExpect(jsonPath("$[0].employeeId", Matchers.is(EMPLOYEE_ID_1)))
//                .andExpect(jsonPath("$[0].login", Matchers.is(LOGIN + EMPLOYEE_ID_1)))
//                .andExpect(jsonPath("$[0].lastName", Matchers.is(LAST_NAME + EMPLOYEE_ID_1)))
//                .andExpect(jsonPath("$[0].firstName", Matchers.is(FIRST_NAME + EMPLOYEE_ID_1)))
//                .andExpect(jsonPath("$[0].patronicName", Matchers.is(PATRONIC_NAME + EMPLOYEE_ID_1)))
//        ;
//
//    }

    @Test
    public void add() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post("/employee_add")
                .content(asJsonString(createEmployeeForTest(EMPLOYEE_ID_1)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
    //            .andExpect(status().isCreated())
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

        Employee employee = createEmployeeForTest(EMPLOYEE_ID_1);
        String json = new ObjectMapper().writeValueAsString(employee);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        )
                    .andExpect(status().isOk())
        ;
    }

    @Test
    public void delete() throws Exception {

        Mockito.when(employeeService.findById(createEmployeeForTest(EMPLOYEE_ID_1).getDepartmentId())).thenReturn(createEmployeeForTest(EMPLOYEE_ID_1));
        doNothing().when(employeeService).delete(createEmployeeForTest(EMPLOYEE_ID_1).getDepartmentId());

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/employees/{employeeId}", EMPLOYEE_ID_1))
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;

        Mockito.verify(employeeService).delete(EMPLOYEE_ID_1);
        Mockito.verify(employeeService, times(1)).delete(EMPLOYEE_ID_1);
        Mockito.verifyNoMoreInteractions(employeeService);
    }
//
//    @Test
//    public void totalCountOfEmployees() throws Exception {
//        int totalCountOfEmployees = 2;
//
//        Mockito.when(employeeService.totalCountOfEmployees()).thenReturn(totalCountOfEmployees);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/employees/{totalCountOfEmployees}", totalCountOfEmployees)
//                .accept(MediaType.APPLICATION_JSON_UTF8))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
//        ;

//    }

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
