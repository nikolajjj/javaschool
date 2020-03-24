package com.tsystems.service.api;

import com.tsystems.entity.Order;

import java.util.List;

public interface OrderService {
    void addOrder(Order order);

    /**
     *
     * @param id
     * @return
     */
    Order findOrderById(Integer id);

    /**
     *
     * @param order
     */
    void updateOrder(Order order);

    /**
     *
     * @param order
     */
    void deleteOrder(Order order);

    /**
     *
     * @return
     */
    List<Order> getAllOrders();
}
