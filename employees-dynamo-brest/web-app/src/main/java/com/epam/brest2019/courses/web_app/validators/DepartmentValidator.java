package com.epam.brest2019.courses.web_app.validators;

import com.epam.brest2019.courses.model.Department;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class DepartmentValidator implements Validator {

    public static final String IS_NULL = " is null! ";

    public static final Integer DEPARTMENT_NAME_MAX_SIZE = 40;
    public static final String DEPARTMENT_NAME = "departmentName";
    public static final String DEPARTMENT_NAME_IS_EMPTY = "departmentName.empty";
    public static final String DEPARTMENT_NAME_MAX_SIZE_40 = "departmentName.maxSize40";


    @Override
    public boolean supports(Class<?> clazz) {
        return Department.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Department department = (Department) target;

        ValidationUtils.rejectIfEmpty(errors, DEPARTMENT_NAME, DEPARTMENT_NAME_IS_EMPTY);

        if(department.getDepartmentName() == null){
            errors.rejectValue(DEPARTMENT_NAME, IS_NULL + DEPARTMENT_NAME_IS_EMPTY);
        }

        if (StringUtils.hasLength(department.getDepartmentName())
                && department.getDepartmentName().length() > DEPARTMENT_NAME_MAX_SIZE) {
            errors.rejectValue(DEPARTMENT_NAME, DEPARTMENT_NAME_MAX_SIZE_40);
        }
    }
}