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

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
public class EmployeeControllerTest {

    private static final String EMPLOYEE_EDIT = "employee_Edit";
    private static final String EMPLOYEE_ADD = "employee_Add";
    private static final String EMPLOYEES = "employees";
    private static final String EMPLOYEE_ID = "employeeId";
    private static final String EMPLOYEE_LOGIN = "login";
    private static final String EMPLOYEE_LAST_NAME = "lastName";
    private static final String EMPLOYEE_FIRST_NAME = "firstName";
    private static final String EMPLOYEE_PATRONIC_NAME = "patronicName";
    private static final Integer EMPLOYEE_ID_1 = 1;
    private static final Integer EMPLOYEE_ID_2 = 2;

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

    /**
     * Find all employees.
     */
     @Test
    public void findAll() throws Exception {
         Mockito.when(employeeService.findAll()).thenReturn(Arrays.asList(createEmployeeForTest(EMPLOYEE_ID_1), createEmployeeForTest(EMPLOYEE_ID_2)));

         mockMvc.perform(MockMvcRequestBuilders
                 .get("/employees"))
                 .andDo(MockMvcResultHandlers.print())
                 .andExpect(MockMvcResultMatchers.status().isOk())
                 .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                 .andExpect(MockMvcResultMatchers.view().name(EMPLOYEES))
                 .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("<title>Employees List</title>")))
                 .andExpect(MockMvcResultMatchers.model().attribute(EMPLOYEES, hasSize(2)))
                 .andExpect(MockMvcResultMatchers.model().attribute(EMPLOYEES, hasItem(
                         allOf(
                                 hasProperty(EMPLOYEE_ID, Matchers.is(EMPLOYEE_ID_1)),
                                 hasProperty(EMPLOYEE_LOGIN, Matchers.is(EMPLOYEE_LOGIN + EMPLOYEE_ID_1)),
                                 hasProperty(EMPLOYEE_LAST_NAME, Matchers.is(EMPLOYEE_LAST_NAME + EMPLOYEE_ID_1)),
                                 hasProperty(EMPLOYEE_FIRST_NAME, Matchers.is(EMPLOYEE_FIRST_NAME + EMPLOYEE_ID_1)),
                                 hasProperty(EMPLOYEE_PATRONIC_NAME, Matchers.is(EMPLOYEE_PATRONIC_NAME + EMPLOYEE_ID_1))
                         )
                 )))
                 .andExpect(model().attribute(EMPLOYEES, hasItem(
                         allOf(
                                 hasProperty(EMPLOYEE_ID, Matchers.is(EMPLOYEE_ID_2)),
                                 hasProperty(EMPLOYEE_LOGIN, Matchers.is(EMPLOYEE_LOGIN + EMPLOYEE_ID_2)),
                                 hasProperty(EMPLOYEE_LAST_NAME, Matchers.is(EMPLOYEE_LAST_NAME + EMPLOYEE_ID_2)),
                                 hasProperty(EMPLOYEE_FIRST_NAME, Matchers.is(EMPLOYEE_FIRST_NAME + EMPLOYEE_ID_2)),
                                 hasProperty(EMPLOYEE_PATRONIC_NAME, Matchers.is(EMPLOYEE_PATRONIC_NAME + EMPLOYEE_ID_2))
                         )
                 )))
         ;
    }

    /**
     * Find all employees with specified department id (departmentId).
     */
    @Test
    public void findById() throws Exception {

        Mockito.when(employeeService.findById(EMPLOYEE_ID_1)).thenReturn(createEmployeeForTest(EMPLOYEE_ID_1));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/employee_Edit/{employeeId}", EMPLOYEE_ID_1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(EMPLOYEES))
                .andExpect(MockMvcResultMatchers.model().attribute(EMPLOYEE_EDIT, hasProperty(EMPLOYEE_ID, Matchers.is(EMPLOYEE_ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute(EMPLOYEE_EDIT, hasProperty(EMPLOYEE_LOGIN, Matchers.is(EMPLOYEE_LOGIN + EMPLOYEE_ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute(EMPLOYEE_EDIT, hasProperty(EMPLOYEE_LAST_NAME, Matchers.is(EMPLOYEE_LAST_NAME + EMPLOYEE_ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute(EMPLOYEE_EDIT, hasProperty(EMPLOYEE_FIRST_NAME, Matchers.is(EMPLOYEE_FIRST_NAME + EMPLOYEE_ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute(EMPLOYEE_EDIT, hasProperty(EMPLOYEE_PATRONIC_NAME, Matchers.is(EMPLOYEE_PATRONIC_NAME + EMPLOYEE_ID_1))))
        ;

        Mockito.verify(employeeService, times(1)).findById(EMPLOYEE_ID_1);
    }

    /**
     * Go to employee add page.
     */
    @Test
    public void goToEmployeeAddPage() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .get("/employee_Add"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("<title>Employees Add</title>")))
        ;
    }


    /**
     * Add new employee.
     */
    @Test
    public void addDepartment() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post("/employee_Add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param(EMPLOYEE_LOGIN, EMPLOYEE_LOGIN)
                .param(DEPARTMENT_ACCESS_RIGHTS, DEPARTMENT_ACCESS_RIGHTS)
                .sessionAttr(DEPARTMENTS, new Department())
        )
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/departments"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/departments"))
                //      .andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors(DEPARTMENT, DEPARTMENT_ACCESS_RIGHTS))
                .andExpect(MockMvcResultMatchers.model().attribute(DEPARTMENT, hasProperty(DEPARTMENT_ID, nullValue())))
                .andExpect(MockMvcResultMatchers.model().attribute(DEPARTMENT, hasProperty(DEPARTMENT_NAME, Matchers.is(DEPARTMENT_NAME))))
                .andExpect(MockMvcResultMatchers.model().attribute(DEPARTMENT, hasProperty(DEPARTMENT_ACCESS_RIGHTS, Matchers.is(DEPARTMENT_ACCESS_RIGHTS))))
        ;
    }


    private Employee createEmployeeForTest(int employeeId) {
        Employee employee = new Employee();

        employee.setDepartmentId(employeeId);
        employee.setEmployeeId(employeeId);
        employee.setLogin(EMPLOYEE_LOGIN + employeeId);
        employee.setLastName(EMPLOYEE_LAST_NAME + employeeId);
        employee.setFirstName(EMPLOYEE_FIRST_NAME + employeeId);
        employee.setPatronicName(EMPLOYEE_PATRONIC_NAME + employeeId);

        return employee;
    }
}
