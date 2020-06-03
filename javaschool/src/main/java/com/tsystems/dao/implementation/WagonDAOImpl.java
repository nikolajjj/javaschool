package com.tsystems.dao.implementation;

import com.tsystems.dao.api.WagonDAO;
import com.tsystems.dto.enums.WagonStatus;
import com.tsystems.entity.Wagon;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 *
 */
@SuppressWarnings("unchecked")
@Repository
public class WagonDAOImpl extends GenericDAOImpl<Wagon, Integer> implements WagonDAO {
    private final static Logger log = Logger.getLogger(WagonDAOImpl.class);

    @Override
    public List<Wagon> getAllDisabledWagons() {
        try {
            Query query = entityManager.createQuery("select wagon from Wagon wagon where wagon.state =: state")
                    .setParameter("state", WagonStatus.DISABLE);
            return (List<Wagon>) query.getResultList();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Wagon getWagonByCarPlate(String carplate) {
        try {
            Query query = entityManager.createQuery("select wagon from Wagon wagon where wagon.car_plate =: carplate")
                    .setParameter("carplate", carplate);
            return (Wagon) query.getResultList().get(0);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
