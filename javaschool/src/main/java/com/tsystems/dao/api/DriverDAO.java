package com.tsystems.dao.api;

import com.tsystems.entity.Driver;

import java.util.List;

/**
 * CRUD and specific operations of Driver entity
 */
public interface DriverDAO extends GenericDAO<Driver, Integer> {
    /**
     *
     * @param driverId - driver of current id
     * @param wagonId
     * @return
     */
    List<Driver> getCoDrivers(Integer driverId, Integer wagonId);

    /**
     *
     * @param wagonId
     * @return
     */
    List<Driver> getDriversByWagonId(Integer wagonId);

    List<Driver> getFreeDrivers();
}
