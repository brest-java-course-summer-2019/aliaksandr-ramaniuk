package com.epam.brest2019.courses.web_app.validators;

import com.epam.brest2019.courses.model.Employee;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class EmployeeValidator implements Validator {


    public static final String IS_NULL = " is null! ";

    public static final int EMPLOYEE_MAX_SIZE = 40;
    public static final String EMPLOYEE_MAX_SIZE_40 = ".maxSize40";
    public static final String EMPLOYEE_IS_EMPTY = ".empty";

    public static final String EMPLOYEE_LOGIN = "login";
    public static final String EMPLOYEE_FIRST_NAME = "firstName";
    public static final String EMPLOYEE_LAST_NAME = "lastName";
    public static final String EMPLOYEE_PATRONIC_NAME = "patronicName";


    private Employee employee;

    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        employee = (Employee) target;

        checkField(EMPLOYEE_LOGIN, employee.getLogin(), errors);
        checkField(EMPLOYEE_LAST_NAME, employee.getLastName(), errors);
        checkField(EMPLOYEE_FIRST_NAME, employee.getFirstName(), errors);
        checkField(EMPLOYEE_PATRONIC_NAME, employee.getPatronicName(), errors);

    }

    private void checkField(String fieldName, String fieldValue, Errors errors){

        fieldValue = fieldValue.trim();

        ValidationUtils.rejectIfEmpty(errors, fieldName, fieldName + EMPLOYEE_IS_EMPTY);

        if(fieldValue == null){
            errors.rejectValue(fieldName, IS_NULL + fieldName + EMPLOYEE_IS_EMPTY);
        }
        if (StringUtils.hasLength(fieldValue)
                && fieldValue.length() > EMPLOYEE_MAX_SIZE) {
            errors.rejectValue(fieldName, fieldName + EMPLOYEE_MAX_SIZE_40);
        }

    }

}