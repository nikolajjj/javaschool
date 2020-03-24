package com.tsystems.service.api;

import com.tsystems.entity.Wagon;

import java.util.List;

/**
 *
 */
public interface WagonService {
    /**
     *
     * @param wagon
     */
    void addWagon(Wagon wagon);

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
    void updateWagon(Wagon wagon);

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
}
