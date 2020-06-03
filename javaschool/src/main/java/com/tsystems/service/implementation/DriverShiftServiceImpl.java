package com.tsystems.service.implementation;

import com.tsystems.dao.api.DriverDAO;
import com.tsystems.dao.api.DriverShiftDAO;
import com.tsystems.dto.DriverShiftDTO;
import com.tsystems.entity.Driver;
import com.tsystems.entity.DriverShift;
import com.tsystems.service.api.DriverShiftService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverShiftServiceImpl implements DriverShiftService {
    private DriverShiftDAO driverShiftDAO;
    private DriverDAO driverDAO;

    private final static Logger log = Logger.getLogger(DriverShiftServiceImpl.class);

    @Autowired
    public DriverShiftServiceImpl(DriverShiftDAO driverShiftDAO, DriverDAO driverDAO) {
        this.driverShiftDAO = driverShiftDAO;
        this.driverDAO = driverDAO;
    }

    @Override
    @Transactional
    public void addDriverShift(DriverShiftDTO dto) {
        DriverShift driverShift = new DriverShift();
        driverShift.setBegin(dto.getBegin());
        driverShift.setEnd(dto.getEnd());
        driverShift.setDriver(driverDAO.findById(dto.getDriverDTO().getId()));
        try {
            driverShiftDAO.add(driverShift);
        } catch (Exception e) {
            log.info("Driver shift no created!");
            log.error(e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public List<DriverShift> findDriverShiftsByDriver(Driver driver) {
        List<DriverShift> driverShiftList = driverShiftDAO.getAll();
        List<DriverShift> assignedDriverShifts = new ArrayList<>();
        for(DriverShift driverShift : driverShiftList) {
            if (driverShift.getDriver().getId() == driver.getId()) {
                assignedDriverShifts.add(driverShift);
            }
        }
        return assignedDriverShifts;
    }

    @Override
    @Transactional
    public DriverShift getLastDriverShift(Integer driverId) {
        return driverShiftDAO.getLastDriverShiftByDriverId(driverId);
    }

    @Override
    @Transactional
    public void updateDriverShift(DriverShiftDTO dto) {
        Driver driver = driverDAO.findById(dto.getDriverDTO().getId());
        DriverShift driverShift = driverShiftDAO.findById(dto.getId());
        driverShift.setBegin(dto.getBegin());
        driverShift.setEnd(dto.getEnd());
        driverShift.setDriver(driver);
        driverShiftDAO.update(driverShift);
    }

    @Override
    @Transactional
    public void deleteDriverShift(DriverShift driverShift) {
        driverShiftDAO.delete(driverShift);
    }

    @Override
    @Transactional
    public List<DriverShift> getAllDriversShifts() {
        return driverShiftDAO.getAll();
    }
}
