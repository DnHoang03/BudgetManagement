package com.web.springmvc.budgetmanagement.dto;

import com.web.springmvc.budgetmanagement.model.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class AccountsDto {
    private Long id;
    private String name;
    private long amount;
    private String note;
    private Long userId;
    private Long accountTypeId;
    private Long currencyId;
    private Long iconId;
    private String iconName;
    private String accountTypeName;
    private String currencyName;
}
