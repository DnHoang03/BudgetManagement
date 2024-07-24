package com.web.springmvc.budgetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UsersDto {
    private Long id;
    private String username;
    private String gender;
    private long budget;
    private String imageUrl;
}
