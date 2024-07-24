package com.web.springmvc.budgetmanagement.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private LocalDateTime date;
    private long income;
    private long cost;
    private List<TransactionsDto> transactions;
}
