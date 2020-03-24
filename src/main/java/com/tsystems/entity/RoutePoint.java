package com.tsystems.entity;

/**
 *
 */
public class RoutePoint {
    private City city;
    private Cargo cargo;
    private Boolean status;

    public RoutePoint(City city, Cargo cargo, Boolean status) {
        this.city = city;
        this.cargo = cargo;
        this.status = status;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
