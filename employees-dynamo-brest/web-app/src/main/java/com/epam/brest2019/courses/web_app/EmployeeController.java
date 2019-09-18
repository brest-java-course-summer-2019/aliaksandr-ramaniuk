package com.epam.brest2019.courses.web_app;

import com.epam.brest2019.courses.model.Employee;
import com.epam.brest2019.courses.service.DepartmentService;
import com.epam.brest2019.courses.service.EmployeeService;
import com.epam.brest2019.courses.web_app.validators.EmployeeValidator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
     * Department Service.
     */
    @Autowired
    private DepartmentService departmentService;

    /**
     * Employee Validator.
     */
    @Autowired
    EmployeeValidator employeeValidator;

    @Bean(name = "OBJECT_MAPPER_BEAN")
    public ObjectMapper jsonObjectMapper() {
        return Jackson2ObjectMapperBuilder.json()
                .serializationInclusion(JsonInclude.Include.NON_NULL) // Don’t include null values
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) //ISODate
                .modules(new JSR310Module())
                .build();
    }

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
    @GetMapping(value = "/employee-edit/{employeeId}")
    public final String findById(@PathVariable Integer employeeId, Model model) {
        LOGGER.debug("Go to edit employee page({},{})", employeeId, model);
        //  Employee employee = employeeService.findById(employeeId);
        model.addAttribute("employee", employeeService.findById(employeeId));
        model.addAttribute("departments", departmentService.findAll());
        return "employee-edit";
    }

    /**
     * Update employee into persistence storage.
     *
     * @return view name
     */
    @PostMapping(value = "/employee-edit/{employeeId}")
    public String updateEmployee(@Valid Employee employee,
                                 BindingResult result, Model model) {
        LOGGER.debug("Update employee ({}, {})", employee, result);
        employeeValidator.validate(employee, result);
        if (result.hasErrors()) {
            model.addAttribute("employee", employee);
            model.addAttribute("departments", departmentService.findAll());
            return "employee-edit";
        } else {
            this.employeeService.update(employee);
            return "redirect:/employees";
        }
    }

    /**
     * Go to add employee-add page.
     *
     * @return view name
     */
    @GetMapping(value = "/employee-add")
    public final String goToAddEmployeePage(Model model) {
        LOGGER.debug("Go to employee add page ({})", model);
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentService.findAll());
        return "employee-add";
    }

    /**
     * Persist new employee into persistence storage.
     *
     * @param employee new employee with filled data.
     * @param result   binding result.
     * @return view name
     */
    @PostMapping(value = "/employee-add")
    public String addEmployee(@Valid @ModelAttribute("employee") Employee employee,
                              BindingResult result, Model model) {
        LOGGER.debug("Add employee({}, {})", employee, result);
        employeeValidator.validate(employee, result);
        try {
            employee.setLocalDate(LocalDate.parse(employee.getLocalDate3()));
        } catch (DateTimeParseException e) {
            employee.setLocalDate(LocalDate.now());
        }

        if (result.hasErrors()) {
            model.addAttribute("employee", employee);
            model.addAttribute("departments", departmentService.findAll());
            return "employee-add";
        } else {
            try {
                this.employeeService.add(employee);
                return "redirect:/employees";
            } catch (HttpServerErrorException e) {
                FieldError ssoError = new FieldError("employee", "login", "This login is used");
                result.addError(ssoError);
                model.addAttribute("departments", departmentService.findAll());
                return "employee-add";
            }
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
        this.employeeService.delete(employeeId);
        return "redirect:/employees";
    }

    @PostMapping(value = "/filter")
    public String filterEmployee(@ModelAttribute("lastName") String lastName,
                                 BindingResult result, Model model) {
        LOGGER.debug("Get filter employees by last name: ({})", result);

        if (result.hasErrors() || lastName == null || lastName == "") {
            return "employees";
        } else {
            model.addAttribute("employees", employeeService.filterEmployee(lastName));
            return "employees";
        }
    }

    /**
     * Get filter employees by date.
     *
     * @param localDate1 local date first value.
     * @param localDate2 local date second value.
     * @return employees list with filter by date.
     */
    @PostMapping(value = "/filter-date")
    public String filterEmployeeByDate (@PathVariable (value = "localDate1") String localDate1,
                                       @PathVariable (value = "localDate2") String localDate2,
                                       Model model) {

        LOGGER.debug("Get filter employees by date: ({} : {})", localDate1, localDate2);

          LocalDate localDate11 = LocalDate.parse(localDate1);
          LocalDate localDate22 = LocalDate.parse(localDate2);

          model.addAttribute("employees", employeeService.filterEmployeeByDate(localDate11, localDate22));
        return "employees";
    }
}
