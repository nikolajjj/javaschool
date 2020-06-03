package com.tsystems.dao.implementation;

import com.tsystems.dao.api.CargoDAO;
import com.tsystems.entity.Cargo;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class CargoDAOImpl extends GenericDAOImpl<Cargo, Integer> implements CargoDAO {
    @Override
    public List<Cargo> getCargoesByOrderId(Integer orderId) {
        Query query = entityManager.createQuery("select cargo from Cargo cargo where cargo.order.id =: order_id")
                .setParameter("order_id", orderId);
        return (List<Cargo>) query.getResultList();
    }
}
