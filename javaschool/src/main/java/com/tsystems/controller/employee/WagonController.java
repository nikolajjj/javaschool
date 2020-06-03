package com.tsystems.controller.employee;

import com.tsystems.Util.ConverterUtil;
import com.tsystems.dto.CityDTO;
import com.tsystems.dto.WagonDTO;
import com.tsystems.entity.Wagon;
import com.tsystems.exception.CTCExecption;
import com.tsystems.service.api.WagonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@Controller
@RequestMapping("/employee/wagon")
public class WagonController {
    private WagonService wagonService;

    private final static Logger log = Logger.getLogger(WagonController.class);

    @Autowired
    public WagonController(WagonService wagonService) {
        this.wagonService = wagonService;
    }

    @GetMapping("/list")
    public String wagonList(Model model) {
        model.addAttribute("wagonList", wagonService.getAllWagons());
        return "views/emp/empWagon";
    }

    @GetMapping("/add")
    public String createNewWagonPage(Model model) {
        model.addAttribute("wagon", new WagonDTO());
        return "views/emp/empAddWagon";
    }

    @PostMapping("/add")
    public String addNewWagon(@ModelAttribute("wagon") WagonDTO wagon, @RequestParam("cityId") Integer cityId) {
        CityDTO city = new CityDTO();
        // cityId + 1 cuz in select count start from 0, but city ids from 1
        city.setId(cityId + 1);
        wagon.setCurrent_city(city);
        try {
            wagonService.addWagon(wagon);
        } catch (CTCExecption e) {
            log.error(e.getMessage(), e);
        }
        return "redirect:/employee/wagon/list";
    }

    @PostMapping("/delete={id}")
    @Transactional
    public String deleteWagon(@PathVariable("id") Integer id) {
        wagonService.deleteWagon(wagonService.findWagonById(id));
        return "redirect:/employee/wagon/list";
    }

    @GetMapping("/update={wagonId}")
    public String updateWagonPage(@PathVariable("wagonId") Integer wagonId, Model model) {
        Wagon wagon = wagonService.findWagonById(wagonId);
        WagonDTO dto = new WagonDTO();
        CityDTO city = new CityDTO();
        city.setId(wagon.getCurrent_city().getId());
        dto.setId(wagon.getId());
        dto.setCar_plate(wagon.getCar_plate());
        dto.setCapacity(wagon.getCapacity());
        dto.setDriver_shift_count(wagon.getDriver_shift_count());
        dto.setState(wagon.getState().toString());
        dto.setCurrent_city(city);
        model.addAttribute("wagon", dto);
        model.addAttribute("selectedId", ConverterUtil.parseJson(dto.getCurrent_city().getId()));
        return "views/emp/empEditWagon";
    }

    @PostMapping("/update={wagonId}")
    public String updateWagon(@ModelAttribute("wagon") WagonDTO wagon/*, @RequestParam("cityId") Integer cityId*/) {
        CityDTO city = new CityDTO();
        // cityId + 1 cuz in select count start from 0, but city ids from 1
//        city.setId(cityId + 1);
        wagon.setCurrent_city(city);
        try {
            wagonService.updateWagon(wagon);
        } catch (CTCExecption e) {
            log.error(e.getMessage(), e);
        }
        return "redirect:/employee/wagon/list";
    }
}