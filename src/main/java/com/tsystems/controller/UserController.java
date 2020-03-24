package com.tsystems.controller;

import com.tsystems.entity.User;
import com.tsystems.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public ModelAndView registration() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/registration");
        mv.addObject("user", new User());
        return mv;
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, Model model) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView mv = new ModelAndView();
        if (error != null) {
            mv.addObject("error", "Invalid Credentials provided.");
        }
        if (logout != null) {
            mv.addObject("message", "Logged out successfully.");
        }
        mv.setViewName("login2");
        return mv;
    }
}
