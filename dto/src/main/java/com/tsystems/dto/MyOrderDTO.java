package com.tsystems.dto;

import com.tsystems.dto.enums.OrderStatus;

import java.util.Objects;

public class MyOrderDTO {
    private Integer id;
    private String number;
    private String orderStatus;
    private WagonDTO wagon;

    public MyOrderDTO() {
    }

    public MyOrderDTO(Integer id, String number, String orderStatus, WagonDTO wagon) {
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public WagonDTO getWagon() {
        return wagon;
    }

    public void setWagon(WagonDTO wagon) {
        this.wagon = wagon;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", number=" + number +
                ", orderStatus=" + orderStatus +
                ", wagon=" + wagon +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyOrderDTO myOrderDTO = (MyOrderDTO) o;
        return Objects.equals(id, myOrderDTO.id) &&
                Objects.equals(number, myOrderDTO.number) &&
                orderStatus == myOrderDTO.orderStatus &&
                Objects.equals(wagon, myOrderDTO.wagon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, orderStatus, wagon);
    }
}
