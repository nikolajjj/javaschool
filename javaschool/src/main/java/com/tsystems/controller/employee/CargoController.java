package com.tsystems.controller.employee;

import com.tsystems.dto.CargoDTO;
import com.tsystems.dto.CityDTO;
import com.tsystems.exception.CTCExecption;
import com.tsystems.service.api.CargoService;
import com.tsystems.service.api.CityService;
import com.tsystems.service.api.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee/cargo")
public class CargoController {
    private CargoService cargoService;
    private final static Logger log = Logger.getLogger(CargoController.class);

    @Autowired
    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping("/list")
    public String cargoListPage(Model model) {
        model.addAttribute("cargoList", cargoService.getAllCargoes());
        return "views/emp/empCargo";
    }

    @GetMapping("/add")
    public String createNewCargoPage(Model model) {
        model.addAttribute("cargo", new CargoDTO());
        return "views/emp/empAddCargo";
    }

    @PostMapping("/add")
    public String addNewCargo(@ModelAttribute("cargo") CargoDTO cargo,
                           @RequestParam("from") Integer city_from,
                           @RequestParam("to") Integer city_to) {
        log.info("Adding new cargo");
        CityDTO dto_from = new CityDTO();
        CityDTO dto_to = new CityDTO();
        // city id + 1 cuz in select count start from 0, but city ids from 1
        dto_from.setId(city_from + 1);
        dto_to.setId(city_to + 1);

        cargo.setCity_from(dto_from);
        cargo.setCity_to(dto_to);

        try {
            cargoService.addCargo(cargo);
        } catch (CTCExecption e) {
            log.info("Cargo was not created!");
            log.error(e.getMessage(), e);
        }
        return "redirect:/employee/cargo/list";
    }
}
