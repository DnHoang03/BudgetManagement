package com.web.springmvc.budgetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IconNotesDto {
    private Long id;
    private String name;
    private Long iconId;
}
