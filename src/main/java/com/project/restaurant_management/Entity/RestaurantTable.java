package com.project.restaurant_management.Entity;
import com.project.restaurant_management.enums.TableStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant_tables")
@Data
@EqualsAndHashCode(callSuper = true)
public class RestaurantTable extends BaseEntity {

    @NotBlank(message = "Table number is required")
    @Size(min = 1, max = 20, message = "Table number must be between 1 and 20 characters")
    @Column(name = "table_number", nullable = false, unique = true)
    private String tableNumber;

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 20, message = "Capacity cannot exceed 20")
    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TableStatus status = TableStatus.AVAILABLE;

    @Size(max = 100, message = "Location cannot exceed 100 characters")
    @Column(name = "location")
    private String location;

    @Size(max = 500, message = "Special features cannot exceed 500 characters")
    @Column(name = "special_features")
    private String specialFeatures;

    @Column(name = "active")
    private Boolean active = true;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    // Helper methods
    public boolean isAvailable() {
        return TableStatus.AVAILABLE.equals(this.status) && this.active;
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
        reservation.setTable(this);
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setTable(this);
    }
}
