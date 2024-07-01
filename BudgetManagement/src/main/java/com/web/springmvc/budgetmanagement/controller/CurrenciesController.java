package com.web.springmvc.budgetmanagement.controller;

import com.web.springmvc.budgetmanagement.dto.CurrenciesDto;
import com.web.springmvc.budgetmanagement.service.CurrenciesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/currencies")
public class CurrenciesController {
    private final CurrenciesService currenciesService;

    @GetMapping
    public ResponseEntity<List<CurrenciesDto>> getAllCurrencies() {
        return ResponseEntity.ok(currenciesService.getAllCurrencies());
    }
}
