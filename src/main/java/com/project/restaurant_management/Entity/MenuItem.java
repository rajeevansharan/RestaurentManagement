package com.project.restaurant_management.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu_items")
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuItem extends BaseEntity {

    @NotBlank(message = "Menu item name is required")
    @Size(min = 2, max = 200, message = "Menu item name must be between 2 and 200 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    @DecimalMax(value = "9999.99", message = "Price cannot exceed 9999.99")
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "available")
    private Boolean available = true;

    @Size(max = 500, message = "Image URL cannot exceed 500 characters")
    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "preparation_time")
    private Integer preparationTimeMinutes;

    @Size(max = 200, message = "Allergen info cannot exceed 200 characters")
    @Column(name = "allergen_info")
    private String allergenInfo;

    @Column(name = "calories")
    private Integer calories;

    @Column(name = "spice_level")
    private Integer spiceLevel;

    @Column(name = "vegetarian")
    private Boolean vegetarian = false;

    @Column(name = "vegan")
    private Boolean vegan = false;

    @Column(name = "gluten_free")
    private Boolean glutenFree = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();
}
