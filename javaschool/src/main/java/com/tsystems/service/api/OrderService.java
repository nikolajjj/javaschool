package com.tsystems.service.api;

import com.tsystems.dto.OrderDTO;
import com.tsystems.entity.Order;

import java.util.List;

public interface OrderService {
    void addOrder(OrderDTO order);

    /**
     *
     * @param id
     * @return
     */
    Order findOrderById(Integer id);

    /**
     *
     * @param id
     * @return
     */
    Order findOrderByWagonId(Integer id);

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
