package com.tsystems.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.dao.api.CargoDAO;
import com.tsystems.dao.api.DriverDAO;
import com.tsystems.dao.api.OrderDAO;
import com.tsystems.dao.api.WagonDAO;
import com.tsystems.dto.OrderDTO;
import com.tsystems.dto.enums.OrderStatus;
import com.tsystems.entity.Cargo;
import com.tsystems.entity.Converter.Converter;
import com.tsystems.entity.Driver;
import com.tsystems.entity.Order;
import com.tsystems.exception.CTCExecption;
import com.tsystems.jms.MessageSender;
import com.tsystems.service.api.OrderService;
import com.tsystems.service.api.ScoreBoardSender;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;
    private WagonDAO wagonDAO;
    private CargoDAO cargoDAO;
    private DriverDAO driverDAO;

    private MessageSender messageSender;
    private ScoreBoardSender scoreBoardSender;

    private final static ObjectMapper objectMapper = new ObjectMapper();
    private final static Logger log = Logger.getLogger(OrderServiceImpl.class);

    public OrderServiceImpl(OrderDAO orderDAO, WagonDAO wagonDAO, CargoDAO cargoDAO, DriverDAO driverDAO,
                            MessageSender messageSender, ScoreBoardSender scoreBoardSender) {
        this.orderDAO = orderDAO;
        this.wagonDAO = wagonDAO;
        this.cargoDAO = cargoDAO;
        this.driverDAO = driverDAO;
        this.messageSender = messageSender;
        this.scoreBoardSender = scoreBoardSender;
    }

    @Override
    @Transactional
    public void addOrder(OrderDTO dto) {
        Order order = new Order();
        order.setStatus(OrderStatus.CREATED);
        order.setWagon(wagonDAO.findById(dto.getWagon().getId()));
        try {
            orderDAO.add(order);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        try {
            messageSender.sendMessage(objectMapper.writeValueAsString(scoreBoardSender.sendStatistic()));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        try {
            dto.getCargo().forEach(cargoDto -> {
                Cargo cargo = cargoDAO.findById(cargoDto.getId());
                cargo.setOrder(order);
                cargoDAO.update(cargo);
            });
            dto.getDriver().forEach(driverDto -> {
                Driver driver = driverDAO.findById(driverDto.getId());
                driver.setCurrent_wagon(order.getWagon());
                driverDAO.update(driver);
            });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public Order findOrderById(Integer id) {
        return orderDAO.findById(id);
    }

    /**
     *
     * @param id - wagon id
     * @return
     */
    @Override
    @Transactional
    public Order findOrderByWagonId(Integer id) {
        return orderDAO.getOrderByWagonId(id);
    }

    @Override
    @Transactional
    public void updateOrder(Order order) {
        orderDAO.update(order);
        try {
            messageSender.sendMessage(objectMapper.writeValueAsString(scoreBoardSender.sendStatistic()));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void deleteOrder(Order order) {
        orderDAO.delete(order);
    }

    @Override
    @Transactional
    public List<Order> getAllOrders() {
        return orderDAO.getAll();
    }
}
