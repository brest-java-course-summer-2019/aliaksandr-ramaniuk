package com.epam.brest2019.courses.rest_app;


import com.epam.brest2019.courses.model.Employee;
import com.epam.brest2019.courses.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

//import java.util.Collection;
//import java.time.LocalDate;
import java.util.List;

/**
 * Employee Rest controller.
 */

@RestController
public class EmployeeRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRestController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/employees")
    public List<Employee> findAll() {
        LOGGER.debug("Find all employees");
        return employeeService.findAll();
    }

    @GetMapping(value = "/employees/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Employee findById(@PathVariable("id") Integer employeeId) {
        LOGGER.debug("Find employee with specified id: ({})", employeeId);
        return employeeService.findById(employeeId);
    }

    @GetMapping(value = "/employees/departmentId")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Employee> findByDepartmentId(@PathVariable ("departmentId") Integer departmentId) {
        LOGGER.debug("Find all employees with specified department id: ({})", departmentId);
        return employeeService.findByDepartmentId(departmentId);
    }
/*
    @PostMapping()
    public ResponseEntity<Employee> add(@RequestBody Employee employee) {
        LOGGER.debug("Add new employee: ({})", employee);
        Employee result = employeeService.add(employee);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

 */

    @PutMapping()
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void update(@RequestBody Employee employee) {
        LOGGER.debug("Update employee: ({})", employee);
        employeeService.update(employee);
    }

    @DeleteMapping(value = "/employees/{id}")
    public void delete(@PathVariable("id") Integer employeeId) {
        LOGGER.debug("Delete employee with specified id: ({})", employeeId);
        employeeService.delete(employeeId);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public int totalCountOfEmployees() {
        LOGGER.debug("Get the number of employees in all departments");
        return employeeService.totalCountOfEmployees();
    }


/*
    @GetMapping(value = "/employees/lastName")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Employee> filterEmployee(@PathVariable String lastName) {
        LOGGER.debug("Get filter employees by last name: ({})", lastName);
        return employeeService.filterEmployee(lastName);
    }

    @GetMapping(value = "/employees/localDates")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Employee> filterEmployeeByDate(@PathVariable LocalDate... localDates) {
        LOGGER.debug("Get filter employees by date: ({})", localDates, localDates);
        return employeeService.filterEmployeeByDate(localDates);
    }
*/
}