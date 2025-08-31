package com.project.restaurant_management.Entity;

import com.project.restaurant_management.enums.ReservationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Data
@EqualsAndHashCode(callSuper = true)
public class Reservation extends BaseEntity {

    @NotBlank(message = "Customer name is required")
    @Size(min = 2, max = 100, message = "Customer name must be between 2 and 100 characters")
    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @NotBlank(message = "Customer email is required")
    @Email(message = "Invalid email format")
    @Size(max = 200, message = "Email cannot exceed 200 characters")
    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    @Column(name = "customer_phone")
    private String customerPhone;

    @NotNull(message = "Party size is required")
    @Min(value = 1, message = "Party size must be at least 1")
    @Max(value = 20, message = "Party size cannot exceed 20")
    @Column(name = "party_size", nullable = false)
    private Integer partySize;

    @NotNull(message = "Reservation date and time is required")
    @Future(message = "Reservation must be in the future")
    @Column(name = "reservation_datetime", nullable = false)
    private LocalDateTime reservationDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReservationStatus status = ReservationStatus.PENDING;

    @Size(max = 500, message = "Special requests cannot exceed 500 characters")
    @Column(name = "special_requests")
    private String specialRequests;

    @Column(name = "confirmed_at")
    private LocalDateTime confirmedAt;

    @Column(name = "cancelled_at")
    private LocalDateTime cancelledAt;

    @Size(max = 500, message = "Cancellation reason cannot exceed 500 characters")
    @Column(name = "cancellation_reason")
    private String cancellationReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    private RestaurantTable table;

    // Helper methods
    public boolean isActive() {
        return ReservationStatus.CONFIRMED.equals(this.status) ||
                ReservationStatus.PENDING.equals(this.status);
    }

    public void confirm() {
        this.status = ReservationStatus.CONFIRMED;
        this.confirmedAt = LocalDateTime.now();
    }

    public void cancel(String reason) {
        this.status = ReservationStatus.CANCELLED;
        this.cancelledAt = LocalDateTime.now();
        this.cancellationReason = reason;
    }
}