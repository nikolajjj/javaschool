package com.tsystems.entity;

import com.tsystems.entity.enums.DriverStatus;

import javax.persistence.*;
import java.util.List;

/**
 * Driver entity
 */
@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "second_name")
    private String second_name;

    @Column(name = "personal_number", unique = true)
    private Integer personal_number;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private DriverStatus status;

    @ManyToOne
    @JoinColumn(name = "current_city")
    private City current_city;

    @ManyToOne
    @JoinColumn(name = "current_wagon")
    private Wagon current_wagon;

    @OneToMany(mappedBy = "driver")
    private List<DriverShift> driverList;

    public Driver() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public Integer getPersonal_number() {
        return personal_number;
    }

    public void setPersonal_number(Integer personal_number) {
        this.personal_number = personal_number;
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public City getCurrent_city() {
        return current_city;
    }

    public void setCurrent_city(City current_city) {
        this.current_city = current_city;
    }

    public Wagon getCurrent_wagon() {
        return current_wagon;
    }

    public void setCurrent_wagon(Wagon current_wagon) {
        this.current_wagon = current_wagon;
    }
}
