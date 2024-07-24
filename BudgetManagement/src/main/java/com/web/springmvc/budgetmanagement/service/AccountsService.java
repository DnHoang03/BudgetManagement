package com.web.springmvc.budgetmanagement.service;

import com.web.springmvc.budgetmanagement.dto.AccountsDto;
import com.web.springmvc.budgetmanagement.dto.AccountReportResponse;
import com.web.springmvc.budgetmanagement.exception.ResourceNotFoundException;
import com.web.springmvc.budgetmanagement.model.Account;
import com.web.springmvc.budgetmanagement.model.User;
import com.web.springmvc.budgetmanagement.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountsService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final IconRepository iconRepository;
    private final CurrencyRepository currencyRepository;
    private final UsersService usersService;
    public AccountsDto createAccount(AccountsDto accountsDto) {
        return mapToDto(accountRepository.save(mapToEntity(accountsDto)));
    }

    public List<AccountsDto> getAllAccountsByUserId(Long id) {
        return accountRepository.findByUserId(id).stream().map(this::mapToDto).toList();
    }

    public AccountsDto getAccountById(Long id) {
        return mapToDto(accountRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found account")));
    }

    public AccountReportResponse getResponseAccountByUserId(Long id) {
        List<Account> accounts = accountRepository.findByUserId(id);
        long debt = 0, asset = 0, total = 0;
        for (Account account : accounts) {
            if (account.getAccountType().getName().equals("Tôi nợ/ Tài khoản phải trả")
                    || account.getAccountType().getName().equals("Thẻ tín dụng")) {
                debt += account.getAmount();
                total -= account.getAmount();
            } else {
                total += account.getAmount();
                if (!account.getAccountType().getName().equals("Thẻ ghi nợ")) {
                    asset += account.getAmount();
                }
            }
        }
        return new AccountReportResponse(asset, debt, total, accounts.stream().map(this::mapToDto).toList());
    }

    public AccountsDto updateAccount(AccountsDto accountsDto, Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found account"));
        account.setAmount(accountsDto.getAmount());
        account.setName(accountsDto.getName());
        account.setIcon(iconRepository.findById(accountsDto.getIconId())
                .orElseThrow(()-> new ResourceNotFoundException("Not found account")));
        account.setNote(accountsDto.getNote());
        account.setCurrency(currencyRepository.findById(accountsDto.getCurrencyId())
                .orElseThrow(()-> new ResourceNotFoundException("Not found currency")));
        account.setAccountType(accountTypeRepository.findById(accountsDto.getAccountTypeId()).orElseThrow(()-> new ResourceNotFoundException("Not found accountType")));
        return mapToDto(accountRepository.save(mapToEntity(accountsDto)));
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    private AccountsDto mapToDto(Account account) {
        return new AccountsDto(account.getId(),
                account.getName(),
                account.getAmount(),
                account.getNote(),
                account.getUser().getId(),
                account.getAccountType().getId(),
                account.getCurrency().getId(),
                account.getIcon().getId(),
                account.getIcon().getUrl(),
                account.getAccountType().getName(),
                account.getCurrency().getUnit()+' '+ account.getCurrency().getName());
    }

    private Account mapToEntity(AccountsDto accountsDto) {
        Account account = new Account();
        account.setId(accountsDto.getId());
        account.setName(accountsDto.getName());
        account.setAmount(accountsDto.getAmount());
        account.setNote(accountsDto.getNote());
        account.setUser(userRepository.findById(accountsDto.getUserId()).orElseThrow(()->new ResourceNotFoundException("Not found user")));
        account.setAccountType(accountTypeRepository.findById(accountsDto.getAccountTypeId()).orElseThrow(()->new ResourceNotFoundException("Not found accountType")));
        account.setCurrency(currencyRepository.findById(accountsDto.getCurrencyId()).orElseThrow(()->new ResourceNotFoundException("Not found currency")));
        account.setIcon(iconRepository.findById(accountsDto.getIconId()).orElseThrow(()->new ResourceNotFoundException("Not found icon")));
        return account;
    }

    public AccountReportResponse getResponseAccountByUsername() {
        User user = userRepository.findByUsername(usersService.getCurrentUsername()).orElseThrow(()->new ResourceNotFoundException("Not found user or user didn't login"));
        return getResponseAccountByUserId(user.getId());
    }
}
