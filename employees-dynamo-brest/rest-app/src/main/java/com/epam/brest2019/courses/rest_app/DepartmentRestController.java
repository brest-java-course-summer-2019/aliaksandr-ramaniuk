package com.epam.brest2019.courses.rest_app;

import com.epam.brest2019.courses.model.Department;
import com.epam.brest2019.courses.service.DepartmentService;

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

import java.util.Collection;
import java.util.List;


/**
 * Department Rest controller.
 */
@RestController
public class DepartmentRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentRestController.class);

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = "/departments")
    public Collection<Department> findAll() {
        LOGGER.debug("Find all departments");
        return departmentService.findAll();
    }

    @GetMapping(value = "/departments/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Department findById(@PathVariable Integer departmentId) {
        LOGGER.debug("Find department with specified id: ({})", departmentId);
        return departmentService.findById(departmentId);
    }

    @PostMapping()
    public ResponseEntity<Department> add(@RequestBody Department department) {
        LOGGER.debug("Add new department: ({})", department);
        Department result = departmentService.add(department);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping()
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void update(@RequestBody Department department) {
        LOGGER.debug("Update department: ({})", department);
        departmentService.update(department);
    }

    @DeleteMapping(value = "/departments/{id}")
    public void delete(@PathVariable("id") Integer departmentId) {
        LOGGER.debug("Delete department with specified id: ({})", departmentId);
        departmentService.delete(departmentId);
    }

    @GetMapping(value = "/departments/with_total_count_employees")
    public List<Department> findAllCountEmployeesInDepartment() {
        LOGGER.debug("Find all departments with the count of employees in each department");
        return departmentService.findAllCountEmployeesInDepartment();
    }
}