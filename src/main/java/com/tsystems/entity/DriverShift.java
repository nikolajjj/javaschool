package com.tsystems.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "driver_shift")
public class DriverShift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "begin")
    private Date begin;

    @Column(name = "end")
    private Date end;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    public DriverShift() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
