package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.dao.EmployeeDao;
import com.epam.brest2019.courses.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * Employee Service Interface implementation.
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> findAll() {
        LOGGER.debug("Find all employees");
        return employeeDao.findAll();
    }

    @Override
    public List<Employee> findByDepartmentId(Integer departmentId) {
        LOGGER.debug("Find all employees with specified department id: {}, departmentId", departmentId);
        return employeeDao.findByDepartmentId(departmentId);
    }

    @Override
    public Optional<Employee> findById(Integer employeeId) {
        LOGGER.debug("Find employee with specified id: {}", employeeId);
        return employeeDao.findById(employeeId);

    }

    @Override
    public Employee add(Employee employee) {
        LOGGER.debug("Add new employee: {}", employee);
        return employeeDao.add(employee);
    }

    @Override
    public void update(Employee employee) {
        LOGGER.debug("Update employee: {}", employee);
        employeeDao.update(employee);
    }

    @Override
    public void delete(Integer employeeId) {
        LOGGER.debug("Delete employee with specified id: {}", employeeId);
        employeeDao.delete(employeeId);
    }
}