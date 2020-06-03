package com.tsystems.controller.rest;

import com.tsystems.Util.ConverterUtil;
import com.tsystems.entity.Converter.Converter;
import com.tsystems.entity.Order;
import com.tsystems.entity.Wagon;
import com.tsystems.service.api.WagonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WagonRestController {
    private WagonService wagonService;

    @Autowired
    public void setWagonService(WagonService wagonService) {
        this.wagonService = wagonService;
    }

    @GetMapping("/wagonList")
    public String getAllWagons() {
        List<Wagon> wagonList = wagonService.getAllWagons();
        return ConverterUtil.parseJson(Converter.getWagonDtos(wagonList));
    }
}
