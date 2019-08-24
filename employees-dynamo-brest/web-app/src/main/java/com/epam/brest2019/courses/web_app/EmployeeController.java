package com.epam.brest2019.courses.web_app;


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
     * Goto employee add page.
     *
     * @return view name
     */
    @GetMapping(value = "/employee_Add")
    public final String add_Employee() {
        return "employee";
    }

    /**
     * Goto employee edit page.
     *
     * @return view name
     */
    @GetMapping(value = "/employee_Edit")
    public final String gotoEditEmployeePage() {
        return "employee_Edit";
    }
}