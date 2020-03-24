package com.tsystems.dto;

import com.tsystems.entity.enums.OrderStatus;

import java.util.Objects;

public class OrderDTO {
    private Integer id;
    private Integer number;
    private OrderStatus orderStatus;
    private WagonDTO wagon;

    public OrderDTO() {
    }

    public OrderDTO(Integer id, Integer number, OrderStatus orderStatus, WagonDTO wagon) {
        this.id = id;
        this.number = number;
        this.orderStatus = orderStatus;
        this.wagon = wagon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public WagonDTO getWagon() {
        return wagon;
    }

    public void setWagon(WagonDTO wagon) {
        this.wagon = wagon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(id, orderDTO.id) &&
                Objects.equals(number, orderDTO.number) &&
                orderStatus == orderDTO.orderStatus &&
                Objects.equals(wagon, orderDTO.wagon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, orderStatus, wagon);
    }
}
