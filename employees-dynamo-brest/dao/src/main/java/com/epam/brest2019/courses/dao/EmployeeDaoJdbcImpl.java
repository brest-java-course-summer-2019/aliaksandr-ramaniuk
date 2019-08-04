package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Employee;
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

// java -jar ~/soft/h2/bin/h2-1.4.199.jar

/**
 * Employee DAO Interface implementation.
 */
@Component
public class EmployeeDaoJdbcImpl implements EmployeeDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final static String SELECT_ALL =
            "SELECT employee_id, login, last_name, first_name, patronic_name, local_date, department_id FROM employee ORDER BY employee_id";

    private static final String FIND_BY_ID =
            "SELECT employee_id, login, last_name, first_name, patronic_name, local_date, department_id " +
                    "FROM employee WHERE employee_id = :employeeId";

    private static final String FIND_BY_DEPARTMENT_ID =
            "SELECT employee_id, login, last_name, first_name, patronic_name, local_date, department_id " +
                    "FROM employee WHERE department_id = :departmentId";

    private final static String ADD_EMPLOYEE = "INSERT INTO employee (login, last_name, first_name, patronic_name, local_date, department_id ) VALUES " +
            "(:login, :lastName, :firstName, :patronicName, :localDate, :departmentId)";

    private static final String UPDATE_EMPLOYEE =
            "UPDATE employee SET login = :login, last_name = :lastName, first_name = :firstName, patronic_name = :patronicName, " +
                    "local_date = :localDate, department_id = :departmentId WHERE employee_id = :employeeId";

    private static final String DELETE_EMPLOYEE =
            "DELETE FROM employee WHERE employee_id = :employeeId";

    private static final String TOTAL_COUNT_OF_EMPLOYEES =
            "SELECT COUNT (employee_id) FROM employee";

    private static final String FIND_BY_LAST_NAME =
            "SELECT employee_id, login, last_name, first_name, patronic_name, local_date, department_id FROM employee " +
                    "WHERE LAST_NAME  LIKE last_name = ':lastName%'";

    private static final String DEPARTMENT_ID = "departmentId";
    private static final String EMPLOYEE_ID = "employeeId";
    private static final String LAST_NAME = "lastName";

    public EmployeeDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees =
                namedParameterJdbcTemplate.query(SELECT_ALL, BeanPropertyRowMapper.newInstance(Employee.class));
        return employees;
    }


    @Override
    public List<Employee> findByDepartmentId(Integer departmentId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        List<Employee> results = namedParameterJdbcTemplate.query(FIND_BY_DEPARTMENT_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Employee.class));

        return results;
    }

    @Override
    public Optional<Employee> findById(Integer employeeId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(EMPLOYEE_ID, employeeId);
        List<Employee> results = namedParameterJdbcTemplate.query(FIND_BY_ID, namedParameters,
                BeanPropertyRowMapper.newInstance(Employee.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public Employee add(Employee employee) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("login", employee.getLogin());
        parameters.addValue("lastName", employee.getLastName());
        parameters.addValue("firstName", employee.getFirstName());
        parameters.addValue("patronicName", employee.getPatronicName());
        parameters.addValue("localDate", employee.getLocalDate());
        parameters.addValue("departmentId", employee.getDepartmentId());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(ADD_EMPLOYEE, parameters, generatedKeyHolder);
        employee.setEmployeeId(generatedKeyHolder.getKey().intValue());
        return employee;
    }

    @Override
    public void update(Employee employee) {
        Optional.of(namedParameterJdbcTemplate.update(UPDATE_EMPLOYEE, new BeanPropertySqlParameterSource(employee)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update employee in Database"));
    }

    @Override
    public void delete(Integer employeeId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(EMPLOYEE_ID, employeeId);
        Optional.of(namedParameterJdbcTemplate.update(DELETE_EMPLOYEE, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete employee from Database"));
    }

    @Override
    public int totalCountOfEmployees() {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        return namedParameterJdbcTemplate.queryForObject(
                TOTAL_COUNT_OF_EMPLOYEES, mapSqlParameterSource, Integer.class);
        namedParameterJdbcTemplate.quer
    }

    public List<Employee> filterEmployee(String lastName) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(LAST_NAME, lastName);
        List<Employee> resultsFilter = namedParameterJdbcTemplate.query(FIND_BY_LAST_NAME, namedParameters,
                BeanPropertyRowMapper.newInstance(Employee.class));
        return resultsFilter;
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }
}