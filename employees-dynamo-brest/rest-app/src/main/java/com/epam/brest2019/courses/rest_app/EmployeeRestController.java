package com.epam.brest2019.courses.rest_app;


import com.epam.brest2019.courses.model.Employee;
import com.epam.brest2019.courses.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * Employee Rest controller.
 */

@RestController
public class EmployeeRestController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRestController.class);

    /**
     * Employee Service.
     */
    @Autowired
    private EmployeeService employeeService;

    /**
     * Get all employees.
     *
     * @return employees list of all departments.
     */
    @GetMapping(value = "/employees")
    public List<Employee> findAll() {
        LOGGER.debug("Find all employees");
        return employeeService.findAll();
    }

    /**
     * Get employee with specified id.
     *
     * @param employeeId employee id.
     * @return employees list with employeeId.
     */
    @GetMapping(value = "/employees/{employeeId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Employee findById(@PathVariable Integer employeeId) {
        LOGGER.debug("Find employee with specified id (employeeId): ({})", employeeId);
        return employeeService.findById(employeeId);
    }

    /**
     * Add new employee.
     *
     * @param employee employee.
     * @return employee.
     */
    @PostMapping(value = "/employees")
    public ResponseEntity<Employee> add(@RequestBody Employee employee) {
        LOGGER.debug("Add new employee: ({})", employee);
        Employee result = employeeService.add(employee);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * Update employee.
     *
     * @param employee employee.
     */

    @PutMapping(value = "/employees")
    public void update(@RequestBody Employee employee) {
        LOGGER.debug("Update employee: ({})", employee);
        employeeService.update(employee);
    }

    /**
     * Delete employee with specified id.
     *
     * @param employeeId employee id.
     */
    @DeleteMapping(value = "/employees/{employeeId}")
    public void delete(@PathVariable("employeeId") Integer employeeId) {
        LOGGER.debug("Delete employee with specified id (employeeId): ({})", employeeId);
        employeeService.delete(employeeId);
    }

    /**
     * Get filter employees by last name.
     *
     * @param lastName last name.
     * @return employees list with filter by last name.
     */

    @GetMapping(value = "/employees2/{lastName}")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Employee> filterEmployee(@PathVariable(value = "lastName") String lastName) {
        LOGGER.debug("Get filter employees by last name: ({})", lastName);
        return employeeService.filterEmployee(lastName);
    }

    /**
     * Get filter employees by date.
     *
     * @param localDateStart local date first value.
     * @param localDateEnd local date second value.
     * @return employees list with filter by date.
     */
    @GetMapping(value = "/employees/{localDateStart}/{localDateEnd}")
    public List<Employee> filterEmployeeByDate(@PathVariable(value = "localDateStart") String localDateStart,
                                               @PathVariable(value = "localDateEnd") String localDateEnd) {
        LOGGER.debug("Get filter employees by date: ({} : {})", localDateStart, localDateEnd);

        LocalDate localDateStartView = LocalDate.parse(localDateStart);
        LocalDate localDateEndView = LocalDate.parse(localDateEnd);

        return employeeService.filterEmployeeByDate(localDateStartView, localDateEndView);
    }

}