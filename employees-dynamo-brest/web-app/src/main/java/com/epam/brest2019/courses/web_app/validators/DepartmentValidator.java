package com.epam.brest2019.courses.web_app.validators;

import com.epam.brest2019.courses.model.Department;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class DepartmentValidator implements Validator {

    public static final int DEPARTMENT_NAME_MAX_SIZE = 255;

    @Override
    public boolean supports(Class<?> clazz) {
        return Department.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "departmentName", "departmentName.empty");
        Department department = (Department) target;

        if (StringUtils.hasLength(department.getDepartmentName())
                && department.getDepartmentName().length() > DEPARTMENT_NAME_MAX_SIZE) {
            errors.rejectValue("departmentName", "departmentName.maxSize255");
        }
    }
}