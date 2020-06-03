package com.tsystems.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.controller.validation.Validator;
import com.tsystems.dao.api.CityDAO;
import com.tsystems.dao.api.OrderDAO;
import com.tsystems.dao.api.WagonDAO;
import com.tsystems.dto.WagonDTO;
import com.tsystems.dto.enums.OrderStatus;
import com.tsystems.dto.enums.WagonStatus;
import com.tsystems.entity.City;
import com.tsystems.entity.Order;
import com.tsystems.entity.Wagon;
import com.tsystems.exception.CTCExecption;
import com.tsystems.jms.MessageSender;
import com.tsystems.service.api.ScoreBoardSender;
import com.tsystems.service.api.WagonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class WagonServiceImpl implements WagonService {
    private WagonDAO wagonDAO;
    private CityDAO cityDAO;
    private OrderDAO orderDAO;

    private final static Logger log = Logger.getLogger(WagonServiceImpl.class);
    private final static ObjectMapper objectMapper = new ObjectMapper();

    private ScoreBoardSender scoreBoardSender;
    private MessageSender messageSender;

    @Autowired
    public WagonServiceImpl(WagonDAO wagonDAO, CityDAO cityDAO, OrderDAO orderDAO, ScoreBoardSender scoreBoardSender, MessageSender messageSender) {
        this.wagonDAO = wagonDAO;
        this.cityDAO = cityDAO;
        this.orderDAO = orderDAO;
        this.scoreBoardSender = scoreBoardSender;
        this.messageSender = messageSender;
    }

    @Override
    @Transactional
    public void addWagon(WagonDTO dto) throws CTCExecption {
        log.info("Adding new wagon" + dto.toString());
        if (!Validator.isPlateValid(dto.getCar_plate())) {
            throw new CTCExecption("Wrong car plate");
        }
        if (wagonDAO.getWagonByCarPlate(dto.getCar_plate()) != null) {
            if (dto.getCar_plate().equals(wagonDAO.getWagonByCarPlate(dto.getCar_plate()).getCar_plate())) {
                throw new CTCExecption("Wagon already exists");
            }
        }
        City city = cityDAO.findById(dto.getCurrent_city().getId());
        Wagon wagon = new Wagon();
        wagon.setCar_plate(dto.getCar_plate());
        wagon.setCapacity(dto.getCapacity());
        wagon.setDriver_shift_count(dto.getDriver_shift_count());
        wagon.setState(WagonStatus.valueOf(dto.getState()));
        wagon.setCurrent_city(city);
        try {
            wagonDAO.add(wagon);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        try {
            messageSender.sendMessage(objectMapper.writeValueAsString(scoreBoardSender.sendStatistic()));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Wagon findWagonById(Integer id) {
        return wagonDAO.findById(id);
    }

    /**
     * @param dto
     */
    @Override
    @Transactional
    public void updateWagon(WagonDTO dto) throws CTCExecption {
        log.info("Updating wagon#" + dto.getId());
        if (!Validator.isPlateValid(dto.getCar_plate())) {
            throw new CTCExecption("Wrong car plate");
        }
        if (wagonDAO.getWagonByCarPlate(dto.getCar_plate()) != null) {
            if (dto.getId() != wagonDAO.getWagonByCarPlate(dto.getCar_plate()).getId()) {
                throw new CTCExecption("Wagon already exists");
            }
        }
//        City city = cityDAO.findById(dto.getCurrent_city().getId());
        Wagon wagon = wagonDAO.findById(dto.getId());
        wagon.setCar_plate(dto.getCar_plate());
        wagon.setDriver_shift_count(dto.getDriver_shift_count());
        wagon.setCapacity(dto.getCapacity());
//        wagon.setCurrent_city(city);
        wagon.setState(WagonStatus.valueOf(dto.getState()));
        try {
            wagonDAO.update(wagon);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        try {
            log.info("Sending message to mq");
            messageSender.sendMessage(objectMapper.writeValueAsString(scoreBoardSender.sendStatistic()));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * @param wagon
     */
    @Override
    @Transactional
    public void deleteWagon(Wagon wagon) {
        log.info("Deleting wagon...");
        wagon.setState(WagonStatus.DISABLE);
        try {
            wagonDAO.update(wagon);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        try {
            messageSender.sendMessage(objectMapper.writeValueAsString(scoreBoardSender.sendStatistic()));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * @return
     */
    @Override
    @Transactional
    public List<Wagon> getAllWagons() {
        List<Wagon> set = wagonDAO.getAll();
        List<Wagon> resultSet = new ArrayList<>();
        set.forEach(wagon -> {
            if(wagon.getState().equals(WagonStatus.ENABLE)) {
                resultSet.add(wagon);
            }
        });
        return resultSet;
    }

    @Override
    @Transactional
    public List<Wagon> getAllDisableWagons() {
        List<Wagon> set = wagonDAO.getAllDisabledWagons();
        List<Wagon> resultSet = new ArrayList<>();
        set.forEach(wagon -> {
            if (wagon.getState().equals(WagonStatus.DISABLE)) {
                resultSet.add(wagon);
            }
        });
        return resultSet;
    }

    @Override
    @Transactional
    public List<Wagon> getAllBusyWagons() {
        List<Wagon> resultSet = new ArrayList<>();
        List<Wagon> wagons = wagonDAO.getAll();
        List<Order> orders = orderDAO.getAll();
        orders.forEach(order -> {
            if (wagons.contains(order.getWagon()) && order.getStatus().toString().equals("CREATED")) {
                if(order.getWagon().getState().toString().equals("ENABLE")) resultSet.add(order.getWagon());
            }
        });
        return resultSet;
    }
}
