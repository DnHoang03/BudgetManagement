package com.web.springmvc.budgetmanagement.service;

import com.web.springmvc.budgetmanagement.dto.TransactionsDto;
import com.web.springmvc.budgetmanagement.exception.ResourceNotFoundException;
import com.web.springmvc.budgetmanagement.model.TransactionType;
import com.web.springmvc.budgetmanagement.model.Transaction;
import com.web.springmvc.budgetmanagement.repository.AccountRepository;
import com.web.springmvc.budgetmanagement.repository.IconNoteRepository;
import com.web.springmvc.budgetmanagement.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final IconNoteRepository iconNoteRepository;
    public TransactionsDto createTransaction(TransactionsDto transactionsDto) {
        transactionRepository.save(mapToEntity(transactionsDto));
        return transactionsDto;
    }

    public List<TransactionsDto> getAllTransaction(String type, Integer month, Integer year) {
        if(type == null) {
            if(month == null) {
                return transactionRepository.findAll().stream().map(this::mapToDto).toList();
            } else {
                return transactionRepository.findByCreatedAt(month, year).stream().map(this::mapToDto).toList();
            }
        } else {
            if(month == null) {
                return transactionRepository.findByType(type).stream().map(this::mapToDto).toList();
            } else {
                return transactionRepository.findByTypeAndCreatedAt(type, month, year).stream().map(this::mapToDto).toList();
            }
        }
    }

    public List<TransactionsDto> getAllTransactionByAccountId(Long id, String type, Integer month, Integer year) {
        if(type == null) {
            if(month == null) {
                return transactionRepository.findByTransferAccountId(id).stream().map(this::mapToDto).toList();
            } else {
                return transactionRepository.findByTransferAccountIdAndCreatedAt(id,month, year).stream().map(this::mapToDto).toList();
            }
        } else {
            if(month == null) {
                return transactionRepository.findByTypeAndTransferAccountId(type, id).stream().map(this::mapToDto).toList();
            } else {
                return transactionRepository.findByTypeAndTransferAccountIdAndCreatedAt(type, id, month, year).stream().map(this::mapToDto).toList();
            }
        }
    }

    public List<TransactionsDto> getTransactionsByTransferAccountId(Long id) {
        return transactionRepository.findByTransferAccountId(id).stream().map(this::mapToDto).toList();
    }

    public TransactionsDto updateTransaction(TransactionsDto transactionsDto, Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found transaction"));
        transaction.setAmount(transactionsDto.getAmount());
        transaction.setName(transactionsDto.getName());
        transaction.setIconNote((transactionsDto.getIconNoteId() != null)? iconNoteRepository.findById(transactionsDto.getIconNoteId()).orElseThrow(()->new ResourceNotFoundException("Not found note")):null);
        transaction.setTransferAccount((transactionsDto.getTransferAccountId() != null) ? accountRepository.findById(transactionsDto.getTransferAccountId()).orElseThrow(()->new ResourceNotFoundException("Not found transfer account")):null);
        transaction.setReceiveAccount((transactionsDto.getReceiveAccountId() != null) ? accountRepository.findById(transactionsDto.getReceiveAccountId()).orElseThrow(()->new ResourceNotFoundException("Not found receive account")):null);
        return mapToDto(transactionRepository.save(transaction));
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
    private Transaction mapToEntity(TransactionsDto transactionsDto) {
        return Transaction
                .builder()
                .id(transactionsDto.getId())
                .name(transactionsDto.getName())
                .transactionType(TransactionType.valueOf(transactionsDto.getType()))
                .createdAt(transactionsDto.getCreatedAt())
                .iconNote((transactionsDto.getIconNoteId() != null)? iconNoteRepository.findById(transactionsDto.getIconNoteId()).orElseThrow(()->new ResourceNotFoundException("Not found note")):null)
                .transferAccount((transactionsDto.getTransferAccountId() != null) ? accountRepository.findById(transactionsDto.getTransferAccountId()).orElseThrow(()->new ResourceNotFoundException("Not found transfer account")):null)
                .receiveAccount((transactionsDto.getReceiveAccountId() != null) ? accountRepository.findById(transactionsDto.getReceiveAccountId()).orElseThrow(()->new ResourceNotFoundException("Not found receive account")):null)
                .build();
    }

    private TransactionsDto mapToDto(Transaction transaction) {
        return TransactionsDto
                .builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getTransactionType().name())
                .createdAt(transaction.getCreatedAt())
                .name(transaction.getName())
                .transferAccountId((transaction.getTransferAccount() != null)? transaction.getTransferAccount().getId():null)
                .receiveAccountId((transaction.getReceiveAccount() != null)? transaction.getReceiveAccount().getId():null)
                .iconNoteId((transaction.getIconNote() != null) ? transaction.getIconNote().getId():null)
                .iconNoteName((transaction.getIconNote() != null) ? transaction.getIconNote().getName():null)
                .receiveAccountName((transaction.getReceiveAccount() != null)? transaction.getReceiveAccount().getName():null)
                .transferAccountName((transaction.getTransferAccount() != null)? transaction.getTransferAccount().getName():null)
                .build();
    }
//    public
}
