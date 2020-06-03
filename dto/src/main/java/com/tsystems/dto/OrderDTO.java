package com.tsystems.dto;

import java.util.List;

public class OrderDTO {
    private WagonDTO wagon;
    private List<CargoDTO> cargo;
    private List<DriverDTO> driver;

    public OrderDTO() {

    }

    public OrderDTO(WagonDTO wagon, List<CargoDTO> cargo, List<DriverDTO> driver) {
        this.wagon = wagon;
        this.cargo = cargo;
        this.driver = driver;
    }

    public String cargoList() {
        StringBuilder car = new StringBuilder("");
        for (CargoDTO e : cargo) {
            car.append(e.toString());
        }
        return car.toString();
    }

    public WagonDTO getWagon() {
        return wagon;
    }

    public void setWagon(WagonDTO wagon) {
        this.wagon = wagon;
    }

    public List<CargoDTO> getCargo() {
        return cargo;
    }

    public void setCargo(List<CargoDTO> cargo) {
        this.cargo = cargo;
    }

    public List<DriverDTO> getDriver() {
        return driver;
    }

    public void setDriver(List<DriverDTO> driver) {
        this.driver = driver;
    }
}
