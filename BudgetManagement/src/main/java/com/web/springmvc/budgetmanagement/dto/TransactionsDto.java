package com.web.springmvc.budgetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TransactionsDto {
    private Long id;
    private long amount;
    private String name;
    private String type;
    private LocalDateTime createdAt;
    private Long transferAccountId;
    private Long receiveAccountId;
    private Long iconNoteId;
    private String iconNoteName;
    private String receiveAccountName;
    private String transferAccountName;
}
