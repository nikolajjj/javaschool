package com.tsystems.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.Util.HashPasswordUtil;
import com.tsystems.dao.api.*;
import com.tsystems.dto.CargoDTO;
import com.tsystems.dto.DriverDTO;
import com.tsystems.dto.enums.DriverStatus;
import com.tsystems.dto.enums.Role;
import com.tsystems.dto.enums.WagonStatus;
import com.tsystems.entity.City;
import com.tsystems.entity.Driver;
import com.tsystems.entity.User;
import com.tsystems.entity.Wagon;
import com.tsystems.exception.CTCExecption;
import com.tsystems.jms.MessageSender;
import com.tsystems.service.api.DriverService;
import com.tsystems.service.api.ScoreBoardSender;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    private DriverDAO driverDAO;
    private OrderDAO orderDAO;
    private WagonDAO wagonDAO;
    private CityDAO cityDAO;
    private UserDAO userDAO;
    private CargoDAO cargoDAO;

    private final static Logger log = Logger.getLogger(DriverServiceImpl.class);
    private final static ObjectMapper objectMapper = new ObjectMapper();

    private MessageSender messageSender;
    private ScoreBoardSender scoreBoardSender;

    @Autowired
    public DriverServiceImpl(DriverDAO driverDAO, OrderDAO orderDAO, WagonDAO wagonDAO, CityDAO cityDAO,
                             UserDAO userDAO, CargoDAO cargoDAO, MessageSender messageSender,
                             ScoreBoardSender scoreBoardSender) {
        this.driverDAO = driverDAO;
        this.orderDAO = orderDAO;
        this.wagonDAO = wagonDAO;
        this.cityDAO = cityDAO;
        this.userDAO = userDAO;
        this.cargoDAO = cargoDAO;
        this.messageSender = messageSender;
        this.scoreBoardSender = scoreBoardSender;
    }

    @Override
    @Transactional
    public void addDriver(DriverDTO dto) {
        log.info("Adding new driver...");
        City city = cityDAO.findById(dto.getCurrent_city().getId());

        Driver driver = new Driver();
        driver.setFirst_name(dto.getFirst_name());
        driver.setSecond_name(dto.getSecond_name());
        driver.setCurrent_city(city);
        driver.setStatus(DriverStatus.REST);

        try {
            driverDAO.add(driver);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        User user = new User();
        user.setRole(Role.DRIVER);
        user.setUsername(driver.getPersonal_number());
        user.setPassword(HashPasswordUtil.getHash(driver.getPersonal_number()));

        driver.setUser_id(user);

        try {
            driverDAO.update(driver);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        try {
            messageSender.sendMessage(objectMapper.writeValueAsString(scoreBoardSender.sendStatistic()));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public Driver findDriverById(Integer Id) {
        return driverDAO.findById(Id);
    }

    @Override
    public Driver findDriverByUserId(Integer id) {
        List<Driver> driverList = driverDAO.getAll();
        for(Driver driver : driverList) {
            if (driver.getUser_id() != null) {
                if (driver.getUser_id().getId() == id) {
                    return driver;
                }
            }
        }
        return null;
    }


    @Override
    @Transactional
    public List<Driver> findCoDriverByWagonId(Driver driver) throws CTCExecption {
        List<Driver> driverList = driverDAO.getAll();
        List<Driver> coDrivers = new ArrayList<>();
        for(Driver temp : driverList) {
            if (driver.getCurrent_wagon() != null) {
                if (temp.getCurrent_wagon() != null) {
                    if ((temp.getCurrent_wagon().getId() == driver.getCurrent_wagon().getId()) && (driver.getId() != temp.getId())) {
                        coDrivers.add(temp);
                    }
                }
            } else {
                throw new CTCExecption("No codrivers found");
            }

        }
        return coDrivers;
    }

    @Override
    @Transactional
    public void updateDriver(DriverDTO dto) {
        log.info("Updating driver #" + dto.getId());
        Driver driver = driverDAO.findById(dto.getId());
        User user = userDAO.findByUsername(driver.getFirst_name());

        driver.setFirst_name(dto.getFirst_name());
        driver.setSecond_name(dto.getSecond_name());
        try {
            driver.setStatus(DriverStatus.valueOf(dto.getStatus()));
        } catch (Exception e) {
            log.info("Can not get driver status to update it");
        }
        try {
            List<Driver> coDrivers = driverDAO.getCoDrivers(driver.getId(), driver.getCurrent_wagon().getId());
            if (coDrivers.contains(driver)) {
                coDrivers.remove(driver);
            }
            if (dto.getStatus().equals("DRIVING")) {
                coDrivers.forEach(coDriver -> {
                    coDriver.setStatus(DriverStatus.SECOND_DRIVER);
                    driverDAO.update(coDriver);
                });
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        try {
            driverDAO.update(driver);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        try {
            messageSender.sendMessage(objectMapper.writeValueAsString(scoreBoardSender.sendStatistic()));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }


    @Override
    @Transactional
    public void updateDriver(DriverDTO dto, CargoDTO cargo) {
        Driver driver = driverDAO.findById(dto.getId());
        driverDAO.update(driver);
    }

    @Override
    @Transactional
    public void deleteDriver(Driver driver) {
        log.info("Deleting driver...");
        driver.setStatus(DriverStatus.DISABLED);
        try {
            driverDAO.update(driver);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        try {
            messageSender.sendMessage(objectMapper.writeValueAsString(scoreBoardSender.sendStatistic()));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public List<Driver> getAllDrivers() {
        List<Driver> set = driverDAO.getAll();
        List<Driver> resultSet = new ArrayList<>();
        set.forEach(driver -> {
            if(!driver.getStatus().equals(DriverStatus.DISABLED)) {
                resultSet.add(driver);
            }
        });
        return resultSet;
    }

    @Override
    @Transactional
    public List<Driver> getAllDriversWithoutWagon() {
        List<Driver> driverList = driverDAO.getAll();
        List<Driver> driversWithoutWagon = new ArrayList<>();
        for(Driver driver : driverList) {
            if (driver.getCurrent_wagon() == null && (!driver.getStatus().equals(DriverStatus.DISABLED))) {
                driversWithoutWagon.add(driver);
            }
        }
        return driversWithoutWagon;
    }
}
