package com.tsystems.dto.enums;

public enum OrderStatus {
    CREATED("CREATED"),
    DONE("DONE");
    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

}
