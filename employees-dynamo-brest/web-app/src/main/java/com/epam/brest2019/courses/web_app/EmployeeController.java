package com.epam.brest2019.courses.web_app;

import com.epam.brest2019.courses.model.Employee;
import com.epam.brest2019.courses.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Employee controller.
 */
@Controller
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private EmployeeService employeeService;

    Employee employee;

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
     * Goto add employee_Add page.
     *
     * @return view name
     */
    @GetMapping(value = "/employee_Add")
    public final String goToAddEmployeePage(Model model) {

        LOGGER.debug("Go to employee add page ({})", model);
        model.addAttribute("isNew", true);
        model.addAttribute("employee", employee);
        return "employee";
    }

    /**
     * Goto employee edit page.
     *
     * @return view name
     */
    @GetMapping(value = "/employee_Edit/{id}")
    public final String gotoEmployeeAddPage(Model model) {
        LOGGER.debug("Go to employee edit page ({})", model);
        Employee employee = new Employee();
        model.addAttribute("isEdit", true);
        model.addAttribute("employee", employee);
        return "employee_Edit";
    }

    /**
     * Delete employee.
     *
     * @return view name
     */
    @GetMapping(value = "/employees/{id}/delete")
    public final String delete(@PathVariable Integer employeeId, Model model) {
        LOGGER.debug("Delete employee with specified id:({},{})", employeeId, model);
        employeeService.delete(employeeId);
        return "redirect:/employees";
    }
}