package com.tsystems.service.implementation;

import com.tsystems.dao.api.CargoDAO;
import com.tsystems.entity.Cargo;
import com.tsystems.service.api.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CargoServiceImpl implements CargoService {
    private CargoDAO cargoDAO;

    @Autowired
    @Transactional
    public void setCargoDAO(CargoDAO cargoDAO) {
        this.cargoDAO = cargoDAO;
    }

    @Override
    @Transactional
    public void addCargo(Cargo cargo) {
        cargoDAO.add(cargo);
    }

    @Override
    @Transactional
    public Cargo findCargoById(Integer id) {
        return cargoDAO.findById(id);
    }

    @Override
    @Transactional
    public void updateCargo(Cargo cargo) {
        cargoDAO.update(cargo);
    }

    @Override
    @Transactional
    public void deleteCargo(Cargo cargo) {
        cargoDAO.delete(cargo);
    }

    @Override
    @Transactional
    public List<Cargo> getAllCargoes() {
        return cargoDAO.getAll();
    }
}
