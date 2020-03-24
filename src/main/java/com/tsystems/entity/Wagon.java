package com.tsystems.entity;

import com.tsystems.entity.enums.WagonStatus;

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

    @Column(name = "car_plate", unique = true)
    private String car_plate;

    @Column(name = "driver_shift_count")
    private Integer driver_shift_count;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private WagonStatus state;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "current_city")
    private City current_city;

    @OneToMany(mappedBy = "current_wagon")
    private List<Driver> driversInWagon;

    @OneToOne(mappedBy = "wagon")
    private Order order;

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

    public List<Driver> getDriversInWagon() {
        return driversInWagon;
    }

    public void setDriversInWagon(List<Driver> driversInWagon) {
        this.driversInWagon = driversInWagon;
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

    /*public City getCurrent_city() {
        return current_city;
    }

    public void setCurrent_city(City current_city) {
        this.current_city = current_city;
    }*/

    /*public List<Driver> getDriversInWagon() {
        return driversInWagon;
    }

    public void setDriversInWagon(List<Driver> driversInWagon) {
        this.driversInWagon = driversInWagon;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wagon wagon = (Wagon) o;
        return Objects.equals(id, wagon.id) &&
                Objects.equals(car_plate, wagon.car_plate) &&
                Objects.equals(capacity, wagon.capacity) &&
                Objects.equals(state, wagon.state) &&
                Objects.equals(current_city, wagon.current_city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, car_plate, capacity, state, current_city);
    }

    @Override
    public String toString() {
        return "Wagon{" +
                "id=" + id +
                ", car_plate='" + car_plate + '\'' +
                ", capacity=" + capacity +
                ", condition=" + state +
                ", current_city=" + current_city.toString() +
                '}';
    }
}


