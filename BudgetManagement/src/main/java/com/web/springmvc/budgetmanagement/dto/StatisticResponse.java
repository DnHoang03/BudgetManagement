package com.web.springmvc.budgetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticResponse {
    private Integer month;
    private Integer year;
    private long cost;
    private long income;
}
