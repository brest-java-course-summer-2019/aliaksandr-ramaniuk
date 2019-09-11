package com.epam.brest2019.courses.web_app;

import com.epam.brest2019.courses.model.Department;
import com.epam.brest2019.courses.service.DepartmentService;
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

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
public class DepartmentControllerTest {

    private static final String DEPARTMENT = "department";
    private static final String DEPARTMENTS = "departments";
    private static final String DEPARTMENT_ID = "departmentId";
    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String DEPARTMENT_ACCESS_RIGHTS = "departmentAccessRights";
    private static final Integer DEPARTMENT_ID_1 = 1;
    private static final Integer DEPARTMENT_ID_2 = 2;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private DepartmentService departmentService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();
    }

    /**
     * Find all departments with the count of employees in each department.
     */
    @Test
    public void findAllCountEmployeesInDepartment() throws Exception {

        Mockito.when(departmentService.findAllCountEmployeesInDepartment()).thenReturn(Arrays.asList(createDepartmentForTest(DEPARTMENT_ID_1), createDepartmentForTest(DEPARTMENT_ID_2)));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/departments"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.view().name(DEPARTMENTS))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("<title>Departments List</title>")))
                .andExpect(MockMvcResultMatchers.model().attribute(DEPARTMENTS, hasSize(2)))
                .andExpect(MockMvcResultMatchers.model().attribute(DEPARTMENTS, hasItem(
                        allOf(
                                hasProperty(DEPARTMENT_ID, Matchers.is(DEPARTMENT_ID_1)),
                                hasProperty(DEPARTMENT_NAME, Matchers.is(DEPARTMENT_NAME + DEPARTMENT_ID_1)),
                                hasProperty(DEPARTMENT_ACCESS_RIGHTS, Matchers.is(DEPARTMENT_ACCESS_RIGHTS + DEPARTMENT_ID_1))
                        )
                )))
                .andExpect(model().attribute(DEPARTMENTS, hasItem(
                        allOf(
                                hasProperty(DEPARTMENT_ID, Matchers.is(DEPARTMENT_ID_2)),
                                hasProperty(DEPARTMENT_NAME, Matchers.is(DEPARTMENT_NAME + DEPARTMENT_ID_2)),
                                hasProperty(DEPARTMENT_ACCESS_RIGHTS, Matchers.is(DEPARTMENT_ACCESS_RIGHTS + DEPARTMENT_ID_2))
                        )
                )))
        ;

        Mockito.verify(departmentService, times(1)).findAllCountEmployeesInDepartment();
        //  Mockito.verifyNoMoreInteractions(departmentService);
    }

    /**
     * Find Department by id (departmentId).
     */
    @Test
    public void findById() throws Exception {

        Mockito.when(departmentService.findById(DEPARTMENT_ID_1)).thenReturn(createDepartmentForTest(DEPARTMENT_ID_1));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/department/{departmentId}", DEPARTMENT_ID_1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(DEPARTMENT))
                .andExpect(MockMvcResultMatchers.model().attribute(DEPARTMENT, hasProperty(DEPARTMENT_ID, Matchers.is(DEPARTMENT_ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute(DEPARTMENT, hasProperty(DEPARTMENT_NAME, Matchers.is(DEPARTMENT_NAME + DEPARTMENT_ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute(DEPARTMENT, hasProperty(DEPARTMENT_ACCESS_RIGHTS, Matchers.is(DEPARTMENT_ACCESS_RIGHTS + DEPARTMENT_ID_1))))
        ;

        Mockito.verify(departmentService, times(1)).findById(DEPARTMENT_ID_1);
    }

    /**
     * Go to department add page.
     */
    @Test
    public void goToDepartmentAddPage() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .get("/department"))
        .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("<title>Department</title>")))
        ;
    }

    /**
     * Add new department.
     */
    @Test
    public void addDepartment() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post("/department")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param(DEPARTMENT_NAME, DEPARTMENT_NAME)
                .param(DEPARTMENT_ACCESS_RIGHTS, DEPARTMENT_ACCESS_RIGHTS)
                .sessionAttr(DEPARTMENT, new Department())
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

    /**
     * Update department.
     */
    @Test
    public void updateEmptyDepartment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/department/{departmentId}", DEPARTMENT_ID_1)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param(DEPARTMENT_ID, DEPARTMENT_ID_1.toString())
                .sessionAttr(DEPARTMENT, new Department())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name(DEPARTMENT))
                .andExpect(MockMvcResultMatchers.model().attribute(DEPARTMENT, hasProperty(DEPARTMENT_ID, Matchers.is(DEPARTMENT_ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute(DEPARTMENT, hasProperty(DEPARTMENT_NAME, isEmptyOrNullString())))
                .andExpect(MockMvcResultMatchers.model().attribute(DEPARTMENT, hasProperty(DEPARTMENT_ACCESS_RIGHTS, isEmptyOrNullString())))
        ;

    }

    /**
     * Update department with specified id (departmentId).
     */
    @Test
    public void updateDepartment() throws Exception {
        Mockito.doNothing().doThrow(new IllegalStateException())
                .when(departmentService).update(createDepartmentForTest(DEPARTMENT_ID_1));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/department/{departmentId}", DEPARTMENT_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .param(DEPARTMENT_NAME, DEPARTMENT_NAME + DEPARTMENT_ID_2)
                .param(DEPARTMENT_ACCESS_RIGHTS, DEPARTMENT_ACCESS_RIGHTS + DEPARTMENT_ID_2)
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/departments"))
                .andExpect(MockMvcResultMatchers.model().attribute(DEPARTMENT, hasProperty(DEPARTMENT_ID, Matchers.is(DEPARTMENT_ID_1))))
                .andExpect(MockMvcResultMatchers.model().attribute(DEPARTMENT, hasProperty(DEPARTMENT_NAME, Matchers.is(DEPARTMENT_NAME + DEPARTMENT_ID_2))))
                .andExpect(MockMvcResultMatchers.model().attribute(DEPARTMENT, hasProperty(DEPARTMENT_ACCESS_RIGHTS, Matchers.is(DEPARTMENT_ACCESS_RIGHTS + DEPARTMENT_ID_2))))
                ;

        Mockito.verify(departmentService, times(1)).update(Mockito.any(Department.class));
    }

    /**
     * Delete department with specified id (departmentId).
     */
    @Test
    public void delete() throws Exception {

        Mockito.doNothing().doThrow(new IllegalStateException()).when(departmentService).delete(Mockito.anyInt());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/department/{departmentId}/delete", DEPARTMENT_ID_1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/departments"))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/departments"))
        ;

        Mockito.verify(departmentService, Mockito.times(1)).delete(Mockito.anyInt());

    }

    private Department createDepartmentForTest(int departmentId) {
        Department department = new Department();
        department.setDepartmentId(departmentId);
        department.setDepartmentName(DEPARTMENT_NAME + departmentId);
        department.setDepartmentAccessRights(DEPARTMENT_ACCESS_RIGHTS + departmentId);
        return department;
    }

}
