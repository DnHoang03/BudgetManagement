package com.web.springmvc.budgetmanagement.controller;

import com.web.springmvc.budgetmanagement.dto.AccountsDto;
import com.web.springmvc.budgetmanagement.model.Accounts;
import com.web.springmvc.budgetmanagement.service.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountsController {
    private final AccountsService accountsService;

    @GetMapping
    public ResponseEntity<List<AccountsDto>> getAllAccounts() {
        return ResponseEntity.ok(accountsService.getAllAccountsByUserId(1L));
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
