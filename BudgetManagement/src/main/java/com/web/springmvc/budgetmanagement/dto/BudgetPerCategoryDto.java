package com.web.springmvc.budgetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BudgetPerCategoryDto {
    private Long id;
    private Long amount;
    private Long userId;
    private Long iconNotesId;
}
