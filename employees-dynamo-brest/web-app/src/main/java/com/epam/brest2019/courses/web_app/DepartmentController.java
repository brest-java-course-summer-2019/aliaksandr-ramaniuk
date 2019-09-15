package com.epam.brest2019.courses.web_app;

import com.epam.brest2019.courses.model.Department;
import com.epam.brest2019.courses.service.DepartmentService;

import com.epam.brest2019.courses.web_app.validators.DepartmentValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.validation.ObjectError;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


/**
 * Department controller.
 */
@Controller
public class DepartmentController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    /**
     * Department Service.
     */
    @Autowired
    private DepartmentService departmentService;

    /**
     * Department Validator.
     */
    @Autowired
    DepartmentValidator departmentValidator;


    /**
     * Go to departments list page.
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
     * Go to edit department page.
     *
     * @return view name
     */
    @GetMapping(value = "/department/{departmentId}")
    public final String findById(@PathVariable Integer departmentId, Model model) {
        LOGGER.debug("Go to edit department page({},{})", departmentId, model);
        Department department = departmentService.findById(departmentId);
        model.addAttribute("department", department);
        return "department";
    }

    /**
     * Go to add department page.
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
     * Persist new department into persistence storage.
     *
     * @param department new department with filled data.
     * @param result     binding result.
     * @return view name
     */
    @PostMapping(value = "/department")
    public String addDepartment(@Valid Department department,
                                BindingResult result) {

        LOGGER.debug("Add department({}, {})", department, result);
        departmentValidator.validate(department, result);

        if (result.hasErrors()) {
            return "department";
        } else {
            try{
                this.departmentService.add(department);
                return "redirect:/departments";
            }
            catch (Exception error){
                FieldError ssoError = new FieldError("department", "departmentName","This name is used");
                result.addError(ssoError);
                return "department";
            }
        }
    }

    /**
     * Update department into persistence storage.
     *
     * @return view name
     */
    @PostMapping(value = "/department/{departmentId}")
    public String updateDepartment(@Valid Department department,
                                   BindingResult result) {
        LOGGER.debug("Update department({}, {})", department, result);
        departmentValidator.validate(department, result);
        if (result.hasErrors()) {
            return "department";
        } else {
            try{
                this.departmentService.update(department);
                return "redirect:/departments";
            }
            catch (Exception error){
                FieldError ssoError = new FieldError("department", "departmentName","This name is used");
                result.addError(ssoError);
                return "department";
            }
        }
    }

    /**
     * Delete department.
     *
     * Redirect to main page: departments.
     *
     * @return view name.
     */
    @GetMapping(value = "/department/{departmentId}/delete")
    public final String deleteDepartment(@PathVariable Integer departmentId, Model model) {
        LOGGER.debug("Delete department with specified id (departmentId): ({}, {})", departmentId, model);
        departmentService.delete(departmentId);
        return "redirect:/departments";
    }
}