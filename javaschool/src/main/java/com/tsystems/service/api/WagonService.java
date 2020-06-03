package com.tsystems.service.api;

import com.tsystems.dto.WagonDTO;
import com.tsystems.entity.Wagon;
import com.tsystems.exception.CTCExecption;

import java.util.List;

/**
 *
 */
public interface WagonService {
    /**
     *
     * @param wagon
     */
    void addWagon(WagonDTO wagon) throws CTCExecption;

    /**
     *
     * @param id
     * @return
     */
    Wagon findWagonById(Integer id);

    /**
     *
     * @param wagon
     */
    void updateWagon(WagonDTO wagon) throws CTCExecption;

    /**
     *
     * @param wagon
     */
    void deleteWagon(Wagon wagon);

    /**
     *
     * @return
     */
    List<Wagon> getAllWagons();

    List<Wagon> getAllDisableWagons();

    List<Wagon> getAllBusyWagons();
}
