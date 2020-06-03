package com.tsystems.dao.implementation;

import com.tsystems.dao.api.OrderDAO;
import com.tsystems.entity.DriverShift;
import com.tsystems.entity.Order;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.Query;

@Repository
public class OrderDAOImpl extends GenericDAOImpl<Order, Integer> implements OrderDAO {
    @Override
    public Order getOrderByWagonId(Integer wagonId) {
        Query query = entityManager.createQuery("select order from Order order where order.wagon.id =: wagonId order by order.id desc")
                .setParameter("wagonId", wagonId)
                .setMaxResults(1);
        if (CollectionUtils.isEmpty(query.getResultList())) {
            return null;
        } else {
            return (Order) query.getResultList().get(0);
        }
    }
}
