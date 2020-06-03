package com.tsystems.dto.enums;

public enum  CargoStatus {
    PREPARED("PREPARED"),
    SHIPPED("SHIPPED"),
    DELIVERED("DELIVERED");
    private String status;
    private CargoStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
