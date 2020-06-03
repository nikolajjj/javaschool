package com.tsystems.entity;

import com.tsystems.dto.enums.WagonStatus;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Wagon entity
 */
@Entity
@Table(name = "wagon")
public class Wagon {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "car_plate")
    private String car_plate;

    @Column(name = "driver_shift_count")
    private Integer driver_shift_count;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private WagonStatus state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "current_city")
    private City current_city;

    @OneToMany(mappedBy = "current_wagon")
    private List<Driver> driversInWagon;

    @OneToMany(mappedBy = "wagon")
    private List<Order> order;

    public Wagon() {
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

    public City getCurrent_city() {
        return current_city;
    }

    public void setCurrent_city(City current_city) {
        this.current_city = current_city;
    }

    public WagonStatus getState() {
        return state;
    }

    public void setState(WagonStatus state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wagon wagon = (Wagon) o;
        return Objects.equals(id, wagon.id) &&
                Objects.equals(car_plate, wagon.car_plate) &&
                Objects.equals(driver_shift_count, wagon.driver_shift_count) &&
                Objects.equals(capacity, wagon.capacity) &&
                state == wagon.state &&
                Objects.equals(current_city, wagon.current_city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car_plate, driver_shift_count, capacity, state, current_city);
    }

    @Override
    public String toString() {
        return id.toString();
    }
}