package com.tsystems.entity;

import javax.persistence.*;

/**
 * Wagon entity
 */
@Entity
@Table(name = "wagon")
public class Wagon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "car_plate")
    private String car_plate;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "condition")
    private Integer condition;

    @Column(name = "current_city")
    private Integer current_city;

    public Wagon() {
    }

    public Wagon(String car_plate, Integer capacity, Integer condition, Integer current_city) {
        this.car_plate = car_plate;
        this.capacity = capacity;
        this.condition = condition;
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    public Integer getCurrent_city() {
        return current_city;
    }

    public void setCurrent_city(Integer current_city) {
        this.current_city = current_city;
    }
}
