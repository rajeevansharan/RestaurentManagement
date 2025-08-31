package com.project.restaurant_management.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    List<MenuItem> findByAvailableTrue();

    List<MenuItem> findByCategoryIdAndAvailableTrue(Long categoryId);

    List<MenuItem> findByCategoryIdAndAvailableTrueOrderByName(Long categoryId);

    Page<MenuItem> findByAvailableTrue(Pageable pageable);

    @Query("SELECT m FROM MenuItem m WHERE m.available = true AND m.name ILIKE %:searchTerm%")
    List<MenuItem> searchByName(@Param("searchTerm") String searchTerm);

    @Query("SELECT m FROM MenuItem m WHERE m.available = true AND m.price BETWEEN :minPrice AND :maxPrice")
    List<MenuItem> findByPriceRange(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice);

    @Query("SELECT m FROM MenuItem m WHERE m.vegetarian = true AND m.available = true")
    List<MenuItem> findVegetarianItems();

    @Query("SELECT m FROM MenuItem m WHERE m.vegan = true AND m.available = true")
    List<MenuItem> findVeganItems();

    @Query("SELECT m FROM MenuItem m WHERE m.glutenFree = true AND m.available = true")
    List<MenuItem> findGlutenFreeItems();

    @Query("SELECT m FROM MenuItem m WHERE m.category.id = :categoryId AND " +
            "(m.vegetarian = :vegetarian OR :vegetarian IS NULL) AND " +
            "(m.vegan = :vegan OR :vegan IS NULL) AND " +
            "(m.glutenFree = :glutenFree OR :glutenFree IS NULL) AND " +
            "m.available = true")
    List<MenuItem> findWithFilters(@Param("categoryId") Long categoryId,
                                   @Param("vegetarian") Boolean vegetarian,
                                   @Param("vegan") Boolean vegan,
                                   @Param("glutenFree") Boolean glutenFree);

    @Query("SELECT COUNT(m) FROM MenuItem m WHERE m.category.id = :categoryId AND m.available = true")
    long countByCategoryAndAvailable(@Param("categoryId") Long categoryId);
}


