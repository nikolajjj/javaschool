package com.tsystems.service.implementation;

import com.tsystems.dao.api.DriverDAO;
import com.tsystems.entity.Driver;
import com.tsystems.service.api.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    private DriverDAO driverDAO;

    public DriverServiceImpl(DriverDAO driverDAO) {
        this.driverDAO = driverDAO;
    }

    @Autowired
    public void setDriverDAO(DriverDAO driverDAO) {
        this.driverDAO = driverDAO;
    }

    @Override
    @Transactional
    public void addDriver(Driver driver) {
        driverDAO.add(driver);
    }

    @Override
    public Driver findDriverById(Integer Id) {
        return driverDAO.findById(Id);
    }

    @Override
    public void updateDriver(Driver driver) {
        driverDAO.update(driver);
    }

    @Override
    public void deleteDriver(Driver driver) {
        driverDAO.delete(driver);
    }

    @Override
    public List<Driver> getAllDrivers() {
        return driverDAO.getAll();
    }
}
