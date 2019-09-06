package com.epam.brest2019.courses.web_app;

import com.epam.brest2019.courses.model.Department;
import com.epam.brest2019.courses.service.DepartmentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * Department controller.
 */
@Controller
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;


    /**
     * Goto departments list page.
     *
     * @param model model
     * @return view name
     */
    @GetMapping(value = "/departments")
    public final String departments(Model model) {
        LOGGER.debug("Find all departments: ({})", model);
        model.addAttribute("departments", departmentService.findAllCountEmployeesInDepartment());
        return "departments";
    }

    /**
     * Goto edit department page.
     *
     * @return view name
     */
    @GetMapping(value = "/department/{id}")
    public final String gotoEditDepartmentPage(@PathVariable Integer departmentId, Model model) {

        LOGGER.debug("Go to edit department page({},{})", departmentId, model);
        Department department = departmentService.findById(departmentId);
        model.addAttribute("department", department);
        return "department";
    }


    /**
     * Goto add department page.
     *
     * @return view name
     */
    @GetMapping(value = "/department")
    public final String goToDepartmentAddPage(Model model) {
        LOGGER.debug("Go to add department page ({})", model);
        Department department = new Department();
        model.addAttribute("isNew", true);
        model.addAttribute("department", department);
        return "department";
    }


    /**
     * Delete department.
     *
     * Redirect to main page: departments.
     * @return view name.
     */
    @GetMapping(value = "/department/{id}/delete")
    public final String delete(@PathVariable Integer departmentId, Model model) {
        LOGGER.debug("Delete department with specified id: ({},{})", departmentId, model);
        departmentService.delete(departmentId);
        return "redirect:/departments";
    }
}