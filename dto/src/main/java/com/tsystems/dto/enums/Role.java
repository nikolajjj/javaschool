package com.tsystems.dto.enums;

public enum Role {
    ADMIN("ADMIN"),
    EMPLOYEE("EMPLOYEE"),
    DRIVER("DRIVER");
    private String status;

    Role(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
