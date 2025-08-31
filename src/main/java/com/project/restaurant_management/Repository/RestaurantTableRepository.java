package com.project.restaurant_management.Repository;

import com.project.restaurant_management.Entity.RestaurantTable;
import com.project.restaurant_management.enums.TableStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {

    List<RestaurantTable> findByActiveTrue();

    List<RestaurantTable> findByStatusAndActiveTrue(TableStatus status);

    List<RestaurantTable> findByCapacityGreaterThanEqualAndActiveTrue(Integer capacity);

    Optional<RestaurantTable> findByTableNumberAndActiveTrue(String tableNumber);

    boolean existsByTableNumber(String tableNumber);

    @Query("SELECT t FROM RestaurantTable t WHERE t.active = true AND t.status = 'AVAILABLE' " +
            "AND t.capacity >= :requiredCapacity ORDER BY t.capacity ASC")
    List<RestaurantTable> findAvailableTablesWithCapacity(@Param("requiredCapacity") Integer requiredCapacity);

    @Query("SELECT t FROM RestaurantTable t WHERE t.id NOT IN " +
            "(SELECT r.table.id FROM Reservation r WHERE r.status IN ('CONFIRMED', 'PENDING') " +
            "AND r.reservationDateTime BETWEEN :startTime AND :endTime) " +
            "AND t.capacity >= :requiredCapacity AND t.active = true AND t.status = 'AVAILABLE'")
    List<RestaurantTable> findAvailableTablesForDateTime(@Param("startTime") LocalDateTime startTime,
                                                         @Param("endTime") LocalDateTime endTime,
                                                         @Param("requiredCapacity") Integer requiredCapacity);

    @Query("SELECT COUNT(t) FROM RestaurantTable t WHERE t.status = :status AND t.active = true")
    long countByStatus(@Param("status") TableStatus status);

    @Query("SELECT t FROM RestaurantTable t WHERE t.location = :location AND t.active = true")
    List<RestaurantTable> findByLocation(@Param("location") String location);
}
