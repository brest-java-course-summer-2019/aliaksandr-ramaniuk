package com.epam.brest2019.courses.service;

import com.epam.brest2019.courses.dao.DepartmentDao;
import com.epam.brest2019.courses.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Department Service Interface implementation.
 */

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final static Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    private DepartmentDao dao;

    public DepartmentServiceImpl(DepartmentDao dao) {
        this.dao = dao;
    }

    @Override
    public Department add(Department department) {
        LOGGER.debug("Add department: {}", department);
        return dao.add(department);
    }

    @Override
    public void update(Department department) {
        LOGGER.debug("Update department: {}", department);
        dao.update(department);
    }

    @Override
    public void delete(Integer departmentId) {
        LOGGER.debug("Delete department: {}", departmentId);
        dao.delete(departmentId);
    }

    @Override
    public List<Department> findAll() {
        LOGGER.debug("Find all departments");
        return dao.findAll();
    }

    @Override
    public Optional<Department> findById(Integer departmentId) {
        LOGGER.debug("Find by department Id : {}", departmentId);
        return dao.findById(departmentId);
    }


}