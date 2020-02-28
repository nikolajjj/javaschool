package com.tsystems.controller;

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
}
