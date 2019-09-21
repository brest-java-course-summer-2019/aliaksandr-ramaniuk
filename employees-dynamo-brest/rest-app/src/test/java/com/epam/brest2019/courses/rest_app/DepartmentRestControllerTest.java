package com.epam.brest2019.courses.rest_app;

import com.epam.brest2019.courses.model.Department;
import com.epam.brest2019.courses.service.DepartmentService;
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
//import org.springframework.mock.web.MockHttpServletResponse;
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
public class DepartmentRestControllerTest {

    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String DEPARTMENT_ACCESS_RIGHTS = "departmentAccessRights";
    private static final Integer DEPARTMENT_ID_1 = 1;
    private static final Integer DEPARTMENT_ID_2 = 2;

    @Autowired
    private DepartmentRestController departmentRestController;

    @Autowired
    private DepartmentService departmentService;

    ObjectMapper objectMapper = new ObjectMapper();

    private MockMvc mockMvc;

    @BeforeEach
    void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(departmentRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }

    @AfterEach
    void after() {
        Mockito.reset(departmentService);
    }


    @Test
    public void findAll() throws Exception {

        Mockito.when(departmentService.findAll()).thenReturn(Arrays.asList(createDepartmentForTest(DEPARTMENT_ID_1), createDepartmentForTest(DEPARTMENT_ID_2)));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/departments")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentId", Matchers.is(DEPARTMENT_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentName", Matchers.is(DEPARTMENT_NAME + DEPARTMENT_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentAccessRights", Matchers.is(DEPARTMENT_ACCESS_RIGHTS + DEPARTMENT_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentId", Matchers.is(DEPARTMENT_ID_2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentName", Matchers.is(DEPARTMENT_NAME + DEPARTMENT_ID_2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentAccessRights", Matchers.is(DEPARTMENT_ACCESS_RIGHTS + DEPARTMENT_ID_2)))
        ;

        Mockito.verify(departmentService).findAll();
        Mockito.verifyNoMoreInteractions(departmentService);
    }

    @Test
    public void findById() throws Exception {
        Mockito.when(departmentService.findById(DEPARTMENT_ID_1)).thenReturn(createDepartmentForTest(DEPARTMENT_ID_1));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/departments/{departmentId}", DEPARTMENT_ID_1)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentId").value(DEPARTMENT_ID_1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentName").value(DEPARTMENT_NAME + DEPARTMENT_ID_1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentAccessRights").value(DEPARTMENT_ACCESS_RIGHTS + DEPARTMENT_ID_1))
        ;

        Mockito.verify(departmentService).findById(DEPARTMENT_ID_1);
        Mockito.verifyNoMoreInteractions(departmentService);
    }

    @Test
    public void add() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/departments")
                .content(asJsonString(new Department(DEPARTMENT_NAME, DEPARTMENT_ACCESS_RIGHTS)))
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

        Department department = createDepartmentForTest(DEPARTMENT_ID_1);

        String json = new ObjectMapper().writeValueAsString(department);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        )
                .andExpect(status().isAccepted())
        ;
    }

    @Test
    public void delete() throws Exception {
        Mockito.when(departmentService.findById(createDepartmentForTest(DEPARTMENT_ID_1).getDepartmentId())).thenReturn(createDepartmentForTest(DEPARTMENT_ID_1));
        doNothing().when(departmentService).delete(createDepartmentForTest(DEPARTMENT_ID_1).getDepartmentId());

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/departments/{departmentId}", DEPARTMENT_ID_1))
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;

        Mockito.verify(departmentService).delete(DEPARTMENT_ID_1);
        Mockito.verify(departmentService, times(1)).delete(DEPARTMENT_ID_1);
        Mockito.verifyNoMoreInteractions(departmentService);
    }

    @Test
    public void findAllCountEmployeesInDepartment() throws Exception {

        Mockito.when(departmentService.findAllCountEmployeesInDepartment()).thenReturn(Arrays.asList(createDepartmentForTest(DEPARTMENT_ID_1), createDepartmentForTest(DEPARTMENT_ID_2)));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/departments/with-total-count-employees")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentId", Matchers.is(DEPARTMENT_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentName", Matchers.is(DEPARTMENT_NAME + DEPARTMENT_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentAccessRights", Matchers.is(DEPARTMENT_ACCESS_RIGHTS + DEPARTMENT_ID_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentId", Matchers.is(DEPARTMENT_ID_2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentName", Matchers.is(DEPARTMENT_NAME + DEPARTMENT_ID_2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentAccessRights", Matchers.is(DEPARTMENT_ACCESS_RIGHTS + DEPARTMENT_ID_2)))
        ;

        Mockito.verify(departmentService).findAllCountEmployeesInDepartment();
        Mockito.verifyNoMoreInteractions(departmentService);
    }


    private Department createDepartmentForTest(int departmentId) {
        Department department = new Department();
        department.setDepartmentId(departmentId);
        department.setDepartmentName(DEPARTMENT_NAME + departmentId);
        department.setDepartmentAccessRights(DEPARTMENT_ACCESS_RIGHTS + departmentId);
        return department;
    }
}
