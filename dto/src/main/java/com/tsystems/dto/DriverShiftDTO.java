package com.tsystems.dto;

import java.util.Date;

public class DriverShiftDTO {
    private Integer id;
    private Date begin;
    private Date end;
    private DriverDTO driverDTO;

    public DriverShiftDTO() {
    }

    public DriverShiftDTO(Date begin, Date end) {
        this.begin = begin;
        this.end = end;
    }

    public DriverShiftDTO(Integer id, Date begin, Date end, DriverDTO driverDTO) {
        this.id = id;
        this.begin = begin;
        this.end = end;
        this.driverDTO = driverDTO;
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

    public DriverDTO getDriverDTO() {
        return driverDTO;
    }

    public void setDriverDTO(DriverDTO driverDTO) {
        this.driverDTO = driverDTO;
    }
}
