package com.tsystems.controller.employee;

import com.tsystems.dto.CityDTO;
import com.tsystems.dto.DriverDTO;
import com.tsystems.entity.Driver;
import com.tsystems.service.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class DriverController {
    private DriverService driverService;

    @Autowired
    public void setDriverService(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/drivers")
    public String empDriversIndexPage(Model model) {
        model.addAttribute("driverList", driverService.getAllDrivers());
        return "views/emp/empDriver";
    }

    @GetMapping("/driver/add")
    public String addNewDriverPage(Model model) {
        model.addAttribute("driver", new DriverDTO());
        return "/views/emp/empAddDriver";
    }

    @PostMapping("/driver/add")
    @Transactional
    public String addNewDriver(@ModelAttribute("driver") DriverDTO driver , @RequestParam("cityId") Integer cityId) {
        CityDTO city = new CityDTO();
        // cityId + 1 cuz in select count start from 0, but city ids from 1
        city.setId(cityId + 1);
        driver.setCurrent_city(city);
        driverService.addDriver(driver);
        return "redirect:/employee/drivers";
    }


    @GetMapping("driver/update={driverId}")
    public String updateDriverPage(@PathVariable("driverId") Integer driverId, Model model) {
        Driver driver = driverService.findDriverById(driverId);
        DriverDTO dto = new DriverDTO();
        dto.setId(driver.getId());
        dto.setFirst_name(driver.getFirst_name());
        dto.setSecond_name(driver.getSecond_name());
        model.addAttribute("driver", dto);
        return "views/emp/empEditDriver";
    }

    @PostMapping("driver/update={driverId}")
    public String updateDriver(@ModelAttribute("driver") DriverDTO dto) {
        driverService.updateDriver(dto);
        return "redirect:/employee/drivers";
    }

    @PostMapping("driver/delete={driverId}")
    @Transactional
    public String deleteDriver(@PathVariable("driverId") Integer id) {
        driverService.deleteDriver(driverService.findDriverById(id));
        return "redirect:/employee/drivers";
    }
}
