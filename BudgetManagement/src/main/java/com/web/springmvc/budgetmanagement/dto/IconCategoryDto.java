package com.web.springmvc.budgetmanagement.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IconCategoryDto {
    private Long id;
    private String name;
}
