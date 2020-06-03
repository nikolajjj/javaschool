package com.tsystems.controller.employee;

import com.tsystems.Util.ConverterUtil;
import com.tsystems.dto.CargoDTO;
import com.tsystems.dto.DriverDTO;
import com.tsystems.dto.MyOrderDTO;
import com.tsystems.dto.OrderDTO;
import com.tsystems.entity.Cargo;
import com.tsystems.entity.Driver;
import com.tsystems.entity.Order;
import com.tsystems.entity.Wagon;
import com.tsystems.dto.enums.OrderStatus;
import com.tsystems.service.api.CargoService;
import com.tsystems.service.api.DriverService;
import com.tsystems.service.api.OrderService;
import com.tsystems.service.api.WagonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class OrderController {
    private OrderService orderService;
    private static final Logger log = Logger.getLogger(OrderController.class);

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public String indexOrderPage(Model model) {
        model.addAttribute("orderList", orderService.getAllOrders());
        return "/views/emp/empOrder";
    }

    @GetMapping("/order/add")
    public String addOrderPage(Model model) {
        return "/views/emp/empAddOrder";
    }

    @PostMapping(value = "/order/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addNewOrder(@RequestBody String json) {
        try {
            orderService.addOrder(ConverterUtil.parseCargoDTOLIst(json));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "redirect:/employee/order";
    }
}
