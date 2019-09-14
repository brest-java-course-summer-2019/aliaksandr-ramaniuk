package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Department;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Department DAO Interface implementation.
 */

// java -jar ~/soft/h2/bin/h2-1.4.199.jar

@Component
public class DepartmentDaoJdbcImpl implements DepartmentDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${department.findAll}")
    private String findAllSql;

    @Value("${department.findById}")
    private String findByIdSql;

    @Value("${department.add}")
    private String addSql;

    @Value("${department.update}")
    private String updateSql;

    @Value("${department.delete}")
    private String deleteSql;

    @Value("${department.findAllCountEmployeesInDepartment}")
    private String findAllCountEmployeesInDepartmentSql;

    private static final String DEPARTMENT_ID = "departmentId";
    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String DEPARTMENT_ACCESS_RIGHTS = "departmentAccessRights";
    private static final String FAILED_TO_UPDATE = "Failed to update department in Database!";
    private static final String FAILED_TO_DELETE = "Failed to delete department from Database!";

    public DepartmentDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments =
                namedParameterJdbcTemplate.query(findAllSql, BeanPropertyRowMapper.newInstance(Department.class));
        return departments;
    }

    @Override
    public Optional<Department> findById(Integer departmentId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        List<Department> results = namedParameterJdbcTemplate.query(findByIdSql, namedParameters,
                BeanPropertyRowMapper.newInstance(Department.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public Department add(Department department) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(DEPARTMENT_NAME, department.getDepartmentName().toUpperCase());
        parameters.addValue(DEPARTMENT_ACCESS_RIGHTS, department.getDepartmentAccessRights().toLowerCase());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(addSql, parameters, generatedKeyHolder);
        department.setDepartmentId(generatedKeyHolder.getKey().intValue());
        return department;
    }

    @Override
    public void update(Department department) {
        department.setDepartmentName(department.getDepartmentName().toUpperCase());
        Optional.of(namedParameterJdbcTemplate.update(updateSql, new BeanPropertySqlParameterSource(department)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException(FAILED_TO_UPDATE));
    }

    @Override
    public void delete(Integer departmentId) {
        MapSqlParameterSource deleteParameter = new MapSqlParameterSource();
        deleteParameter.addValue(DEPARTMENT_ID, departmentId);
        Optional.of(namedParameterJdbcTemplate.update(deleteSql, deleteParameter))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException(FAILED_TO_DELETE));
    }

    public List<Department> findAllCountEmployeesInDepartment() {
        List<Department> departments = namedParameterJdbcTemplate.query(findAllCountEmployeesInDepartmentSql,
                BeanPropertyRowMapper.newInstance(Department.class));
        return departments;
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

}