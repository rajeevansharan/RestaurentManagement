package com.project.restaurant_management.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
    private Integer displayOrder;
    private Boolean active;
    private Integer itemCount;
    private List<MenuItemSummaryResponse> menuItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
