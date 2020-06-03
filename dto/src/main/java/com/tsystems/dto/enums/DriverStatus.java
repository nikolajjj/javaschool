package com.tsystems.dto.enums;

public enum DriverStatus {
    REST("REST"),
    REST_IN_SHIFT("REST_IN_SHIFT"),
    DRIVING("DRIVING"),
    SECOND_DRIVER("SECOND_DRIVER"),
    LOAD_UNLOAD_CARGO("LOAD_UNLOAD_CARGO"),
    DISABLED("DISABLED");
    private String status;

    DriverStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
