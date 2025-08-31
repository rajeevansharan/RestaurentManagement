package com.project.restaurant_management.Repository;

import com.project.restaurant_management.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByActiveTrue();

    List<Category> findByActiveTrueOrderByDisplayOrder();

    Optional<Category> findByNameAndActiveTrue(String name);

    boolean existsByName(String name);

    @Query("SELECT c FROM Category c WHERE c.active = true AND SIZE(c.menuItems) > 0 ORDER BY c.displayOrder")
    List<Category> findActiveCategoriesWithItems();

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.menuItems m WHERE c.active = true AND m.available = true ORDER BY c.displayOrder")
    List<Category> findActiveCategoriesWithAvailableItems();
}

