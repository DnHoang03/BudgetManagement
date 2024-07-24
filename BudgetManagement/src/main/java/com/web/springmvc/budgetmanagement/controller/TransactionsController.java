package com.web.springmvc.budgetmanagement.controller;

import com.web.springmvc.budgetmanagement.dto.StatisticResponse;
import com.web.springmvc.budgetmanagement.dto.TransactionResponse;
import com.web.springmvc.budgetmanagement.dto.TransactionsDto;
import com.web.springmvc.budgetmanagement.service.TransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        if (accountId == null) {
            return ResponseEntity.ok(transactionsService.getAllTransaction(type, month, year));
        } else {
            return ResponseEntity.ok(transactionsService.getAllTransactionByAccountId(accountId, type, month, year));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionsDto> getTransactionById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(transactionsService.getTransactionById(id));
    }

    @GetMapping("/statistic")
    public ResponseEntity<StatisticResponse> getStatisticResponse(
            @RequestParam(value = "month", required = true) Integer month,
            @RequestParam(value = "year", required = true) Integer year
    ) {
        return ResponseEntity.ok(transactionsService.getStatisticResponse(month, year));
    }

    @GetMapping("/home")
    public ResponseEntity<List<TransactionResponse>> getTransactionResponses(
            @RequestParam(value = "month", required = true) Integer month,
            @RequestParam(value = "year", required = true) Integer year
    ) {
        return ResponseEntity.ok(transactionsService.getTransactionResponse(month, year));
    }

    @PostMapping()
    public ResponseEntity<TransactionsDto> createTransaction(@RequestBody TransactionsDto transactionsDto) {
        return new ResponseEntity<>(transactionsService.createTransaction(transactionsDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionsDto> updateTransaction(
            @RequestBody TransactionsDto transactionsDto,
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(transactionsService.updateTransaction(transactionsDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable("id")Long id) {
        transactionsService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}
