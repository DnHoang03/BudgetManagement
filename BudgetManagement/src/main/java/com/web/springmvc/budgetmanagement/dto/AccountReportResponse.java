package com.web.springmvc.budgetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class AccountReportResponse {
    private Long asset;
    private Long debt;
    private Long total;
    private List<AccountsDto> accounts;
}
