package com.tsystems.controller;

import com.tsystems.entity.Cargo;
import com.tsystems.entity.Driver;
import com.tsystems.service.api.CargoService;
import com.tsystems.service.api.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private DriverService driverService;
    private CargoService cargoService;

    @Autowired
    public void setDriverService(DriverService driverService) {
        this.driverService = driverService;
    }

    @Autowired
    public void setCargoService(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping("/drivers")
    public ModelAndView empDriversIndexPage() {
        ModelAndView mv = new ModelAndView();
        List<Driver> driverList = driverService.getAllDrivers();
        mv.setViewName("views/employeeDriver");
        mv.addObject("driverList", driverList);
        return mv;
    }

    @GetMapping("/cargoes")
    public ModelAndView emfCargoesIndexPage() {
        ModelAndView mv = new ModelAndView();
        List<Cargo> cargoList = cargoService.getAllCargoes();
        mv.setViewName("views/employeeCargo");
        mv.addObject("cargoList", cargoList);
        return mv;
    }
}
