package com.web.springmvc.budgetmanagement.service;

import com.web.springmvc.budgetmanagement.dto.AccountTypesDto;
import com.web.springmvc.budgetmanagement.model.AccountTypes;
import com.web.springmvc.budgetmanagement.repository.AccountTypesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTypesService {
    private final AccountTypesRepository accountTypesRepository;

    public List<AccountTypesDto> getAllAccountTypes() {
        return accountTypesRepository.findAll().stream().map(this::mapToDto).toList();
    }

    private AccountTypesDto mapToDto(AccountTypes accountTypes) {
        return new AccountTypesDto(accountTypes.getId(), accountTypes.getName());
    }
}
