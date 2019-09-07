package com.epam.brest2019.courses.web_app;

import com.epam.brest2019.courses.model.Employee;
import com.epam.brest2019.courses.service.EmployeeService;
import com.epam.brest2019.courses.web_app.validators.EmployeeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Employee controller.
 */
@Controller
public class EmployeeController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    /**
     * Department Service.
     */
    @Autowired
    private EmployeeService employeeService;

    /**
     * Validator.
     */
    @Autowired
    EmployeeValidator employeeValidator;

    /**
     * Goto employees list page.
     *
     * @param model model
     * @return view name
     */
    @GetMapping(value = "/employees")
    public final String employees(Model model) {
        LOGGER.debug("Find all employees: ({})", model);
        model.addAttribute("employees", employeeService.findAll());
        return "employees";
    }

    /**
     * Goto employee edit page.
     *
     * @return view name
     */
    @GetMapping(value = "/employee_Edit/{employeeId}")
    public final String findById(@PathVariable Integer employeeId, Model model) {
        LOGGER.debug("Go to edit employee page({},{})", employeeId, model);
        Employee employee = employeeService.findById(employeeId);
        model.addAttribute("employee_Edit", employee);
        return "employees";
    }

    /**
     * Goto add employee_Add page.
     *
     * @return view name
     */
    @GetMapping(value = "employee_Add")
    public final String goToAddEmployeePage(Model model) {
        LOGGER.debug("Go to employee add page ({})", model);
        Employee employee = new Employee();
        model.addAttribute("isNew", true);
        model.addAttribute("employee_Add", employee);
        return "employee_Add";
    }

    /**
     * Persist new employee into persistence storage.
     *
     * @param employee new employee with filled data.
     * @param result binding result.
     * @return view name
     */
    @PostMapping(value = "/employee_Add")
    public String addEmployee(@Valid Employee employee,
                                BindingResult result) {

        LOGGER.debug("Add employee({}, {})", employee, result);
        employeeValidator.validate(employee, result);
        if (result.hasErrors()) {
            return "employee_Add";
        } else {
            this.employeeService.add(employee);
            return "redirect:/employees";
        }
    }

    /**
     * Update employee into persistence storage.
     *
     * @return view name
     */
    @PostMapping(value = "/employee_Edit/{employeeId}")
    public String updateEmployee(@Valid Employee employee,
                                   BindingResult result) {
        LOGGER.debug("Update employee ({}, {})", employee, result);
        employeeValidator.validate(employee, result);
        if (result.hasErrors()) {
            return "employee_Edit";
        } else {
            this.employeeService.update(employee);
            return "redirect:/employees";
        }
    }

    /**
     * Delete employee.
     *
     * @return view name
     */
    @GetMapping(value = "/employees/{employeeId}/delete")
    public final String delete(@PathVariable Integer employeeId, Model model) {
        LOGGER.debug("Delete employee with specified id:({},{})", employeeId, model);
        employeeService.delete(employeeId);
        return "redirect:/employees";
    }
}