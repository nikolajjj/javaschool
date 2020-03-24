package com.tsystems.service.api;

import com.tsystems.entity.City;
import com.tsystems.entity.Driver;

import java.util.List;

public interface CityService {
    /**
     *
     */
    void addCity(City city);

    /**
     *
     * @param id
     * @return
     */
    City findCityById(Integer id);

    /**
     *
     * @param city
     */
    void updateCity(City city);

    /**
     *
     * @param city
     */
    void deleteCity(City city);

    /**
     *
     * @return
     */
    List<City> getAllCities();
}
