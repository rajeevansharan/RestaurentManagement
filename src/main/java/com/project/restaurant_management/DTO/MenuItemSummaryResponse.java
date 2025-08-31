package com.project.restaurant_management.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemSummaryResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available;
    private String imageUrl;
    private Integer preparationTimeMinutes;
    private Boolean vegetarian;
    private Boolean vegan;
    private Boolean glutenFree;
}
