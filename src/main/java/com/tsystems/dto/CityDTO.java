package com.tsystems.dto;

import java.util.Objects;

public class CityDTO {
    private Integer id;
    private String name;
    private Double longitude;
    private Double latitude;

    public CityDTO() {
    }

    public CityDTO(Integer id, String name, Double longitude, Double latitude) {
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityDTO cityDTO = (CityDTO) o;
        return Objects.equals(id, cityDTO.id) &&
                Objects.equals(name, cityDTO.name) &&
                Objects.equals(longitude, cityDTO.longitude) &&
                Objects.equals(latitude, cityDTO.latitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, longitude, latitude);
    }
}
