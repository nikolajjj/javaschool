package com.tsystems.dto;

import com.tsystems.entity.Cargo;
import com.tsystems.entity.enums.CargoStatus;

import java.util.Objects;

public class CargoDTO {
    private Integer id;
    private String description;
    private Integer weight;
    private String cargoStatus;
    private CityDTO city_from;
    private CityDTO city_to;
    private OrderDTO order;

    public CargoDTO() {
    }

    public CargoDTO(Integer id, String description, Integer weight, String cargoStatus, CityDTO city_from, CityDTO city_to/*, OrderDTO order*/) {
        this.id = id;
        this.description = description;
        this.weight = weight;
        this.cargoStatus = cargoStatus;
        this.city_from = city_from;
        this.city_to = city_to;/*
        this.order = order;*/
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String  getCargoStatus() {
        return cargoStatus;
    }

    public void setCargoStatus(String cargoStatus) {
        this.cargoStatus = cargoStatus;
    }

    public CityDTO getCity_from() {
        return city_from;
    }

    public void setCity_from(CityDTO city_from) {
        this.city_from = city_from;
    }

    public CityDTO getCity_to() {
        return city_to;
    }

    public void setCity_to(CityDTO city_to) {
        this.city_to = city_to;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CargoDTO cargoDTO = (CargoDTO) o;
        return Objects.equals(id, cargoDTO.id) &&
                Objects.equals(description, cargoDTO.description) &&
                Objects.equals(weight, cargoDTO.weight) &&
                cargoStatus == cargoDTO.cargoStatus &&
                Objects.equals(city_from, cargoDTO.city_from) &&
                Objects.equals(city_to, cargoDTO.city_to) &&
                Objects.equals(order, cargoDTO.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, weight, cargoStatus, city_from, city_to, order);
    }
}
