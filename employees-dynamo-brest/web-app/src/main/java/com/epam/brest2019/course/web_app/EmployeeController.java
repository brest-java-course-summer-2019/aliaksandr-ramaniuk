package com.epam.brest2019.course.web_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Employee controller.
 */
@Controller
public class EmployeeController {

    /**
     * Goto employees page.
     *
     * @return view name
     */
    @GetMapping(value = "/employees")
    public final String employees() {
        return "employees";
    }

    /**
     * Goto employees_Add page.
     *
     * @return view name
     */
    @GetMapping(value = "/employees_Add")
    public final String employeesAdd() {
        return "employees_Add";
    }

    /**
     * Goto employees_Edit page.
     *
     * @return view name
     */
    @GetMapping(value = "/employees_Edit")
    public final String employeesEdit() {
        return "employees_Edit";
    }
}