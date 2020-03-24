package com.tsystems.controller;

import com.tsystems.Util.ConverterUtil;
import com.tsystems.entity.Cargo;
import com.tsystems.entity.Order;
import com.tsystems.entity.Wagon;
import com.tsystems.entity.enums.OrderStatus;
import com.tsystems.service.api.CargoService;
import com.tsystems.service.api.OrderService;
import com.tsystems.service.api.WagonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class OrderController {
    private OrderService orderService;
    private WagonService wagonService;
    private CargoService cargoService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setWagonService(WagonService wagonService) {
        this.wagonService = wagonService;
    }

    @Autowired
    public void setCargoService(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping("/order")
    public ModelAndView indexOrderPage() {
        ModelAndView mv = new ModelAndView();
        List<Order> orderList = orderService.getAllOrders();
        mv.addObject("orderList", orderList);
        mv.setViewName("views/employeeOrder");
        return mv;
    }

    @GetMapping("/order/add")
    public ModelAndView addOrderPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/employeeOrderAdd");
        Order order = new Order();
        List<Wagon> wagonList = wagonService.getAllWagons();
        List<Cargo> cargoList = cargoService.getAllCargoes();
        mv.addObject("order", order);
        mv.addObject("wagonList", wagonList);
        mv.addObject("cargoList", cargoList);
        return mv;
    }

    /*@RequestMapping(name = "/order/add", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<Cargo>> getAllCargoJSON() {
        List<Cargo> cargoList = cargoService.getAllCargoes();
        return new ResponseEntity<List<Cargo>>(cargoList, HttpStatus.OK);
    }*/



    @PostMapping("/order/add")
    public ModelAndView addNewOrder(@ModelAttribute("order") Order order, @RequestParam("wagon_id") Integer wagonId, @RequestParam("cargo_id") Integer cargoId) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/employee/order");
        Wagon wagon = wagonService.findWagonById(wagonId);
        order.setWagon(wagon);
        order.setStatus(OrderStatus.CREATED);
        orderService.addOrder(order);
        Cargo cargo = cargoService.findCargoById(cargoId);
        cargo.setOrder(order);
        cargoService.updateCargo(cargo);
        return mv;
    }

    @GetMapping("/order/update")
    public ModelAndView updateOrder() {
        ModelAndView mv = new ModelAndView();
        return mv;
    }

    @PostMapping("/order/update")
    public ModelAndView updateOrder(@PathVariable("orderId") Integer orderId) {
        ModelAndView mv = new ModelAndView();
        return mv;
    }

    @PostMapping("/order/delete={id}")
    public String deleteOrder(@PathVariable("id") Integer orderId) {
        orderService.deleteOrder(orderService.findOrderById(orderId));
        return "redirect:/employee/order";
    }
}
