package com.epam.brest2019.course.web_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Root controller.
 */
@Controller
public class HomeController {

    /**
     * Redirect to default page -> departments.
     */

    @GetMapping(value = "/")
    public String defaultPageRedirect() {
        return "redirect:departments";
    }
}