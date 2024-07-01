package com.web.springmvc.budgetmanagement.service;

import com.web.springmvc.budgetmanagement.dto.AccountsDto;
import com.web.springmvc.budgetmanagement.model.AccountTypes;
import com.web.springmvc.budgetmanagement.model.Accounts;
import com.web.springmvc.budgetmanagement.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountsService {
    private final AccountsRepository accountsRepository;
    private final UsersRepository usersRepository;
    private final AccountTypesRepository accountTypesRepository;
    private final IconsRepository iconsRepository;
    private final CurrenciesRepository currenciesRepository;

    public AccountsDto createAccount(AccountsDto accountsDto) {
        return mapToDto(accountsRepository.save(mapToEntity(accountsDto)));
    }

    public List<AccountsDto> getAllAccountsByUserId(Long id) {
        return accountsRepository.findByUserId(id).stream().map(this::mapToDto).toList();
    }

    public AccountsDto updateAccount(AccountsDto accountsDto, Long id) {
        Accounts accounts = accountsRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found account"));
        accounts.setAmount(accountsDto.getAmount());
        accounts.setName(accountsDto.getName());
        accounts.setIcon(iconsRepository.findById(accountsDto.getIconId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found icon")));
        accounts.setNote(accountsDto.getNote());
        accounts.setCurrency(currenciesRepository.findById(accountsDto.getCurrencyId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found currency")));
        accounts.setAccountType(accountTypesRepository.findById(accountsDto.getAccountTypeId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found accounttype")));
        return mapToDto(accountsRepository.save(mapToEntity(accountsDto)));
    }

    public void deleteAccount(Long id) {
        accountsRepository.deleteById(id);
    }

    private AccountsDto mapToDto(Accounts accounts) {
        return new AccountsDto(accounts.getId(),
                accounts.getName(),
                accounts.getAmount(),
                accounts.getNote(),
                accounts.getUser().getId(),
                accounts.getAccountType().getId(),
                accounts.getCurrency().getId(),
                accounts.getIcon().getId());
    }

    private Accounts mapToEntity(AccountsDto accountsDto) {
        Accounts accounts = new Accounts();
        accounts.setId(accountsDto.getId());
        accounts.setName(accountsDto.getName());
        accounts.setAmount(accountsDto.getAmount());
        accounts.setNote(accountsDto.getNote());
        accounts.setUser(usersRepository.findById(accountsDto.getUserId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found user")));
        accounts.setAccountType(accountTypesRepository.findById(accountsDto.getAccountTypeId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found account type")));
        accounts.setCurrency(currenciesRepository.findById(accountsDto.getCurrencyId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found currency")));
        accounts.setIcon(iconsRepository.findById(accountsDto.getIconId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found icon")));
        return accounts;
    }
}
