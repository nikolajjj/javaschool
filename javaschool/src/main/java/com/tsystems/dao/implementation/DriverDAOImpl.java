package com.tsystems.dao.implementation;

import com.tsystems.dao.api.DriverDAO;
import com.tsystems.dto.enums.DriverStatus;
import com.tsystems.entity.Driver;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * An implementation of DriverDAO api
 */
@SuppressWarnings("unchecked")
@Repository
public class DriverDAOImpl extends GenericDAOImpl<Driver, Integer> implements DriverDAO {
    @Override
    public List<Driver> getCoDrivers(Integer driverId, Integer wagonId) {
        Query query = entityManager.createQuery("select driver from Driver driver where driver.current_city.id =: cityId and driver.current_wagon.id =: wagonId")
                .setParameter("cityId", entityManager.find(Driver.class, driverId).getCurrent_city().getId())
                .setParameter("wagonId", wagonId);
        return (List<Driver>) query.getResultList();
    }

    @Override
    public List<Driver> getDriversByWagonId(Integer wagonId) {
        Query query = entityManager.createQuery("select driver from Driver driver where driver.current_wagon.id =: wagonId")
                .setParameter("wagonId", wagonId);
        return (List<Driver>) query.getResultList();
    }

    @Override
    public List<Driver> getFreeDrivers() {
        Query query = entityManager.createQuery("select driver from Driver driver where driver.current_wagon is null " +
                "and driver.status =: status")
                .setParameter("status", DriverStatus.REST);

        return (List<Driver>) query.getResultList();
    }
}
