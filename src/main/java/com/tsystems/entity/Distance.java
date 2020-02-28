package com.tsystems.entity;

import javax.persistence.*;

/**
 * Distance entity
 */

@Entity
@Table(name = "distance")
public class Distance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "city_from")
    private Integer city_from;

    @Column(name = "city_to")
    private Integer city_to;

    @Column(name = "distance")
    private Integer distance;

    public Distance() {

    }

    public Distance(Integer city_from, Integer city_to, Integer distance) {
        this.city_from = city_from;
        this.city_to = city_to;
        this.distance = distance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCity_from() {
        return city_from;
    }

    public void setCity_from(Integer city_from) {
        this.city_from = city_from;
    }

    public Integer getCity_to() {
        return city_to;
    }

    public void setCity_to(Integer city_to) {
        this.city_to = city_to;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}
