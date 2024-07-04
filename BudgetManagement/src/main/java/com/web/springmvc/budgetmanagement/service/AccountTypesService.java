package com.web.springmvc.budgetmanagement.service;

import com.web.springmvc.budgetmanagement.dto.AccountTypesDto;
import com.web.springmvc.budgetmanagement.model.AccountType;
import com.web.springmvc.budgetmanagement.repository.AccountTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTypesService {
    private final AccountTypeRepository accountTypeRepository;

    public List<AccountTypesDto> getAllAccountTypes() {
        return accountTypeRepository.findAll().stream().map(this::mapToDto).toList();
    }

    private AccountTypesDto mapToDto(AccountType accountType) {
        return new AccountTypesDto(accountType.getId(), accountType.getName());
    }
}
