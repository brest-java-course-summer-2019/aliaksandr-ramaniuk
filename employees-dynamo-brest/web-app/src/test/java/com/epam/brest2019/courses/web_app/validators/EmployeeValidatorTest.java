package com.epam.brest2019.courses.web_app.validators;

import com.epam.brest2019.courses.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.thymeleaf.util.StringUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeeValidatorTest {
    Employee employee;

    EmployeeValidator employeeValidator = new EmployeeValidator();
    BindingResult result;

    @BeforeEach
    void setup() {
        employee = Mockito.mock(Employee.class);
        result = new BeanPropertyBindingResult(employee, "employee");
    }

    @Test
    void shouldRejectNullEmployeeLogin() {
        // given
        Mockito.when(employee.getLogin()).thenReturn(null);

        // when
        employeeValidator.validate(employee, result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectNullEmployeeLastName() {
        // given
        Mockito.when(employee.getLastName()).thenReturn(null);

        // when
        employeeValidator.validate(employee, result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectEmptyEmployeeLogin() {
        // given
        Mockito.when(employee.getLogin()).thenReturn("");

        // when
        employeeValidator.validate(employee, result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectEmptyEmployeeLastName() {
        // given
        Mockito.when(employee.getLastName()).thenReturn("");

        // when
        employeeValidator.validate(employee, result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectLargeEmployeeLogin() {

        // given
        String filled = StringUtils.repeat("*", 50);
        Mockito.when(employee.getLogin()).thenReturn(filled);

        // when
        employeeValidator.validate(employee, result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectLargeEmployeeLastName() {

        // given
        String filled = StringUtils.repeat("*", 50);
        Mockito.when(employee.getLastName()).thenReturn(filled);

        // when
        employeeValidator.validate(employee, result);

        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldValidateEmployeeLogin() {

        // given
        String filled = StringUtils.repeat("*", 5);
        Mockito.when(employee.getLogin()).thenReturn(filled);

        // when
        employeeValidator.validate(employee, result);

        // then
        // problem
        assertTrue(result.hasErrors());
}

    @Test
    void shouldValidateEmployeeLastName() {

        // given
        String filled = StringUtils.repeat("*", 5);
        Mockito.when(employee.getLastName()).thenReturn(filled);

        // when
        employeeValidator.validate(employee, result);

        // then
        // problem
        assertTrue(result.hasErrors());

    }
}
