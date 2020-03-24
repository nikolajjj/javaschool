package com.tsystems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView homePage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("homePage");
        return mv;
    }
    /**
     *
     * @return
     */
    @GetMapping("/employee")
    public String employeePage() {
        return "views/employees";
    }

    @GetMapping("/driver")
    public String driverPage() {
        return "views/drivers";
    }
}
