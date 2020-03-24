package com.tsystems.service.api;

import com.tsystems.entity.Cargo;

import java.util.List;

public interface CargoService {
    /**
     *
     */
    void addCargo(Cargo cargo);

    /**
     *
     * @param id
     * @return
     */
    Cargo findCargoById(Integer id);

    /**
     *
     * @param cargo
     */
    void updateCargo(Cargo cargo);

    /**
     *
     * @param cargo
     */
    void deleteCargo(Cargo cargo);

    /**
     *
     * @return
     */
    List<Cargo> getAllCargoes();
}
