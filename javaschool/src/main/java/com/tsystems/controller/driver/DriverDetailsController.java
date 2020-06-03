package com.tsystems.controller.driver;

import com.tsystems.dto.*;
import com.tsystems.dto.enums.CargoStatus;
import com.tsystems.dto.enums.DriverStatus;
import com.tsystems.entity.*;
import com.tsystems.exception.CTCExecption;
import com.tsystems.service.api.*;
import com.tsystems.service.implementation.UserDetails;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/driver")
public class DriverDetailsController {
    private DriverService driverService;
    private WagonService wagonService;
    private OrderService orderService;
    private CargoService cargoService;
    private DriverShiftService driverShiftService;
    private CityService cityService;

    private final static Logger log = Logger.getLogger(DriverDetailsController.class);

    @Autowired
    public DriverDetailsController(DriverService driverService, WagonService wagonService, OrderService orderService, CargoService cargoService, DriverShiftService driverShiftService, CityService cityService) {
        this.driverService = driverService;
        this.wagonService = wagonService;
        this.orderService = orderService;
        this.cargoService = cargoService;
        this.driverShiftService = driverShiftService;
        this.cityService = cityService;
    }

    @GetMapping("/")
    public String driverPage() {
        return "views/driver/drivers";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Driver driver = driverService.findDriverByUserId(user.getId());
        model.addAttribute("driver", driver);
        model.addAttribute("user", user);
        try {
            List<Driver> coDrivers = driverService.findCoDriverByWagonId(driver);
            model.addAttribute("coDrivers", coDrivers);
        } catch (CTCExecption e) {
            log.error(e.getMessage(), e);
        }
        if (driver.getCurrent_wagon() != null) {
            Order order = orderService.findOrderByWagonId(driver.getCurrent_wagon().getId());
            try {
                List<Driver> coDrivers = driverService.findCoDriverByWagonId(driver);
                model.addAttribute("drivers", coDrivers);
            } catch (CTCExecption e) {
                log.info("No co drivers were found");
            }
            List<Cargo> cargoList = cargoService.findCargoByOrderId(order.getId());
            model.addAttribute("wagon", wagonService.findWagonById(driver.getCurrent_wagon().getId()));
            model.addAttribute("order", order);
            model.addAttribute("cargoList", cargoList);
        }
        return "/views/driver/driverProfile";
    }

    @GetMapping("/order")
    public String order(Model model) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Driver driver = driverService.findDriverByUserId(user.getId());
        DriverShift lastDriverShift = driverShiftService.getLastDriverShift(driver.getId());
        if (driver.getCurrent_wagon() != null) {
            Order order = orderService.findOrderByWagonId(driver.getCurrent_wagon().getId()); // get last order with this wagon id
            try {
                List<Driver> coDrivers = driverService.findCoDriverByWagonId(driver);
            } catch (CTCExecption e) {
                log.info("No co drivers found!");
            }
            List<Cargo> cargoList = cargoService.findCargoByOrderId(order.getId());
            model.addAttribute("driverShift", lastDriverShift);
            model.addAttribute("driver", driver);
            model.addAttribute("cargoList", cargoList);
            model.addAttribute("order", order);
        }
        return "/views/driver/driverUpdateOrder";
    }

    @PostMapping("/order/beginDriverShift")
    public String beginDriverShift() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Driver driver = driverService.findDriverByUserId(user.getId());

        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(driver.getCurrent_city().getId());

        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(driver.getId());
        driverDTO.setFirst_name(driver.getFirst_name());
        driverDTO.setSecond_name(driver.getSecond_name());
        driverDTO.setPersonal_number(driver.getStatus().toString());
        driverDTO.setCurrent_city(cityDTO);
        driverDTO.setStatus(driver.getStatus().toString());

        DriverShiftDTO driverShift = new DriverShiftDTO();
        driverShift.setBegin(new Date());
        driverShift.setDriverDTO(driverDTO);

        driverService.updateDriver(driverDTO);
        driverShiftService.addDriverShift(driverShift);
        return "redirect:/driver/order";
    }

    @PostMapping("/order/endDriverShift")
    public String endDriverShift() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Driver driver = driverService.findDriverByUserId(user.getId());

        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(driver.getCurrent_city().getId());

        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(driver.getId());
        driverDTO.setFirst_name(driver.getFirst_name());
        driverDTO.setSecond_name(driver.getSecond_name());
        driverDTO.setPersonal_number(driver.getStatus().toString());
        driverDTO.setCurrent_city(cityDTO);

        DriverShift driverShift = driverShiftService.getLastDriverShift(driver.getId());
        DriverShiftDTO driverShiftDTO = new DriverShiftDTO();
        driverShiftDTO.setId(driverShift.getId());
        driverShiftDTO.setBegin(driverShift.getBegin());
        driverShiftDTO.setEnd(new Date());
        driverShiftDTO.setDriverDTO(driverDTO);

        driverService.updateDriver(driverDTO);
        driverShiftService.updateDriverShift(driverShiftDTO);
        return "redirect:/driver/order";
    }

    @PostMapping("/order/changeDriverStatus")
    public String changeDriverStatus(@RequestParam("driver_status") String status, @RequestParam("driver_id") Integer driverId) {
        Driver driver = driverService.findDriverById(driverId);

        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(driver.getCurrent_city().getId());

        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(driver.getId());
        driverDTO.setFirst_name(driver.getFirst_name());
        driverDTO.setSecond_name(driver.getSecond_name());
        driverDTO.setPersonal_number(driver.getStatus().toString());
        driverDTO.setCurrent_city(cityDTO);
        driverDTO.setStatus(status);

        driverService.updateDriver(driverDTO);
        return "redirect:/driver/order";
    }

    @PostMapping("/order/changeCargoStatus")
    public String changeCargoStatus(@RequestParam("cargo_status") String status, @RequestParam("cargo_id") Integer cargoId) {
        UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Driver driver = driverService.findDriverByUserId(user.getId());

        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(driver.getCurrent_city().getId());

        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(driver.getId());
        driverDTO.setFirst_name(driver.getFirst_name());
        driverDTO.setSecond_name(driver.getSecond_name());
        driverDTO.setPersonal_number(driver.getStatus().toString());
        driverDTO.setCurrent_city(cityDTO);
        driverDTO.setStatus(driver.getStatus().toString());
        driverDTO.setCurrent_wagon(new WagonDTO(driver.getCurrent_wagon().getId()));

        Cargo cargo = cargoService.findCargoById(cargoId);
        CargoDTO cargoDTO = new CargoDTO();
        cargoDTO.setId(cargo.getId());
        cargoDTO.setDescription(cargo.getDescription());
        cargoDTO.setWeight(cargo.getWeight());
        cargoDTO.setCargoStatus(status);
        cargoDTO.setCity_from(new CityDTO(cargo.getCity_from().getId()));
        cargoDTO.setCity_to(new CityDTO(cargo.getCity_to().getId()));

        driverService.updateDriver(driverDTO);
        cargoService.updateCargo(cargoDTO, driverDTO);
        return "redirect:/driver/order";
    }
}
