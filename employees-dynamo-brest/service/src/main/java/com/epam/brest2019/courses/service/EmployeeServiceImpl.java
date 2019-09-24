package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.dao.EmployeeDao;
import com.epam.brest2019.courses.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Employee Service Interface implementation.z
 */

@Component
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private static final String FAILED_TO_GET_EMPLOYEE = "Failed to get employee from Database by id";

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
    public Employee findById(Integer employeeId) {
        LOGGER.debug("Find employee with specified id: ({})", employeeId);
        return employeeDao.findById(employeeId)
                .orElseThrow(() -> new RuntimeException(FAILED_TO_GET_EMPLOYEE));
    }

    @Override
    public Employee add(Employee employee) {
        LOGGER.debug("Add new employee: ({})", employee);
        return employeeDao.add(employee);
    }

    @Override
    public void update(Employee employee) {
        LOGGER.debug("Update employee: ({})", employee);
        employeeDao.update(employee);
    }

    @Override
    public void delete(Integer employeeId) {
        LOGGER.debug("Delete employee with specified id: ({})", employeeId);
        employeeDao.delete(employeeId);
    }

    @Override
    public List<Employee> filterEmployee(String lastName){
        LOGGER.debug("Get filter employees by last name: ({})", lastName);
        return  employeeDao.filterEmployee(lastName);
    }

    @Override
    public List<Employee> filterEmployeeByDate(LocalDate localDateStart, LocalDate localDateEnd) {
        LOGGER.debug("Get filter employees by date: ({} : {})", localDateStart, localDateEnd);
        return  employeeDao.filterEmployeeByDate(localDateStart, localDateEnd);
    }
}
