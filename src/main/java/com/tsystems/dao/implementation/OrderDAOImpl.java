package com.tsystems.dao.implementation;

import com.tsystems.dao.api.OrderDAO;
import com.tsystems.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAOImpl extends GenericDAOImpl<Order, Integer> implements OrderDAO {
}
