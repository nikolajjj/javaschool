package com.tsystems.service.implementation;

import com.tsystems.dao.api.OrderDAO;
import com.tsystems.entity.Order;
import com.tsystems.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;

    @Autowired
    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    @Transactional
    public void addOrder(Order order) {
        orderDAO.add(order);
    }

    @Override
    @Transactional
    public Order findOrderById(Integer id) {
        return orderDAO.findById(id);
    }

    @Override
    @Transactional
    public void updateOrder(Order order) {
        orderDAO.update(order);
    }

    @Override
    @Transactional
    public void deleteOrder(Order order) {
        orderDAO.delete(order);
    }

    @Override
    @Transactional
    public List<Order> getAllOrders() {
        return orderDAO.getAll();
    }
}
