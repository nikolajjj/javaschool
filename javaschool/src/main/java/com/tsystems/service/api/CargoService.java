package com.tsystems.service.api;

import com.tsystems.dto.CargoDTO;
import com.tsystems.dto.DriverDTO;
import com.tsystems.entity.Cargo;
import com.tsystems.exception.CTCExecption;

import java.util.List;

public interface CargoService {
    /**
     *
     */
    void addCargo(CargoDTO cargo) throws CTCExecption;

    /**
     *
     * @param id
     * @return
     */
    Cargo findCargoById(Integer id);

    /**
     *
     * @param id - Order id
     * @return
     */
    List<Cargo> findCargoByOrderId(Integer id);

    /**
     *
     * @param cargo
     */
    void updateCargo(CargoDTO cargo, DriverDTO driver);
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
