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

class EmployeeValidatorTest {

    private static final String EMPLOYEE = "employee";

    Employee employee;

    EmployeeValidator employeeValidator = new EmployeeValidator();
    BindingResult result;

    @BeforeEach
    void setup() {
        employee = Mockito.mock(Employee.class);
        result = new BeanPropertyBindingResult(employee, EMPLOYEE);
    }

       // Should Reject Null
    @Test
    void shouldRejectNullLogin() {
        // given
        Mockito.when(employee.getLogin()).thenReturn(null);
        // when
        employeeValidator.validate(employee, result);
        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectNullLastName() {
        // given
        Mockito.when(employee.getLastName()).thenReturn(null);
        // when
        employeeValidator.validate(employee, result);
        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectNullFirstName() {
        // given
        Mockito.when(employee.getFirstName()).thenReturn(null);
        // when
        employeeValidator.validate(employee, result);
        // then
        assertTrue(result.hasErrors());
    }


    //    Should Reject Empty
    @Test
    void shouldRejectEmptyLogin() {
        // given
        Mockito.when(employee.getLogin()).thenReturn("");
        // when
        employeeValidator.validate(employee, result);
        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectEmptyLastName() {
        // given
        Mockito.when(employee.getLastName()).thenReturn("");
        // when
        employeeValidator.validate(employee, result);
        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectEmptyFirstName() {
        // given
        Mockito.when(employee.getFirstName()).thenReturn("");
        // when
        employeeValidator.validate(employee, result);
        // then
        assertTrue(result.hasErrors());
    }


    //    Should Reject Large
    @Test
    void shouldRejectLargeLogin() {
        // given
        String filled = StringUtils.repeat("*", 50);
        Mockito.when(employee.getLogin()).thenReturn(filled);
        // when
        employeeValidator.validate(employee, result);
        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectLargeLastName() {
        // given
        String filled = StringUtils.repeat("*", 50);
        Mockito.when(employee.getLastName()).thenReturn(filled);
        // when
        employeeValidator.validate(employee, result);
        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectLargeFirstName() {
        // given
        String filled = StringUtils.repeat("*", 50);
        Mockito.when(employee.getFirstName()).thenReturn(filled);
        // when
        employeeValidator.validate(employee, result);
        // then
        assertTrue(result.hasErrors());
    }

    @Test
    void shouldRejectLargePatronicName() {
        // given
        String filled = StringUtils.repeat("*", 50);
        Mockito.when(employee.getPatronicName()).thenReturn(filled);
        // when
        employeeValidator.validate(employee, result);
        // then
        assertTrue(result.hasErrors());
    }


    //    Should Validate
    @Test
    void shouldValidateLogin() {
        // given
        String filled = StringUtils.repeat("*", 30);
        Mockito.when(employee.getLogin()).thenReturn(filled);
        // when
        employeeValidator.validate(employee, result);
        // then
        assertFalse(!result.hasErrors());
    }

    @Test
    void shouldValidateLastName() {
        // given
        String filled = StringUtils.repeat("*", 30);
        Mockito.when(employee.getLastName()).thenReturn(filled);
        // when
        employeeValidator.validate(employee, result);
        // then
        assertFalse(!result.hasErrors());
    }

    @Test
    void shouldValidateFirstName() {
        // given
        String filled = StringUtils.repeat("*", 30);
        Mockito.when(employee.getFirstName()).thenReturn(filled);
        // when
        employeeValidator.validate(employee, result);
        // then
        assertFalse(!result.hasErrors());
    }

    @Test
    void shouldValidatePatronicName() {
        // given
        String filled = StringUtils.repeat("*", 30);
        Mockito.when(employee.getPatronicName()).thenReturn(filled);
        // when
        employeeValidator.validate(employee, result);
        // then
        assertFalse(!result.hasErrors());
    }

}