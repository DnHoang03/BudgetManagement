package com.web.springmvc.budgetmanagement.service;

import com.web.springmvc.budgetmanagement.dto.TransactionsDto;
import com.web.springmvc.budgetmanagement.model.Transactions;
import com.web.springmvc.budgetmanagement.repository.AccountsRepository;
import com.web.springmvc.budgetmanagement.repository.TransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsService {
    private final TransactionsRepository transactionsRepository;
    private final AccountsRepository accountsRepository;
    public TransactionsDto createTransaction(TransactionsDto transactionsDto) {
        transactionsRepository.save(mapToEntity(transactionsDto));
        return transactionsDto;
    }

    public List<TransactionsDto> getAllTransaction() {
        return transactionsRepository.findAll().stream().map(this::mapToDto).toList();
    }

    public List<TransactionsDto> getTransactionsByTransferAccountId(Long id) {
        return transactionsRepository.findByTransferAccountId(id).stream().map(this::mapToDto).toList();
    }

    public TransactionsDto updateTransaction(TransactionsDto transactionsDto) {
        return mapToDto(transactionsRepository.save(mapToEntity(transactionsDto)));
    }

    public void deleteTransaction(Long id) {
        transactionsRepository.deleteById(id);
    }
    private Transactions mapToEntity(TransactionsDto transactionsDto) {
        return new Transactions(transactionsDto.getId(),
                transactionsDto.getAmount(),
                transactionsDto.getCreatedAt(),
                accountsRepository.findById(transactionsDto.getTransferAccountId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found tranfer account")),
                accountsRepository.findById(transactionsDto.getReceiveAccountId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found receive account")));
    }

    private TransactionsDto mapToDto(Transactions transactions) {
        return new TransactionsDto(transactions.getId(),
                transactions.getAmount(),
                transactions.getCreatedAt(),
                transactions.getTransferAccount().getId(),
                transactions.getReceiveAccount().getId());
    }
//    public
}
