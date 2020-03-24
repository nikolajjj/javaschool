package com.tsystems.service.api;

import com.tsystems.entity.Driver;

import java.util.List;

/**
 *
 */
public interface DriverService {
    /**
     *
     */
    void addDriver(Driver driver);

    /**
     *
     * @param id
     * @return
     */
    Driver findDriverById(Integer id);

    /**
     *
     * @param driver
     */
    void updateDriver(Driver driver);

    /**
     *
     * @param driver
     */
    void deleteDriver(Driver driver);

    /**
     *
     * @return
     */
    List<Driver> getAllDrivers();
}
