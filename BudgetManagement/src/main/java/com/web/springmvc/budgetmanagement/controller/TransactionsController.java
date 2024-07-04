package com.web.springmvc.budgetmanagement.controller;

import com.web.springmvc.budgetmanagement.dto.TransactionsDto;
import com.web.springmvc.budgetmanagement.service.TransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionsController {
    private final TransactionsService transactionsService;

    @GetMapping()
    public ResponseEntity<List<TransactionsDto>> getTransactionByAccountId(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "accountId", required = false) Long accountId,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "year", required = false) Integer year) {
        if(accountId == null) {
            return ResponseEntity.ok(transactionsService.getAllTransaction(type, month, year));
        } else {
            return ResponseEntity.ok(transactionsService.getAllTransactionByAccountId(accountId, type, month, year));
        }
    }

    @PostMapping()
    public ResponseEntity<TransactionsDto> createTransaction(@RequestBody TransactionsDto transactionsDto) {
        return ResponseEntity.ok(transactionsService.createTransaction(transactionsDto));
    }
}
