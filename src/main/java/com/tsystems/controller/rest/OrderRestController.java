package com.tsystems.controller.rest;

import com.tsystems.Util.ConverterUtil;
import com.tsystems.entity.Cargo;
import com.tsystems.entity.Converter.Converter;
import com.tsystems.service.api.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderRestController {
    private CargoService cargoService;

    @Autowired
    public void setCargoService(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping("employee/order/cargolist")
    public String getAllCargoes() {
        List<Cargo> cargoList = cargoService.getAllCargoes();
        return ConverterUtil.parseJson(Converter.getCargoDtos(cargoList));
    }
}
