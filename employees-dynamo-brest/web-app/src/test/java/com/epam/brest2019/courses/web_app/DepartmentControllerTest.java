package com.epam.brest2019.courses.web_app;

import com.epam.brest2019.courses.model.Department;
import com.epam.brest2019.courses.service.DepartmentService;
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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.awt.dnd.DragGestureEvent;
import java.util.Arrays;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:app-context-test.xml"})
public class DepartmentControllerTest {

    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String DEPARTMENT_ACCESS_RIGHTS = "departmentAccessRights";

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

   // @Test
    public void findAll() throws Exception {

        Mockito.when(departmentService.findAll()).thenReturn(Arrays.asList(createDepartmentForTest(0), createDepartmentForTest(1)));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/departments"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.view().name("departments"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/templates/departments.html"))
                .andExpect(MockMvcResultMatchers.model().attribute("departments", hasSize(2)))
                .andExpect(MockMvcResultMatchers.model().attribute("departments", hasItem(
                        allOf(
                                hasProperty("departmentId", Matchers.is(1)),
                                hasProperty("departmentId", Matchers.is("departmentName1")),
                                hasProperty("departmentAccessRights", Matchers.is("departmentAccessRights1"))
                        )
                )))
                .andExpect(MockMvcResultMatchers.model().attribute("departments", hasItem(
                        allOf(
                                hasProperty("departmentId", Matchers.is(2)),
                                hasProperty("departmentId", Matchers.is("departmentName2")),
                                hasProperty("departmentAccessRights", Matchers.is("departmentAccessRights2"))
                        )
                )))
        ;

        Mockito.verify(departmentService, times(1)).findAll();
        Mockito.verifyNoMoreInteractions(departmentService);
    }

   // @Test
    public void findById() throws Exception {
        int departmentId = 1;

        Mockito.when(departmentService.findById(departmentId)).thenReturn(createDepartmentForTest(departmentId));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/department/{id}", departmentId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("department"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/templates/department.html"))
                .andExpect(MockMvcResultMatchers.model().attribute("department", hasProperty("departmentId", Matchers.is(1))))
                .andExpect(MockMvcResultMatchers.model().attribute("department", hasProperty("departmentName", Matchers.is(DEPARTMENT_NAME + 1))))
                .andExpect(MockMvcResultMatchers.model().attribute("department", hasProperty("departmentAccessRights", Matchers.is(DEPARTMENT_ACCESS_RIGHTS + 1))))
        ;
    }


    private Department createDepartmentForTest(int departmentId) {
        Department department = new Department();
        department.setDepartmentId(departmentId);
        department.setDepartmentName(DEPARTMENT_NAME + departmentId);
        department.setDepartmentAccessRights(DEPARTMENT_ACCESS_RIGHTS);
        return department;
    }

}
