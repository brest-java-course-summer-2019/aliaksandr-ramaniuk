package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Employee;
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
public class EmployeeDaoJdbcImpl implements EmployeeDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final static String SELECT_ALL =
            "select e.employee_id, e.login, e.last_name, e.first_name, e.patronic_name, e.department_id, e.department_name from employee e order by 4";

    private final static String ADD_EMPLOYEE = "insert into employee (login, last_name, first_name, patronic_name, department_id, department_name) values " +
            "(:login, :firstName, :lastName, :patronicName, :departmentId, :departmentName)";


    public EmployeeDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Employee add(Employee employee) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("login",  employee.getLogin());
        parameters.addValue("firstName", employee.getFirstName());
        parameters.addValue("lastName", employee.getLastName());
        parameters.addValue("patronicName", employee.getPatronicName());
        parameters.addValue("departmentId", employee.getDepartmentId());
        parameters.addValue("departmentName", employee.getDepartmentName());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_EMPLOYEE, parameters, generatedKeyHolder);
        employee.setEmployeeId(generatedKeyHolder.getKey().intValue());
        return employee;
    }

    @Override
    public void update(Employee employee) {

    }

    @Override
    public void delete(Integer employeeId) {

    }


    @Override
    public List<Employee> findAll() {
        List<Employee> employees =
                namedParameterJdbcTemplate.query(SELECT_ALL, new EmployeeRowMapper());
        return employees;
    }

    private class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
            Employee employee = new Employee();
            employee.setEmployeeId(resultSet.getInt("employee_id"));
            employee.setLogin(resultSet.getString("login"));
            employee.setLastName(resultSet.getString("last_name"));
            employee.setFirstName(resultSet.getString("first_name"));
            employee.setPatronicName(resultSet.getString("patronic_name"));
            employee.setDepartmentId(resultSet.getInt("department_id"));
            employee.setDepartmentName(resultSet.getString("department_name"));
            return employee;
        }
    }
}