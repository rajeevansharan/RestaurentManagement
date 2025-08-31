package com.project.restaurant_management.enums;

public enum OrderStatus {
    PENDING("Pending"),
    CONFIRMED("Confirmed"),
    PREPARING("Preparing"),
    READY("Ready"),
    SERVED("Served"),
    CANCELLED("Cancelled"),
    REFUNDED("Refunded");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isActiveStatus() {
        return this == PENDING || this == CONFIRMED || this == PREPARING || this == READY;
    }

    public boolean isCompletedStatus() {
        return this == SERVED || this == CANCELLED || this == REFUNDED;
    }

    public boolean canBeModified() {
        return this == PENDING || this == CONFIRMED;
    }
}