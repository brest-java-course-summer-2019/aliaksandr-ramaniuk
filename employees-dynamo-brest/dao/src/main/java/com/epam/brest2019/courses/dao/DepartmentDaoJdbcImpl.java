package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Department;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component
public class DepartmentDaoJdbcImpl implements DepartmentDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final static String SELECT_ALL =
            "select d.department_id, d.department_name from department d order by 2";

    private final static String ADD_DEPARTMENT = "insert into department (department_name) values (:departmentName)";

    private final static String UPDATE_DEPARTMENT = "update department set department_name = :departmentName where department_id = :departmentId";

    private final static String DELETE_DEPARTMENT = "delete from department where department_id = :departmentId";

    public DepartmentDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
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
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("departmentName", department.getDepartmentName());
        parameters.addValue("departmentId", department.getDepartmentId());

        namedParameterJdbcTemplate.update(UPDATE_DEPARTMENT, parameters);

    }

    @Override
    public void delete(Integer departmentId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("departmentId", departmentId);

        namedParameterJdbcTemplate.update(DELETE_DEPARTMENT, parameters);

    }

    @Override
    public List<Department> findAll() {
        List<Department> departments =
                namedParameterJdbcTemplate.query(SELECT_ALL, new DepartmentRowMapper());
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

}