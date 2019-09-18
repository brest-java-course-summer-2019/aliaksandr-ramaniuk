package com.epam.brest2019.courses.web_app.consumers;

import com.epam.brest2019.courses.model.Employee;
import com.epam.brest2019.courses.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

/**
 * Employee Consumer.
 */
public class EmployeeRestConsumer implements EmployeeService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRestConsumer.class);

    private String url;

    private RestTemplate restTemplate;

    public EmployeeRestConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Employee> findAll() {
        LOGGER.debug("Find all employees");
        ResponseEntity responseEntity1 = restTemplate.getForEntity(url + "/", List.class);
        return (List<Employee>) responseEntity1.getBody();
    }

    @Override
    public Employee findById(Integer employeeId) {
        LOGGER.debug("Find employee with specified id: ({})", employeeId);
        ResponseEntity<Employee> responseEntity1 = restTemplate.getForEntity(url + "/" + employeeId, Employee.class);
        return responseEntity1.getBody();
    }

    @Override
    public Employee add(Employee employee) {
        LOGGER.debug("Add new employee: ({})", employee);
        ResponseEntity responseEntity = restTemplate.postForEntity(url, employee, Employee.class);
        Object result = responseEntity.getBody();
        return (Employee) result;
    }

    @Override
    public void update(Employee employee) {
        LOGGER.debug("Update employee: ({})", employee);
        restTemplate.put(url, employee);
    }

    @Override
    public void delete(Integer employeeId) {
        LOGGER.debug("Delete employee with specified id: ({})", employeeId);
        restTemplate.delete(url + "/" + employeeId);
    }

    @Override
    public List<Employee> filterEmployee(String lastName){
        LOGGER.debug("Get filter employees by last name: ({})", lastName);
        ResponseEntity responseEntity2 = restTemplate.getForEntity(url + "2/" + lastName, List.class);
        return (List<Employee>) responseEntity2.getBody();
    }

    @Override
    public List<Employee> filterEmployeeByDate(LocalDate localDate1, LocalDate localDate2) {
        LOGGER.debug("Get filter employees by date: ({} : {})", localDate1, localDate2);
        ResponseEntity responseEntity3 = restTemplate.getForEntity(url + "/" + localDate1 + "/" + localDate2, List.class);
        return (List<Employee>) responseEntity3.getBody();
    }
}