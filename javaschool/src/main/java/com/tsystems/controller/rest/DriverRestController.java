package com.tsystems.controller.rest;

import com.tsystems.Util.ConverterUtil;
import com.tsystems.entity.Converter.Converter;
import com.tsystems.entity.Driver;
import com.tsystems.service.api.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DriverRestController {
    private DriverService driverService;

    @Autowired
    public DriverRestController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("scoreboard/driver/get/list")
    public String getDriversToScoreBoard() {
        List<Driver> drivers = driverService.getAllDrivers();
        return ConverterUtil.parseJson(Converter.getDriverDTOs(drivers));
    }
}
