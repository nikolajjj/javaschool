package com.tsystems.dto;

import java.util.Objects;

public class DriverDTO {
    private Integer id;
    private String first_name;
    private String second_name;
    private String personal_number;
    private String status;
    private CityDTO current_city;
    private WagonDTO current_wagon;
    private Double hoursWorkedThisMonth;

    public DriverDTO() {
    }

    public DriverDTO(Integer id, String first_name, String second_name, String personal_number, String status, CityDTO current_city, Double hoursWorkedThisMonth) {
        this.id = id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.personal_number = personal_number;
        this.status = status;
        this.current_city = current_city;
        this.hoursWorkedThisMonth = hoursWorkedThisMonth;
    }

    public DriverDTO(Integer id, String first_name, String second_name, String personal_number, String status, CityDTO current_city) {
        this.id = id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.personal_number = personal_number;
        this.status = status;
        this.current_city = current_city;
    }

    public DriverDTO(Integer id, String first_name, String second_name, String personal_number, String status, CityDTO current_city, WagonDTO current_wagon) {
        this.id = id;
        this.first_name = first_name;
        this.second_name = second_name;
        this.personal_number = personal_number;
        this.status = status;
        this.current_city = current_city;
        this.current_wagon = current_wagon;
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

    public String getPersonal_number() {
        return personal_number;
    }

    public void setPersonal_number(String personal_number) {
        this.personal_number = personal_number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CityDTO getCurrent_city() {
        return current_city;
    }

    public void setCurrent_city(CityDTO current_city) {
        this.current_city = current_city;
    }

    public Double getHoursWorkedThisMonth() {
        return hoursWorkedThisMonth;
    }

    public void setHoursWorkedThisMonth(Double hoursWorkedThisMonth) {
        this.hoursWorkedThisMonth = hoursWorkedThisMonth;
    }

    public WagonDTO getCurrent_wagon() {
        return current_wagon;
    }

    public void setCurrent_wagon(WagonDTO current_wagon) {
        this.current_wagon = current_wagon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverDTO driverDTO = (DriverDTO) o;
        return Objects.equals(id, driverDTO.id) &&
                Objects.equals(first_name, driverDTO.first_name) &&
                Objects.equals(second_name, driverDTO.second_name) &&
                Objects.equals(personal_number, driverDTO.personal_number) &&
                Objects.equals(status, driverDTO.status) &&
                Objects.equals(current_city, driverDTO.current_city) &&
                Objects.equals(hoursWorkedThisMonth, driverDTO.hoursWorkedThisMonth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, second_name, personal_number, status, current_city, hoursWorkedThisMonth);
    }

    @Override
    public String toString() {
        return "DriverDTO{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", personal_number=" + personal_number +
                ", status='" + status + '\'' +
                ", current_city=" + current_city.toString() +
                ", hoursWorkedThisMonth=" + hoursWorkedThisMonth +
                '}';
    }
}