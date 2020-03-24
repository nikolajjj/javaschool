package com.tsystems.service.implementation;

import com.tsystems.dao.api.CityDAO;
import com.tsystems.entity.City;
import com.tsystems.service.api.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private CityDAO cityDAO;

    @Autowired
    public void setCityDAO(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    @Transactional
    public void addCity(City city) {
        cityDAO.add(city);
    }

    @Transactional
    public City findCityById(Integer id) {
        return cityDAO.findById(id);
    }

    @Transactional
    public void updateCity(City city) {
        cityDAO.update(city);
    }

    @Transactional
    public void deleteCity(City city) {
        cityDAO.delete(city);
    }

    @Transactional
    public List<City> getAllCities() {
        return cityDAO.getAll();
    }
}
