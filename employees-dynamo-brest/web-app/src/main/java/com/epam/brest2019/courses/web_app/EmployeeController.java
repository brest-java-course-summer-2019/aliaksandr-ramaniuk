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

import javax.validation.Valid;
import java.time.LocalDate;

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

   //     employee.setLocalDate(LocalDate.parse(employee.getLocalDate1()));

        if (result.hasErrors()) {
            model.addAttribute("employee", employee);
            model.addAttribute("departments", departmentService.findAll());
            return "employee-add";
        } else {
            try {
                this.employeeService.add(employee);
                return "redirect:/employees";
            } catch (Exception error) {
                FieldError ssoError = new FieldError("employee", "login", "This login is used");
                result.addError(ssoError);
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


    /**
     * Get the number of employees in all departments.
     *
     * @return total count employees in all departments.
     */
    @GetMapping(value = "/{totalCountOfEmployees}")
    public final String totalCountOfEmployees(@PathVariable Integer totalCountOfEmployees) {
        LOGGER.debug("Get the number of employees in all departments:({})", totalCountOfEmployees);
        employeeService.totalCountOfEmployees();
        return "employees";
    }

//    /**
//     * Get filter employees by last name.
//     * @param lastName last name.
//     * @return employees list with filter by last name.
//     */
//****************************************************************
//    @GetMapping(value = "/filter-employee/{lastName}")
//    public String filterEmployee(@PathVariable String lastName, Model model) {
//        LOGGER.debug("Get filter employees by last name: ({})", lastName);
//        model.addAttribute("lastName", employeeService.filterEmployee(lastName));
//        return "employees";
//    }
//******************************************************************
    @PostMapping(value = "/filter")
    public String filterEmployee(@Valid @ModelAttribute("lastName") Employee employee,
                                 BindingResult result, Model model) {
          LOGGER.debug("Get filter employees by last name: ({})", result);
        employeeValidator.validate(employee, result);

        if (result.hasErrors()) {
            return "employees";
        } else {
            model.addAttribute("lastName", employeeService.filterEmployee(employee.getLastName()));
            return "employees";
        }

    }

        //filterEmployeeByDate
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    public Brand getBrand(@PathVariable Integer id) {
//        return brandService.getOne(id);
//    }
//
//    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
//    public List<Brand> getBrand(@PathVariable String name) {
//        return brandService.getSome(name);
//    }


    
}