package com.epam.brest2019.courses.rest_app;

import com.epam.brest2019.courses.model.Department;
import com.epam.brest2019.courses.service.DepartmentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


/**
 * Department Rest controller.
 */
@RestController
public class DepartmentRestController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentRestController.class);

    /**
     * Department Service.
     */
    @Autowired
    private DepartmentService departmentService;

    /**
     * Find all departments.
     *
     * @return departments list of all departments.
     */
    @GetMapping(value = "/departments")
    public List<Department> findAll() {
        LOGGER.debug("Find all departments");
        return departmentService.findAll();
    }

    /**
     * Find Department by departmentId.
     *
     * @param departmentId department id.
     * @return department list with departmentId.
     */
    @GetMapping(value = "/departments/{departmentId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Department findById(@PathVariable("departmentId") Integer departmentId) {
        LOGGER.debug("Find department with specified id: ({})", departmentId);
        return departmentService.findById(departmentId);
    }

    /**
     * Add new department.
     *
     * @param department department.
     * @return department.
     */
    @PostMapping()
//    public ResponseEntity<Department> add(@Valid @RequestBody Department department)  {
    public void add(@Valid @RequestBody Department department)  {
        LOGGER.debug("Add new department: ({})", department);
//        Department result = departmentService.add(department);
//        return new ResponseEntity<Department>(result, HttpStatus.CREATED);

    }

    /**
     * Update department.
     *
     * @param department department.
     * @return department.
     */
    @PutMapping()
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void update(@RequestBody Department department) {
        LOGGER.debug("Update department: ({})", department);
        departmentService.update(department);
    }

    /**
     * Delete department with specified id departmentId.
     *
     * @param departmentId department id.
     */
    @DeleteMapping(value = "/departments/{departmentId}")
    public void delete(@PathVariable("departmentId") Integer departmentId) {
        LOGGER.debug("Delete department with specified id (departmentId): ({})", departmentId);
        departmentService.delete(departmentId);
    }

    /**
     * Find all departments with the count of employees in each department.
     *
     * @return list of all departments with the count of employees in each department.
     */
    @GetMapping(value = "/departments/with_total_count_employees")
    public List<Department> findAllCountEmployeesInDepartment() {
        LOGGER.debug("Find all departments with the count of employees in each department");
        return departmentService.findAllCountEmployeesInDepartment();
    }
}