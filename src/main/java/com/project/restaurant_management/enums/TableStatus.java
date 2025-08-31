package com.project.restaurant_management.enums;

public enum TableStatus {
    AVAILABLE("Available"),
    OCCUPIED("Occupied"),
    RESERVED("Reserved"),
    OUT_OF_SERVICE("Out of Service"),
    CLEANING("Cleaning");

    private final String displayName;

    TableStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}