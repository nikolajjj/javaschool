package com.tsystems.dao.implementation;

import com.tsystems.dao.api.DriverShiftDAO;
import com.tsystems.entity.Driver;
import com.tsystems.entity.DriverShift;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.Query;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class DriverShiftDAOImpl extends GenericDAOImpl<DriverShift, Integer> implements DriverShiftDAO {
    /**
     *
     * @param id - driver id
     * @return
     */
//    @Transactional
    @Override
    public DriverShift getLastDriverShiftByDriverId(Integer id) {
        Query query = entityManager.createQuery("select shift from DriverShift shift where shift.driver.id =:id order by shift.id desc")
                .setParameter("id", id)
                .setMaxResults(1);
        if (CollectionUtils.isEmpty(query.getResultList())) {
            return new DriverShift();
        } else {
            return (DriverShift) query.getResultList().get(0);
        }
    }

    @Override
    public List<DriverShift> getLastDriverShiftsByDriverId(Integer id) {
        Query query = entityManager.createQuery("select shift from DriverShift shift where shift.driver.id =:id order by shift.id desc")
                .setParameter("id", id);
        return (List<DriverShift>) query.getResultList();
    }
}
