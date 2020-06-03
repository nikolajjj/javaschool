package com.tsystems.controller.support;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Dispatches queries related to home page
 */

@Controller
public class HomeController {
    /**
     * Returns the home page.
     * @return index.jsp
     */
    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/home")
    public String homePage() {
        return "homePage";
    }

    @GetMapping("/denied")
    public String deniedPage() {
        return "template/accessDenied";
    }

    @GetMapping("NotFound")
    public String notFoundPage() {
        return "template/notFound";
    }
    /**
     *
     * @return
     */
    @GetMapping("/employee")
    public String employeePage() {
        return "views/emp/employees";
    }

    @GetMapping("/driver")
    public String driverPage() {
        return "views/driver/drivers";
    }
}
