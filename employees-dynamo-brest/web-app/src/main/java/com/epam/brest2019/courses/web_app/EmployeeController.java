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
import org.springframework.web.bind.annotation.ModelAttribute;
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
     * Employee Service.
     */
    @Autowired
    private EmployeeService employeeService;

    /**
     * Employee Validator.
     */
    @Autowired
    EmployeeValidator employeeValidator;

    /**
     * Go to employees list page.
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
     * Go to employee edit page.
     *
     * @return view name
     */
    @GetMapping(value = "/employee-Edit/{employeeId}")
    public final String findById(@PathVariable Integer employeeId, Model model) {
        LOGGER.debug("Go to edit employee page({},{})", employeeId, model);
        Employee employee = employeeService.findById(employeeId);
        model.addAttribute("employee-Edit", employee);
        return "employee-Edit";
    }

    /**
     * Goto add employee_Add page.
     *
     * @return view name
     */
    @GetMapping(value = "/employee-Add")
    public final String goToAddEmployeePage(Model model) {
        LOGGER.debug("Go to employee add page ({})", model);
        Employee employee = new Employee();
        model.addAttribute("isNew", true);
        model.addAttribute("employee-Add", employee);
        return "employee-Add";
    }

    /**
     * Persist new employee into persistence storage.
     *
     * @param employee new employee with filled data.
     * @param result binding result.
     * @return view name
     */
    @PostMapping(value = "/employee-Add")
    public String addEmployee(@Valid @ModelAttribute("employee-Add") Employee employee,
                                BindingResult result) {
        LOGGER.debug("Add employee({}, {})", employee, result);
        employeeValidator.validate(employee, result);
        if (result.hasErrors()) {
            return "employee-Add";
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
    @PostMapping(value = "/employee-Edit/{employeeId}")
    public String updateEmployee(@Valid @ModelAttribute("employee-Edit") Employee employee,
                                   BindingResult result) {
        LOGGER.debug("Update employee ({}, {})", employee, result);
        employeeValidator.validate(employee, result);
        if (result.hasErrors()) {
            return "employee-Edit";
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
    @GetMapping(value = "/employee/{employeeId}/delete")
    public final String delete(@PathVariable Integer employeeId, Model model) {
        LOGGER.debug("Delete employee with specified id:({},{})", employeeId, model);
        employeeService.delete(employeeId);
        return "redirect:/employees";
    }


    /**
     * Get the number of employees in all departments.
     *
     * @return total count employees in all departments.
     */
    @GetMapping(value = "/totalCountOfEmployees}")
    public final String totalCountOfEmployees(@PathVariable Integer totalCountOfEmployees) {
        LOGGER.debug("Get the number of employees in all departments:({})", totalCountOfEmployees);
        employeeService.totalCountOfEmployees();
        return "employees";
    }


    //totalCountOfEmployees

    //filterEmployee

    //filterEmployeeByDate
}