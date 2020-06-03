package com.tsystems.dao.api;

import com.tsystems.entity.Wagon;

import java.util.List;

/**
 *
 */

public interface WagonDAO extends GenericDAO<Wagon, Integer> {
    List<Wagon> getAllDisabledWagons();
    Wagon getWagonByCarPlate(String carplate);
}
