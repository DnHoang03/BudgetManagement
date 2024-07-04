package com.web.springmvc.budgetmanagement.controller;

import com.web.springmvc.budgetmanagement.dto.AccountReportResponse;
import com.web.springmvc.budgetmanagement.dto.AccountsDto;
import com.web.springmvc.budgetmanagement.service.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountsController {
    private final AccountsService accountsService;

    @GetMapping
    public ResponseEntity<AccountReportResponse> getAllAccounts() {
        return ResponseEntity.ok(accountsService.getResponseAccountByUserId(1L));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountsDto> getAccountById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(accountsService.getAccountById(id));
    }

    @PostMapping
    public ResponseEntity<AccountsDto> createAccount(@RequestBody AccountsDto accountsDto) {
        return new ResponseEntity<>(accountsService.createAccount(accountsDto),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountsDto> updateAccount(@RequestBody AccountsDto accountsDto, @PathVariable("id") Long id) {
        return ResponseEntity.ok(accountsService.updateAccount(accountsDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable("id")Long id) {
        accountsService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
