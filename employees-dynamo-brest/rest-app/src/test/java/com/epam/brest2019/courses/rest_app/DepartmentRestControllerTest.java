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
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class DepartmentRestControllerTest {

    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String DEPARTMENT_ACCESS_RIGHTS = "departmentAccessRights";

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

        Mockito.when(departmentService.findAll()).thenReturn(Arrays.asList(createDepartmentForTest(0), createDepartmentForTest(1)));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/departments")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentName", Matchers.is(DEPARTMENT_NAME + 0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentId", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentAccessRights", Matchers.is(DEPARTMENT_ACCESS_RIGHTS)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentName", Matchers.is(DEPARTMENT_NAME + 1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentAccessRights", Matchers.is(DEPARTMENT_ACCESS_RIGHTS)))
        ;

        Mockito.verify(departmentService).findAll();
        Mockito.verifyNoMoreInteractions(departmentService);
    }

    @Test
    public void findById() throws Exception {
        int departmentId = 1;

        Mockito.when(departmentService.findById(departmentId)).thenReturn(createDepartmentForTest(departmentId));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/departments/{id}", departmentId)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentId").value(departmentId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentName").value(DEPARTMENT_NAME + departmentId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentAccessRights").value(DEPARTMENT_ACCESS_RIGHTS))
        ;

        Mockito.verify(departmentService).findById(departmentId);
        Mockito.verifyNoMoreInteractions(departmentService);
    }
/*
    @Test
    public void findByIdFail404NotFound() throws Exception {
        int departmentId = 3;

        Mockito.when(departmentService.findById(departmentId)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/departments/{id}", departmentId))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
        ;

        Mockito.verify(departmentService).findById(departmentId);
        Mockito.verifyNoMoreInteractions(departmentService);
    }
    */

    @Test
    public void add() throws Exception {
        int departmentId = 1;
        mockMvc.perform(MockMvcRequestBuilders
                .post("/department")
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
        int departmentId = 1;

        Department department = createDepartmentForTest(departmentId);

        String json = new ObjectMapper().writeValueAsString(department);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/department")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isAccepted())
        ;

    }

    @Test
    public void delete() throws Exception {
    int departmentId = 1;

        Mockito.when(departmentService.findById(createDepartmentForTest(departmentId).getDepartmentId())).thenReturn(createDepartmentForTest(departmentId));
        doNothing().when(departmentService).delete(createDepartmentForTest(departmentId).getDepartmentId());

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/departments/{id}", departmentId))
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;

        Mockito.verify(departmentService).delete(departmentId);
        Mockito.verify(departmentService, times(1)).delete(departmentId);
        Mockito.verifyNoMoreInteractions(departmentService);
    }

    @Test
    public void findAllCountEmployeesInDepartment() throws Exception {
        int departmentId = 1;

        Mockito.when(departmentService.findAllCountEmployeesInDepartment()).thenReturn(Arrays.asList(createDepartmentForTest(0), createDepartmentForTest(1)));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/departments/with_total_count_employees")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentName", Matchers.is(DEPARTMENT_NAME + 0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentId", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentAccessRights", Matchers.is(DEPARTMENT_ACCESS_RIGHTS)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentName", Matchers.is(DEPARTMENT_NAME + 1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].departmentAccessRights", Matchers.is(DEPARTMENT_ACCESS_RIGHTS)))
        ;

        Mockito.verify(departmentService).findAllCountEmployeesInDepartment();
        Mockito.verifyNoMoreInteractions(departmentService);
    }

    private Department createDepartmentForTest(int departmentId) {
        Department department = new Department();
        department.setDepartmentId(departmentId);
        department.setDepartmentName(DEPARTMENT_NAME + departmentId);
        department.setDepartmentAccessRights(DEPARTMENT_ACCESS_RIGHTS);
        return department;
    }
}
