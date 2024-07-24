package com.web.springmvc.budgetmanagement.service;

import com.web.springmvc.budgetmanagement.dto.StatisticResponse;
import com.web.springmvc.budgetmanagement.dto.TransactionResponse;
import com.web.springmvc.budgetmanagement.dto.TransactionsDto;
import com.web.springmvc.budgetmanagement.exception.ResourceNotFoundException;
import com.web.springmvc.budgetmanagement.model.Account;
import com.web.springmvc.budgetmanagement.model.Transaction;
import com.web.springmvc.budgetmanagement.model.TransactionType;
import com.web.springmvc.budgetmanagement.model.User;
import com.web.springmvc.budgetmanagement.repository.AccountRepository;
import com.web.springmvc.budgetmanagement.repository.IconNoteRepository;
import com.web.springmvc.budgetmanagement.repository.TransactionRepository;
import com.web.springmvc.budgetmanagement.repository.UserRepository;
import com.web.springmvc.budgetmanagement.util.CurrencyUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final IconNoteRepository iconNoteRepository;
    private final UsersService usersService;
    private final UserRepository userRepository;
    private final AccountsService accountsService;

    public TransactionsDto createTransaction(TransactionsDto transactionsDto) {
        User user = userRepository
                .findByUsername(usersService.getCurrentUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Not found user"));
        if (transactionsDto.getTransferAccountId() != null && transactionsDto.getReceiveAccountId() == null) {
            Account transferAccount = accountRepository
                    .findById(transactionsDto.getTransferAccountId())
                    .orElseThrow(() -> new ResourceNotFoundException("Not found transfer account"));
            long amount = transactionsDto.getAmount();
            if (transferAccount
                    .getAccountType()
                    .getName()
                    .equals("Tôi nợ/ Tài khoản phải trả")
                    || transferAccount
                    .getAccountType()
                    .getName()
                    .equals("Thẻ tín dụng")) {
                if (!transactionsDto
                        .getType()
                        .equals(TransactionType.COST.name())) {
                    amount = -amount;
                }
            }
            if (transactionsDto
                    .getType()
                    .equals(TransactionType.COST.name())) {
                transferAccount.setAmount(transferAccount.getAmount() - amount);
            } else {
                transferAccount.setAmount(transferAccount.getAmount() + amount);
            }
            accountRepository.save(transferAccount);
        } else if (transactionsDto.getReceiveAccountId() != null) {
            assert transactionsDto.getTransferAccountId() != null;
            Account transferAccount = accountRepository
                    .findById(transactionsDto.getTransferAccountId())
                    .orElseThrow(() -> new ResourceNotFoundException("Not found transfer account"));
            Account receiveAccount = accountRepository
                    .findById(transactionsDto.getReceiveAccountId())
                    .orElseThrow(() -> new ResourceNotFoundException("Not found receive account"));
            transactionsDto.setReceiveAmount(CurrencyUtil.transform(transactionsDto.getAmount()
                    , transferAccount
                                                                            .getCurrency()
                                                                            .getUnit(), receiveAccount
                                                                            .getCurrency()
                                                                            .getUnit()));
            long transferAmount = transactionsDto.getAmount();
            long receiveAmount = transactionsDto.getReceiveAmount();
            if (transferAccount
                    .getAccountType()
                    .getName()
                    .equals("Tôi nợ/ Tài khoản phải trả")
                    || transferAccount
                    .getAccountType()
                    .getName()
                    .equals("Thẻ tín dụng")) {
                transferAmount = -transferAmount;
            }
            if (receiveAccount
                    .getAccountType()
                    .getName()
                    .equals("Tôi nợ/ Tài khoản phải trả")
                    || receiveAccount
                    .getAccountType()
                    .getName()
                    .equals("Thẻ tín dụng")) {
                receiveAmount = -receiveAmount;
            }
            transferAccount.setAmount(transferAccount.getAmount() - transferAmount);
            receiveAccount.setAmount(receiveAccount.getAmount() + receiveAmount);
            accountRepository.save(transferAccount);
            accountRepository.save(receiveAccount);
        }
        transactionRepository.save(mapToEntity(transactionsDto));
        return transactionsDto;
    }

    public List<TransactionsDto> getAllTransaction(String type, Integer month, Integer year) {
        User user = userRepository
                .findByUsername(usersService.getCurrentUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Not found user"));
        if (type == null) {
            if (month == null) {
                return transactionRepository
                        .findByUserId(user.getId())
                        .stream()
                        .map(this::mapToDto)
                        .toList();
            } else {
                return transactionRepository
                        .findByCreatedAt(month, year, user.getId())
                        .stream()
                        .map(this::mapToDto)
                        .toList();
            }
        } else {
            if (month == null) {
                return transactionRepository
                        .findByType(type, user.getId())
                        .stream()
                        .map(this::mapToDto)
                        .toList();
            } else {
                return transactionRepository
                        .findByTypeAndCreatedAt(type, month, year, user.getId())
                        .stream()
                        .map(this::mapToDto)
                        .toList();
            }
        }
    }

    public List<TransactionsDto> getAllTransactionByAccountId(Long id, String type, Integer month, Integer year) {
        User user = userRepository
                .findByUsername(usersService.getCurrentUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Not found user"));
        if (type == null) {
            if (month == null) {
                return transactionRepository
                        .findByTransferAccountIdAndUserId(id, user.getId())
                        .stream()
                        .map(this::mapToDto)
                        .toList();
            } else {
                return transactionRepository
                        .findByTransferAccountIdAndCreatedAt(id, month, year, user.getId())
                        .stream()
                        .map(this::mapToDto)
                        .toList();
            }
        } else {
            if (month == null) {
                return transactionRepository
                        .findByTypeAndTransferAccountId(type, id, user.getId())
                        .stream()
                        .map(this::mapToDto)
                        .toList();
            } else {
                return transactionRepository
                        .findByTypeAndTransferAccountIdAndCreatedAt(type, id, month, year, user.getId())
                        .stream()
                        .map(this::mapToDto)
                        .toList();
            }
        }
    }

    public StatisticResponse getStatisticResponse(Integer month, Integer year) {
        User user = userRepository
                .findByUsername(usersService.getCurrentUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Not found user"));
        List<Transaction> transactions = transactionRepository.findByCreatedAt(month, year, user.getId());
        long cost = 0, income = 0;
        for (Transaction transaction : transactions) {
            if (!transaction
                    .getTransactionType()
                    .equals(TransactionType.TRANSFER)) {
                if (transaction
                        .getTransactionType()
                        .equals(TransactionType.COST)) {
                    cost += transaction.getAmount();
                } else {
                    income += transaction.getAmount();
                }
            }
        }
        return StatisticResponse
                .builder()
                .income(income)
                .cost(cost)
                .build();
    }

    public List<TransactionResponse> getTransactionResponse(Integer month, Integer year) {
        User user = userRepository
                .findByUsername(usersService.getCurrentUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Not found user"));
        List<Transaction> transactions = transactionRepository.findByCreatedAt(month, year, user.getId());
        LocalDateTime dateTime = LocalDateTime.now();
        List<TransactionsDto> currentTransactions = new ArrayList<>();
        List<TransactionResponse> responses = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction
                    .getCreatedAt()
                    .getDayOfMonth() != dateTime.getDayOfMonth()) {
                if (!currentTransactions.isEmpty()) {
                    responses.add(
                            TransactionResponse
                                    .builder()
                                    .date(dateTime)
                                    .transactions(new ArrayList<>(currentTransactions))
                                    .build()
                    );
                    currentTransactions.clear();
                }
                currentTransactions.add(mapToDto(transaction));
                dateTime = transaction.getCreatedAt();
            } else {
                currentTransactions.add(mapToDto(transaction));
            }
        }
        if (!currentTransactions.isEmpty()) {
            responses.add(
                    TransactionResponse
                            .builder()
                            .date(dateTime)
                            .transactions(new ArrayList<>(currentTransactions))
                            .build()
            );
        }
        for (int i = 0; i < responses.size(); i++) {
            long income = 0, cost = 0;
            for (int j = 0; j < responses
                    .get(i)
                    .getTransactions()
                    .size(); j++) {
                TransactionsDto transactionsDto = responses
                        .get(i)
                        .getTransactions()
                        .get(j);
                if (transactionsDto
                        .getType()
                        .equals(TransactionType.COST.name())) {
                    cost += transactionsDto.getAmount();
                } else if (transactionsDto
                        .getType()
                        .equals(TransactionType.INCOME.name())) {
                    income += transactionsDto.getAmount();
                }
            }
            TransactionResponse transactionResponse = responses.get(i);
            transactionResponse.setCost(cost);
            transactionResponse.setIncome(income);
            responses.set(i, transactionResponse);
        }
        return responses;
    }

    public List<TransactionsDto> getTransactionsByTransferAccountId(Long id) {
        User user = userRepository
                .findByUsername(usersService.getCurrentUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Not found user"));
        return transactionRepository
                .findByTransferAccountIdAndUserId(id, user.getId())
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public TransactionsDto updateTransaction(TransactionsDto transactionsDto, Long id) {
        Transaction transaction = transactionRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found transaction"));
        transaction.setAmount(transactionsDto.getAmount());
        transaction.setName(transactionsDto.getName());
        transaction.setIconNote((transactionsDto.getIconNoteId() !=
                null) ? iconNoteRepository
                .findById(transactionsDto.getIconNoteId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found note")) : null);
        transaction.setTransferAccount((transactionsDto.getTransferAccountId() !=
                null) ? accountRepository
                .findById(transactionsDto.getTransferAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found transfer account")) : null);
        transaction.setReceiveAccount((transactionsDto.getReceiveAccountId() !=
                null) ? accountRepository
                .findById(transactionsDto.getReceiveAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Not found receive account")) : null);
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
                .amount(transactionsDto.getAmount())
                .receiveAmount(transactionsDto.getReceiveAmount())
                .iconNote((transactionsDto.getIconNoteId() != null) ? iconNoteRepository
                        .findById(transactionsDto.getIconNoteId())
                        .orElseThrow(() -> new ResourceNotFoundException("Not found note")) : null)
                .transferAccount((transactionsDto.getTransferAccountId() != null) ? accountRepository
                        .findById(transactionsDto.getTransferAccountId())
                        .orElseThrow(() -> new ResourceNotFoundException("Not found transfer account")) : null)
                .receiveAccount((transactionsDto.getReceiveAccountId() != null) ? accountRepository
                        .findById(transactionsDto.getReceiveAccountId())
                        .orElseThrow(() -> new ResourceNotFoundException("Not found receive account")) : null)
                .user(userRepository
                              .findByUsername(usersService.getCurrentUsername())
                              .orElseThrow(() -> new ResourceNotFoundException("Not found user")))
                .build();
    }

    private TransactionsDto mapToDto(Transaction transaction) {
        return TransactionsDto
                .builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .receiveAmount(transaction.getReceiveAmount())
                .type(transaction
                              .getTransactionType()
                              .name())
                .createdAt(transaction.getCreatedAt())
                .name(transaction.getName())
                .transferAccountId((transaction.getTransferAccount() != null) ? transaction
                        .getTransferAccount()
                        .getId() : null)
                .receiveAccountId((transaction.getReceiveAccount() != null) ? transaction
                        .getReceiveAccount()
                        .getId() : null)
                .iconNoteId((transaction.getIconNote() != null) ? transaction
                        .getIconNote()
                        .getId() : null)
                .iconNoteName((transaction.getIconNote() != null) ? transaction
                        .getIconNote()
                        .getName() : null)
                .receiveAccountName((transaction.getReceiveAccount() != null) ? transaction
                        .getReceiveAccount()
                        .getName() : null)
                .transferAccountName((transaction.getTransferAccount() != null) ? transaction
                        .getTransferAccount()
                        .getName() : null)
                .iconName((transaction.getIconNote() != null) ? (transaction
                        .getIconNote()
                        .getIcon()
                        .getUrl()) : null)
                .build();
    }

    public TransactionsDto getTransactionById(Long id) {
        return mapToDto(transactionRepository
                                .findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Not found transactions")));
    }
//    public
}
