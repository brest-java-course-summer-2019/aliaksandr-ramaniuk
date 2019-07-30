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


/**
 * Employee DAO Interface implementation.
 */
@Component
public class EmployeeDaoJdbcImpl implements EmployeeDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final static String SELECT_ALL =
            "SELECT employee_id, login, last_name, first_name, patronic_name, department_id from employee order by 2, 3";

    private static final String FIND_BY_ID =
            "SELECT employee_id, login, last_name, first_name, patronic_name, department_id " +
                    "from employee where employee_id = :employeeId";

    private static final String FIND_BY_DEPARTMENT_ID =
            "SELECT employee_id, login, last_name, first_name, patronic_name, department_id " +
                    "from employee where department_id = :departmentId";

    private final static String ADD_EMPLOYEE = "INSERT INTO employee (login, last_name, first_name, patronic_name, department_id) values " +
            "(:login, :lastName, :firstName, :patronicName, :departmentId)";

    private static final String UPDATE_EMPLOYEE =
            "UPDATE employee set login = :login, last_name = :lastName, first_name = :firstName, patronic_name = :patronicName, " +
                    "department_id = :departmentId where employee_id = :employeeId";

    private static final String DELETE_EMPLOYEE =
            "DELETE from employee where employee_id = :employeeId";

    private static final String DEPARTMENT_ID = "departmentId";
    private static final String EMPLOYEE_ID = "employeeId";


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

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }
}