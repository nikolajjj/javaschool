package com.tsystems.controller.rest;

import com.tsystems.Util.ConverterUtil;
import com.tsystems.entity.Converter.Converter;
import com.tsystems.service.api.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityRestController {
    private CityService cityService;

    @Autowired
    public CityRestController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/cities/get/list")
    public String getAllCities() {
        return ConverterUtil.parseJson(Converter.getCityDtos(cityService.getAllCities()));
    }
}
