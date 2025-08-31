package com.project.restaurant_management.Repository;
import com.project.restaurant_management.Entity.Reservation;
import com.project.restaurant_management.enums.ReservationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByStatus(ReservationStatus status);

    Page<Reservation> findByStatus(ReservationStatus status, Pageable pageable);

    List<Reservation> findByCustomerEmail(String customerEmail);

    List<Reservation> findByCustomerEmailOrderByReservationDateTimeDesc(String customerEmail);

    @Query("SELECT r FROM Reservation r WHERE r.reservationDateTime BETWEEN :startDate AND :endDate")
    List<Reservation> findByDateRange(@Param("startDate") LocalDateTime startDate,
                                      @Param("endDate") LocalDateTime endDate);

    @Query("SELECT r FROM Reservation r WHERE r.reservationDateTime BETWEEN :startDate AND :endDate AND r.status = :status")
    List<Reservation> findByDateRangeAndStatus(@Param("startDate") LocalDateTime startDate,
                                               @Param("endDate") LocalDateTime endDate,
                                               @Param("status") ReservationStatus status);

    @Query("SELECT r FROM Reservation r WHERE r.table.id = :tableId AND r.status IN ('CONFIRMED', 'PENDING') " +
            "AND r.reservationDateTime BETWEEN :startTime AND :endTime")
    List<Reservation> findConflictingReservations(@Param("tableId") Long tableId,
                                                  @Param("startTime") LocalDateTime startTime,
                                                  @Param("endTime") LocalDateTime endTime);

    @Query("SELECT r FROM Reservation r WHERE r.reservationDateTime < :cutoffTime AND r.status = 'PENDING'")
    List<Reservation> findExpiredPendingReservations(@Param("cutoffTime") LocalDateTime cutoffTime);

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.reservationDateTime BETWEEN :startDate AND :endDate AND r.status = 'CONFIRMED'")
    long countConfirmedReservationsForPeriod(@Param("startDate") LocalDateTime startDate,
                                             @Param("endDate") LocalDateTime endDate);

    @Query("SELECT r FROM Reservation r WHERE r.status = 'CONFIRMED' AND " +
            "r.reservationDateTime BETWEEN :now AND :reminderTime")
    List<Reservation> findReservationsNeedingReminder(@Param("now") LocalDateTime now,
                                                      @Param("reminderTime") LocalDateTime reminderTime);
} 
