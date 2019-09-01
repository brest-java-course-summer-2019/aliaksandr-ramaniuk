package com.epam.brest2019.courses.web_app.validators;

import com.epam.brest2019.courses.model.Employee;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EmployeeValidator implements Validator {

    public static final int EMPLOYEE_LOGIN_MAX_SIZE = 40;
    public static final int EMPLOYEE_LAST_NAME_MAX_SIZE = 40;
    public static final String EMPLOYEE_LOGIN = "login";
    public static final String EMPLOYEE_LAST_NAME = "lastName";
    public static final String EMPLOYEE_LOGIN_IS_EMPTY = "login.empty";
    public static final String EMPLOYEE_LAST_NAME_IS_EMPTY = "lastName.empty";


    private Employee employee;

    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        employee = (Employee) target;

        ValidationUtils.rejectIfEmpty(errors, EMPLOYEE_LOGIN, EMPLOYEE_LOGIN_IS_EMPTY);

        if (StringUtils.hasLength(employee.getLogin())
                && employee.getLogin().length() > EMPLOYEE_LOGIN_MAX_SIZE) {
            errors.rejectValue(EMPLOYEE_LOGIN, "login.maxSize40");
        }

        ValidationUtils.rejectIfEmpty(errors, EMPLOYEE_LAST_NAME, EMPLOYEE_LAST_NAME_IS_EMPTY);

        if (StringUtils.hasLength(employee.getLastName())
                && employee.getLastName().length() > EMPLOYEE_LAST_NAME_MAX_SIZE) {
            errors.rejectValue(EMPLOYEE_LAST_NAME, "lastName.maxSize40");
        }

    }
}