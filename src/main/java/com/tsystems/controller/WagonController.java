package com.tsystems.controller;

import com.tsystems.entity.City;
import com.tsystems.entity.Wagon;
import com.tsystems.entity.enums.WagonStatus;
import com.tsystems.service.api.CityService;
import com.tsystems.service.api.WagonService;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/employee/wagon")
public class WagonController {
    private WagonService wagonService;
    private CityService cityService;

    @Autowired
    public void setWagonService(WagonService wagonService) {
        this.wagonService = wagonService;
    }

    @Autowired
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @Autowired
    /**
     *
     * @param model
     * @return
     */
    @GetMapping("/add")
    public ModelAndView addNewWagon() {
        return new ModelAndView("views/wagonadd", "wagon", new Wagon());
    }

    /**
     *
     * @param wagon
     * @param model
     * @return
     */
    @PostMapping("/add")
    public String addNewWagon(@ModelAttribute("wagon") Wagon wagon) {
        wagonService.addWagon(wagon);
        return "redirect:/";
    }

    /**
     *
     * @return
     */
    @GetMapping("/list")
    public ModelAndView wagonList() {
        ModelAndView mv = new ModelAndView();
        List<Wagon> wagonList = wagonService.getAllWagons();
        mv.setViewName("views/employeesWagon");
        mv.addObject("wagonList", wagonList);
        return mv;
    }

    @PostMapping("/delete={id}")
    public String deleteWagon(@PathVariable("id") Integer id) {
        wagonService.deleteWagon(wagonService.findWagonById(id));
        return "redirect:/employee/wagon/list";
    }

    @GetMapping("/update={wagonId}")
    public ModelAndView updatePage(@PathVariable("wagonId") Integer wagonId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/employeesEditWagon");
        Wagon wagon = wagonService.findWagonById(wagonId);
        List<City> cityList = cityService.getAllCities();
        System.out.println(wagon.toString());
        mv.addObject("wagon", wagon);
        mv.addObject("cityList", cityList);
        return mv;
    }

    @PostMapping("/update={wagonId}")
    public String updateWagon(@ModelAttribute("wagon") Wagon wagon, @RequestParam("city_id") Integer id) {
        City city = cityService.findCityById(id);
        wagon.setCurrent_city(city);
        System.out.println(wagon.toString());
        wagonService.updateWagon(wagon);
        return "redirect:/employee/wagon/list";
    }
}