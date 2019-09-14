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

//    /**
//     * Get all employees with specified department id.
//     *
//     * @param departmentId department id.
//     * @return employees list with specified department id.
//     */
//    @GetMapping(value = "/employees/{departmentId}")
//    public List<Employee> findByDepartmentId(@PathVariable Integer departmentId) {
//        LOGGER.debug("Find all employees with specified department id (departmentId): ({})", departmentId);
//        return employeeService.findByDepartmentId(departmentId);
//    }

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

//    /**
//     * Get the number of employees in all departments.
//     *
//     * @return total count employees in all departments.
//     */
//    @GetMapping(value = "/employees/{totalCountOfEmployees}")
//    @ResponseStatus(value = HttpStatus.OK)
//    public Integer totalCountOfEmployees() {
//        LOGGER.debug("Get the number of employees in all departments");
//        return employeeService.totalCountOfEmployees();
//    }
//
//    /**
//     * Get filter employees by last name.
//     * @param lastName last name.
//     * @return employees list with filter by last name.
//     */
//
//    @GetMapping(value = "/employees/{lastName}")
//    @ResponseStatus(value = HttpStatus.OK)
//    public List<Employee> filterEmployee(@PathVariable ("lastName") String lastName) {
//        LOGGER.debug("Get filter employees by last name: ({})", lastName);
//        return employeeService.filterEmployee(lastName);
//    }

//    /**
//     * Get filter employees by date.
//     * @param localDates1 local date first value.
//     * @param localDates2 local date second value.
//     * @return employees list with filter by date.
//     */
//   @GetMapping(value = "/employees/{localDates1}/{localDates2}")
//    @ResponseStatus(value = HttpStatus.OK)
//    public List<Employee> filterEmployeeByDate(@PathVariable ("localDates1") LocalDate localDates1,
//                                               @PathVariable ("localDates2") LocalDate localDates2) {
//        LOGGER.debug("Get filter employees by date: ({}, {})", localDates1, localDates2);
//        return employeeService.filterEmployeeByDate(localDates1, localDates1);
//    }

}