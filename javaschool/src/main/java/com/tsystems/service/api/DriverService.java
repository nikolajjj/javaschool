package com.tsystems.service.api;

import com.tsystems.dto.CargoDTO;
import com.tsystems.dto.DriverDTO;
import com.tsystems.entity.Driver;
import com.tsystems.exception.CTCExecption;

import java.util.List;

/**
 *
 */
public interface DriverService {
    /**
     *
     */
    void addDriver(DriverDTO driver);

    /**
     * @param id - Driver id
     * @return
     */
    Driver findDriverById(Integer id);

    /**
     * @param id - User id
     * @return
     */
    Driver findDriverByUserId(Integer id);

    /**
     * @param id - Wagon Id
     * @return
     */
    List<Driver> findCoDriverByWagonId(Driver driver) throws CTCExecption;

    /**
     * @param driver
     */
    void updateDriver (DriverDTO driver);

    void updateDriver(DriverDTO driver, CargoDTO cargo);
    /**
     * @param driver
     */
    void deleteDriver(Driver driver);

    /**
     * @return
     */
    List<Driver> getAllDrivers();

    /**
     * @return
     */
    List<Driver> getAllDriversWithoutWagon();
}
