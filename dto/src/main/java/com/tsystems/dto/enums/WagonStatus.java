package com.tsystems.dto.enums;

public enum WagonStatus {
    ENABLE("ENABLE"),
    DISABLE("DISABLE");
    private String status;

    private WagonStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
