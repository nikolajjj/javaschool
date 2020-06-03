package com.tsystems.dao.api;

import com.tsystems.entity.DriverShift;

import java.util.List;
/**
 * CRUD and specific operations of DriverShift entity
 */
public interface DriverShiftDAO extends GenericDAO<DriverShift, Integer> {
    /**
     *
     * @param id
     * @return
     */
    DriverShift getLastDriverShiftByDriverId(Integer id);

    /**
     *
     * @param id
     * @return
     */
    List<DriverShift> getLastDriverShiftsByDriverId(Integer id);
}