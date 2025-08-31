package com.project.restaurant_management.Entity;
import com.project.restaurant_management.enums.FeedbackStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "feedback")
@Data
@EqualsAndHashCode(callSuper = true)
public class Feedback extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Size(max = 100, message = "Customer name cannot exceed 100 characters")
    @Column(name = "customer_name")
    private String customerName;

    @Email(message = "Invalid email format")
    @Size(max = 200, message = "Email cannot exceed 200 characters")
    @Column(name = "customer_email")
    private String customerEmail;

    @NotNull(message = "Overall rating is required")
    @Min(value = 1, message = "Rating must be between 1 and 5")
    @Max(value = 5, message = "Rating must be between 1 and 5")
    @Column(name = "overall_rating", nullable = false)
    private Integer overallRating;

    @Min(value = 1, message = "Food rating must be between 1 and 5")
    @Max(value = 5, message = "Food rating must be between 1 and 5")
    @Column(name = "food_rating")
    private Integer foodRating;

    @Min(value = 1, message = "Service rating must be between 1 and 5")
    @Max(value = 5, message = "Service rating must be between 1 and 5")
    @Column(name = "service_rating")
    private Integer serviceRating;

    @Min(value = 1, message = "Ambiance rating must be between 1 and 5")
    @Max(value = 5, message = "Ambiance rating must be between 1 and 5")
    @Column(name = "ambiance_rating")
    private Integer ambianceRating;

    @Size(max = 2000, message = "Comments cannot exceed 2000 characters")
    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private FeedbackStatus status = FeedbackStatus.PENDING;

    @Size(max = 1000, message = "Staff response cannot exceed 1000 characters")
    @Column(name = "staff_response", columnDefinition = "TEXT")
    private String staffResponse;

    @Column(name = "responded_at")
    private java.time.LocalDateTime respondedAt;

    @Size(max = 100, message = "Responded by cannot exceed 100 characters")
    @Column(name = "responded_by")
    private String respondedBy;

    // Helper methods
    public Double getAverageRating() {
        int count = 1; // overall rating always exists
        double total = overallRating;

        if (foodRating != null) {
            total += foodRating;
            count++;
        }
        if (serviceRating != null) {
            total += serviceRating;
            count++;
        }
        if (ambianceRating != null) {
            total += ambianceRating;
            count++;
        }

        return total / count;
    }

    public void respond(String response, String respondedBy) {
        this.staffResponse = response;
        this.respondedBy = respondedBy;
        this.respondedAt = java.time.LocalDateTime.now();
        this.status = FeedbackStatus.RESPONDED;
    }
}
