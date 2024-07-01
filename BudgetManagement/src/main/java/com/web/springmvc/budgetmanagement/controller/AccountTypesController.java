package com.web.springmvc.budgetmanagement.controller;

import com.web.springmvc.budgetmanagement.dto.AccountTypesDto;
import com.web.springmvc.budgetmanagement.service.AccountTypesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account-types")
public class AccountTypesController {

    private final AccountTypesService accountTypesService;

    @GetMapping()
    public ResponseEntity<List<AccountTypesDto>> getAllAccountTypes() {
        return ResponseEntity.ok(accountTypesService.getAllAccountTypes());
    }

}
