package com.web.springmvc.budgetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TransactionsDto {
    private Long id;
    private long amount;
    private LocalDateTime createdAt;
    private Long transferAccountId;
    private Long receiveAccountId;
}
