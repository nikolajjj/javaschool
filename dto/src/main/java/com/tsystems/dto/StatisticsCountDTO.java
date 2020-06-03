package com.tsystems.dto;

import java.util.List;

public class StatisticsCountDTO {
    private Integer Drivers;
    private Integer FreeDrivers;
    private Integer BusyDrivers;
    private Integer Wagons;
    private Integer FreeWagons;
    private Integer BusyWagons;
    private Integer disabledWagons;
    private List<MyOrderDTO> orders;

    public StatisticsCountDTO() {
    }

    public StatisticsCountDTO(Integer drivers, Integer freeDrivers, Integer busyDrivers, Integer wagons,
                              Integer freeWagons, Integer busyWagons, Integer disabledWagons, List<MyOrderDTO> orders) {
        this.Drivers = drivers;
        this.FreeDrivers = freeDrivers;
        this.BusyDrivers = busyDrivers;
        this.Wagons = wagons;
        this.FreeWagons = freeWagons;
        this.BusyWagons = busyWagons;
        this.disabledWagons = disabledWagons;
        this.orders = orders;
    }

    public Integer getDrivers() {
        return Drivers;
    }

    public void setDrivers(Integer drivers) {
        Drivers = drivers;
    }

    public Integer getFreeDrivers() {
        return FreeDrivers;
    }

    public void setFreeDrivers(Integer freeDrivers) {
        FreeDrivers = freeDrivers;
    }

    public Integer getBusyDrivers() {
        return BusyDrivers;
    }

    public void setBusyDrivers(Integer busyDrivers) {
        BusyDrivers = busyDrivers;
    }

    public Integer getWagons() {
        return Wagons;
    }

    public void setWagons(Integer wagons) {
        Wagons = wagons;
    }

    public Integer getFreeWagons() {
        return FreeWagons;
    }

    public void setFreeWagons(Integer freeWagons) {
        FreeWagons = freeWagons;
    }

    public Integer getBusyWagons() {
        return BusyWagons;
    }

    public void setBusyWagons(Integer busyWagons) {
        BusyWagons = busyWagons;
    }

    public Integer getDisabledWagons() {
        return disabledWagons;
    }

    public void setDisabledWagons(Integer disabledWagons) {
        this.disabledWagons = disabledWagons;
    }

    public List<MyOrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<MyOrderDTO> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "StatisticsCountDTO{" +
                "Drivers=" + Drivers +
                ", FreeDrivers=" + FreeDrivers +
                ", BusyDrivers=" + BusyDrivers +
                ", Wagons=" + Wagons +
                ", FreeWagons=" + FreeWagons +
                ", BusyWagons=" + BusyWagons +
                ", disabledWagons=" + disabledWagons +
                '}';
    }
}
