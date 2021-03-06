package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.dao.DepartmentDao;
import com.epam.brest2019.courses.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Department Service Interface implementation.
 */

@Component
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private static final String FAILED_TO_GET_DEPARTMENT = "Failed to get department from Database by id";

    private final static Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public List<Department> findAll() {
        LOGGER.debug("Find all departments");
        return departmentDao.findAll();
    }


    @Override
    public Department findById(Integer departmentId) {
        LOGGER.debug("Find department with specified id: ({})", departmentId);
        return departmentDao.findById(departmentId)
                .orElseThrow(() -> new RuntimeException(FAILED_TO_GET_DEPARTMENT));
    }

    @Override
    public Department add(Department department) {
        LOGGER.debug("Add new department: ({})", department);
        return departmentDao.add(department);
    }

    @Override
    public void update(Department department) {
        LOGGER.debug("Update department: ({})", department);
        departmentDao.update(department);
    }

    @Override
    public void delete(Integer departmentId) {
        LOGGER.debug("Delete department with specified id: ({})", departmentId);
        departmentDao.delete(departmentId);
    }

    @Override
    public List<Department> findAllCountEmployeesInDepartment() {
        LOGGER.debug("Find all departments with the count of employees in each department");
        return departmentDao.findAllCountEmployeesInDepartment();
    }
}