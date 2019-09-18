package com.epam.brest2019.courses.dao;

import com.epam.brest2019.courses.model.Employee;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// java -jar ~/soft/h2/bin/h2-1.4.199.jar

/**
 * Employee DAO Interface implementation.
 */
@Component
public class EmployeeDaoJdbcImpl implements EmployeeDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Value("${employee.findAll}")
    private String findAllSql;

    @Value("${employee.findAll2}")
    private String findAllSql2;

    @Value("${employee.findByDepartmentId}")
    private String findByDepartmentIdSql;

    @Value("${employee.findById}")
    private String findByIdSql;

    @Value("${employee.add}")
    private String addSql;

    @Value("${employee.update}")
    private String updateSql;

    @Value("${employee.delete}")
    private String deleteSql;

    @Value("${employee.filterEmployee}")
    private String filterEmployeeSql;

    @Value("${employee.filterEmployeeByDate}")
    private String filterEmployeeByDateSql;

    private static final String DEPARTMENT_ID = "departmentId";
    private static final String EMPLOYEE_ID = "employeeId";
    private static final String LOGIN = "login";
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME = "firstName";
    private static final String PATRONIC_NAME = "patronicName";
    private static final String LOCAL_DATE = "localDate";
    private static final String LOCAL_DATE1 = "localDate1";
    private static final String LOCAL_DATE2 = "localDate2";
    private static final String FAILED_TO_UPDATE = "Failed to update employee in Database!";
    private static final String FAILED_TO_DELETE = "Failed to delete employee from Database!";

    public EmployeeDaoJdbcImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees =
                namedParameterJdbcTemplate.query(findAllSql, BeanPropertyRowMapper.newInstance(Employee.class));
        return employees;
    }

    @Override
    public List<Employee> findAll2() {
        List<Employee> employees =
                namedParameterJdbcTemplate.query(findAllSql2, BeanPropertyRowMapper.newInstance(Employee.class));
        return employees;
    }

    @Override
    public List<Employee> findByDepartmentId(Integer departmentId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        List<Employee> results = namedParameterJdbcTemplate.query(findByDepartmentIdSql, namedParameters,
                BeanPropertyRowMapper.newInstance(Employee.class));
        return results;
    }

    @Override
    public Optional<Employee> findById(Integer employeeId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(EMPLOYEE_ID, employeeId);
        List<Employee> results = namedParameterJdbcTemplate.query(findByIdSql, namedParameters,
                BeanPropertyRowMapper.newInstance(Employee.class));
        return Optional.ofNullable(DataAccessUtils.uniqueResult(results));
    }

    @Override
    public Employee add(Employee employee) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(LOGIN, employee.getLogin().toLowerCase());
        parameters.addValue(LAST_NAME, employee.getLastName().toUpperCase());
        parameters.addValue(FIRST_NAME, employee.getFirstName().toUpperCase());
        parameters.addValue(PATRONIC_NAME, employee.getPatronicName().toUpperCase());
        parameters.addValue(LOCAL_DATE, employee.getLocalDate());
        parameters.addValue(DEPARTMENT_ID, employee.getDepartmentId());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(addSql, parameters, generatedKeyHolder);
        employee.setEmployeeId(generatedKeyHolder.getKey().intValue());
        return employee;
    }

    @Override
    public void update(Employee employee) {
        employee.setLastName(employee.getLastName().toUpperCase());
        employee.setFirstName(employee.getFirstName().toUpperCase());
        employee.setPatronicName(employee.getPatronicName().toUpperCase());
        Optional.of(namedParameterJdbcTemplate.update(updateSql, new BeanPropertySqlParameterSource(employee)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException(FAILED_TO_UPDATE));
    }

    @Override
    public void delete(Integer employeeId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(EMPLOYEE_ID, employeeId);
        Optional.of(namedParameterJdbcTemplate.update(deleteSql, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException(FAILED_TO_DELETE));
    }

    @Override
    public List<Employee> filterEmployee(String lastName) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(LAST_NAME, "%" + lastName.toUpperCase() + "%");
        List<Employee> resultsFilterEmployee = namedParameterJdbcTemplate.query(filterEmployeeSql, namedParameters,
                BeanPropertyRowMapper.newInstance(Employee.class));

        return resultsFilterEmployee;
    }

    @Override
    public List<Employee> filterEmployeeByDate(LocalDate localDate1, LocalDate localDate2) {

//        MapSqlParameterSource parameters = new MapSqlParameterSource();
//
//        int i = 1;
//        for (LocalDate localDate : LocalDate) {
//            String LOCAL_DATE_FILTER = LOCAL_DATE + i;
//            parameters.addValue(LOCAL_DATE_FILTER, localDate);
//            i++;
//        }
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(LOCAL_DATE1, localDate1).addValue(LOCAL_DATE2, localDate2);
        List<Employee> resultsFilterDate = namedParameterJdbcTemplate.query(filterEmployeeByDateSql, namedParameters,
                BeanPropertyRowMapper.newInstance(Employee.class));

        return resultsFilterDate;
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }
}