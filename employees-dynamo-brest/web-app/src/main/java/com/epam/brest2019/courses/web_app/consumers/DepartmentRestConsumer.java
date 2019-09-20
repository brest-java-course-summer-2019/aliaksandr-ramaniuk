package com.epam.brest2019.courses.web_app.consumers;

import com.epam.brest2019.courses.model.Department;
import com.epam.brest2019.courses.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Department Consumer.
 */
public class DepartmentRestConsumer implements DepartmentService {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentRestConsumer.class);

    private String url;

    private RestTemplate restTemplate;

    public DepartmentRestConsumer(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Department> findAll() {
        LOGGER.debug("Find all departments");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/", List.class);
        return (List<Department>) responseEntity.getBody();
    }

    @Override
    public Department findById(Integer departmentId) {
        LOGGER.debug("Find department with specified id (departmentId): ({})", departmentId);
        ResponseEntity<Department> responseEntity = restTemplate.getForEntity(url + "/" + departmentId, Department.class);
        return responseEntity.getBody();
    }

    @Override
    public Department add(Department department) {
        LOGGER.debug("Add new department: ({})", department);
        ResponseEntity responseEntity = restTemplate.postForEntity(url, department, Department.class);
        Object result = responseEntity.getBody();
        return (Department) result;
    }

    @Override
    public void update(Department department) {
        LOGGER.debug("Update department: ({})", department);
        restTemplate.put(url, department);
    }

    @Override
    public void delete(Integer departmentId) {
        LOGGER.debug("Delete department with specified id (departmentId): ({})", departmentId);
        restTemplate.delete(url + "/" + departmentId);

    }

    @Override
    public List<Department> findAllCountEmployeesInDepartment() {
        LOGGER.debug("Find all departments with the count of employees in each department");
        ResponseEntity responseEntity = restTemplate.getForEntity(url + "/with_total_count_employees", List.class);
        return (List<Department>) responseEntity.getBody();

    }
}