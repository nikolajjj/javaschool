package com.tsystems.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.dao.api.*;
import com.tsystems.dto.CargoDTO;
import com.tsystems.dto.DriverDTO;
import com.tsystems.dto.enums.CargoStatus;
import com.tsystems.dto.enums.DriverStatus;
import com.tsystems.dto.enums.OrderStatus;
import com.tsystems.entity.*;
import com.tsystems.exception.CTCExecption;
import com.tsystems.jms.MessageSender;
import com.tsystems.service.api.CargoService;
import com.tsystems.service.api.ScoreBoardSender;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CargoServiceImpl implements CargoService {
    private CargoDAO cargoDAO;
    private OrderDAO orderDAO;
    private DriverDAO driverDAO;
    private WagonDAO wagonDAO;
    private DriverShiftDAO driverShiftDAO;
    private CityDAO cityDAO;


    private final static Logger log = Logger.getLogger(CargoServiceImpl.class);
    private final static ObjectMapper objectMapper = new ObjectMapper();
    private MessageSender messageSender;
    private ScoreBoardSender scoreBoardSender;

    @Autowired
    public CargoServiceImpl(CargoDAO cargoDAO, OrderDAO orderDAO, DriverDAO driverDAO, WagonDAO wagonDAO,
                            DriverShiftDAO driverShiftDAO, CityDAO cityDAO, MessageSender messageSender,
                            ScoreBoardSender scoreBoardSender) {
        this.cargoDAO = cargoDAO;
        this.orderDAO = orderDAO;
        this.driverDAO = driverDAO;
        this.wagonDAO = wagonDAO;
        this.driverShiftDAO = driverShiftDAO;
        this.cityDAO = cityDAO;
        this.messageSender = messageSender;
        this.scoreBoardSender = scoreBoardSender;
    }

    @Override
    @Transactional
    public void addCargo(CargoDTO dto) throws CTCExecption {
        if (dto.getCity_from().getId() == dto.getCity_to().getId()) {
            throw new CTCExecption("City from and city to are the same!");
        }
        Cargo cargo = new Cargo();
        cargo.setDescription(dto.getDescription());
        cargo.setWeight(dto.getWeight());
        cargo.setStatus(CargoStatus.PREPARED);
        cargo.setCity_from(cityDAO.findById(dto.getCity_from().getId()));
        cargo.setCity_to(cityDAO.findById(dto.getCity_to().getId()));
        try {
            cargoDAO.add(cargo);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public Cargo findCargoById(Integer id) {
        return cargoDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Cargo> findCargoByOrderId(Integer id) {
        List<Cargo> cargoList = cargoDAO.getAll();
        List<Cargo> resultList = new ArrayList<>();
        for (Cargo cargo : cargoList) {
            if (cargo.getOrder() != null) {
                if (cargo.getOrder().getId() == id) {
                    resultList.add(cargo);
                }
            }
        }
        return resultList;
    }

    @Override
    @Transactional
    public void updateCargo(CargoDTO cargo, DriverDTO driver) {
        Driver driver1 = driverDAO.findById(driver.getId());
        Cargo cargo1 = cargoDAO.findById(cargo.getId());
        cargo1.setStatus(CargoStatus.valueOf(cargo.getCargoStatus()));
        cargo1.setCity_to(cityDAO.findById(cargo.getCity_to().getId()));
        cargo1.setCity_from(cityDAO.findById(cargo.getCity_from().getId()));
        try {
            List<Driver> coDrivers = driverDAO.getCoDrivers(driver1.getId(), driver1.getCurrent_wagon().getId());
            if (cargo.getCargoStatus().equals("DELIVERED")) {
                driver1.setCurrent_city(cityDAO.findById(cargo1.getCity_to().getId()));
                coDrivers.forEach(temp -> {
                    temp.setCurrent_city(cityDAO.findById(cargo.getCity_to().getId()));
                    driverDAO.update(temp);
                });
                Wagon wagon = wagonDAO.findById(driver1.getCurrent_wagon().getId());
                wagon.setCurrent_city(cityDAO.findById(cargo.getCity_to().getId()));
                wagonDAO.update(wagon);
            } else {
                driver1.setCurrent_city(cityDAO.findById(cargo1.getCity_from().getId()));
                coDrivers.forEach(temp -> {
                    temp.setCurrent_city(cityDAO.findById(cargo.getCity_from().getId()));
                    driverDAO.update(temp);
                });
                Wagon wagon = wagonDAO.findById(driver1.getCurrent_wagon().getId());
                wagon.setCurrent_city(cityDAO.findById(cargo.getCity_from().getId()));
                wagonDAO.update(wagon);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        try {
            List<Cargo> cargoList = cargoDAO.getCargoesByOrderId(cargo1.getOrder().getId());
            List<Driver> driverList = driverDAO.getDriversByWagonId(cargo1.getOrder().getWagon().getId());
            Boolean isCargoesAreAllDelivered = true;
            for (Cargo temp : cargoList) {
                if (temp.getStatus() != CargoStatus.DELIVERED) {
                    isCargoesAreAllDelivered = false;
                }
            }
            if (isCargoesAreAllDelivered) {
                Order order = orderDAO.findById(cargo1.getOrder().getId());
                order.setStatus(OrderStatus.DONE);
                List<DriverShift> driverShiftList = new ArrayList<>();
                for (Driver temp : driverList) {
                    temp.setStatus(DriverStatus.REST);
                    temp.setCurrent_wagon(null);
                    driverShiftList.add(driverShiftDAO.getLastDriverShiftByDriverId(driver.getId()));
                }
                for (DriverShift driverShift : driverShiftList) {
                    driverShift.setEnd(new Date());
                    driverShiftDAO.update(driverShift);
                }
                orderDAO.update(order);
                try {
                    messageSender.sendMessage(objectMapper.writeValueAsString(scoreBoardSender.sendStatistic()));
                } catch (JsonProcessingException e) {
                    log.error(e.getMessage(), e);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        cargoDAO.update(cargo1);
    }

    @Override
    @Transactional
    public void deleteCargo(Cargo cargo) {
        cargoDAO.delete(cargo);
    }

    @Override
    @Transactional
    public List<Cargo> getAllCargoes() {
        return cargoDAO.getAll();
    }
}
