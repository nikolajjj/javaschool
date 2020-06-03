package com.tsystems.dto;

import java.util.Objects;

public class WagonDTO {
    private Integer id;
    private String car_plate;
    private Integer driver_shift_count;
    private Integer capacity;
    private String state;
    private CityDTO current_city;

    public WagonDTO() {
    }

    // proxy
    public WagonDTO(Integer id) {
        this.id = id;
    }

    public WagonDTO(Integer id, String car_plate, Integer driver_shift_count, Integer capacity, String wagonStatus, CityDTO current_city) {
        this.id = id;
        this.car_plate = car_plate;
        this.driver_shift_count = driver_shift_count;
        this.capacity = capacity;
        this.state = wagonStatus;
        this.current_city = current_city;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCar_plate() {
        return car_plate;
    }

    public void setCar_plate(String car_plate) {
        this.car_plate = car_plate;
    }

    public Integer getDriver_shift_count() {
        return driver_shift_count;
    }

    public void setDriver_shift_count(Integer driver_shift_count) {
        this.driver_shift_count = driver_shift_count;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public CityDTO getCurrent_city() {
        return current_city;
    }

    public void setCurrent_city(CityDTO current_city) {
        this.current_city = current_city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WagonDTO wagonDTO = (WagonDTO) o;
        return Objects.equals(id, wagonDTO.id) &&
                Objects.equals(car_plate, wagonDTO.car_plate) &&
                Objects.equals(driver_shift_count, wagonDTO.driver_shift_count) &&
                Objects.equals(capacity, wagonDTO.capacity) &&
                state == wagonDTO.state &&
                Objects.equals(current_city, wagonDTO.current_city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car_plate, driver_shift_count, capacity, state, current_city);
    }

    @Override
    public String toString() {
        return "WagonDTO{" +
                "id=" + id +
                ", car_plate='" + car_plate + '\'' +
                ", driver_shift_count=" + driver_shift_count +
                ", capacity=" + capacity +
                ", wagonStatus='" + state + '\'' +
                ", current_city=" + current_city +
                '}';
    }
}
