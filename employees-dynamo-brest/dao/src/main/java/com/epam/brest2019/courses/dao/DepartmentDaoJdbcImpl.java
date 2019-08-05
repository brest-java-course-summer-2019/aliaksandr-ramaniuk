package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Department;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


/**
 * Department DAO Interface implementation.
 */

// java -jar ~/soft/h2/bin/h2-1.4.199.jar

@Component
public class DepartmentDaoJdbcImpl implements DepartmentDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final static String SELECT_ALL =
            "SELECT department_id, department_name FROM department ORDER BY department_name";

    private static final String FIND_BY_ID =
            "SELECT department_id, department_name FROM department WHERE department_id = :departmentId";

    private final static String ADD_DEPARTMENT =
            "INSERT INTO department (department_name) VALUES (:departmentName)";

    private final static String UPDATE_DEPARTMENT =
            "UPDATE department SET department_name = :departmentName WHERE department_id = :departmentId";

    private final static String DELETE_DEPARTMENT =
            "DELETE FROM department WHERE department_id = :departmentId";

    private final static String SELECT_ALL_COUNT_EMPLOYEES_IN_DEPARTMENT =
            "SELECT d.department_id, d.department_name, COUNT(e.employee_id) FROM department d NATURAL JOIN employee e GROUP BY d.department_id ORDER BY COUNT(e.employee_id)";


    private static final String DEPARTMENT_ID = "departmentId";

    public DepartmentDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments =
                namedParameterJdbcTemplate.query(SELECT_ALL, new DepartmentRowMapper());
        return departments;
    }

    @Override
    public Optional<Department> findById(Integer departmentId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        List<Department> results = namedParameterJdbcTemplate.query(FIND_BY_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Department.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public Department add(Department department) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("departmentName", department.getDepartmentName());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_DEPARTMENT, parameters, generatedKeyHolder);
        department.setDepartmentId(generatedKeyHolder.getKey().intValue());
        return department;
    }

    @Override
    public void update(Department department) {
        Optional.of(namedParameterJdbcTemplate.update(UPDATE_DEPARTMENT, new BeanPropertySqlParameterSource(department)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update department in Database!"));
    }

    @Override
    public void delete(Integer departmentId) {
        MapSqlParameterSource deletePasameter = new MapSqlParameterSource();
        deletePasameter.addValue(DEPARTMENT_ID, departmentId);
        Optional.of(namedParameterJdbcTemplate.update(DELETE_DEPARTMENT, deletePasameter))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete department from Database!"));
    }

    public List<Department> findAllCountEmployeesInDepartment(){
        List<Department> departments = namedParameterJdbcTemplate.query(SELECT_ALL_COUNT_EMPLOYEES_IN_DEPARTMENT,
                BeanPropertyRowMapper.newInstance(Department.class));
        return departments;
    }

    private class DepartmentRowMapper implements RowMapper<Department> {
        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {
            Department department = new Department();
            department.setDepartmentId(resultSet.getInt("department_id"));
            department.setDepartmentName(resultSet.getString("department_name"));
            return department;
        }
    }


    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

}