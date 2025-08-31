package com.project.restaurant_management.enums;

public enum FeedbackStatus {
    PENDING("Pending Review"),
    REVIEWED("Reviewed"),
    RESPONDED("Responded"),
    ESCALATED("Escalated"),
    RESOLVED("Resolved"),
    ARCHIVED("Archived");

    private final String displayName;

    FeedbackStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean requiresAttention() {
        return this == PENDING || this == ESCALATED;
    }
}

