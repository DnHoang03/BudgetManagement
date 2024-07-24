package com.web.springmvc.budgetmanagement.repository;

import com.web.springmvc.budgetmanagement.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByTransferAccountIdAndUserId(Long accountId, Long userId);

    @Query("select c from Transaction c where c.transactionType = :type and c.user.id = :id")
    List<Transaction> findByType(String type, Long id);

    @Query("select c from Transaction c where c.transactionType = :type and (c.transferAccount.id = :id or c.receiveAccount.id = :id) and c.user.id = :userId")
    List<Transaction> findByTypeAndTransferAccountId(String type, Long id, Long userId);

    @Query("select c from Transaction c where extract(month from c.createdAt) = :month and extract(year from c.createdAt) = :year and c.user.id = :id order by c.createdAt desc ")
    List<Transaction> findByCreatedAt(int month, int year, Long id);

    @Query("select c from Transaction c where c.transactionType = :type and extract(month from c.createdAt) = :month and extract(year from c.createdAt) = :year and c.user.id = :id")
    List<Transaction> findByTypeAndCreatedAt(String type, int month, int year, Long id);
    @Query("select c from Transaction c where (c.transferAccount.id = :id or c.receiveAccount.id = :id) and extract(month from c.createdAt) = :month and extract(year from c.createdAt) = :year and c.user.id = :userId order by c.createdAt desc ")
    List<Transaction> findByTransferAccountIdAndCreatedAt(Long id, int month, int year, Long userId);
    @Query("select c from Transaction c where c.transactionType = :type and c.transferAccount.id = :id and extract(month from c.createdAt) = :month and extract(year from c.createdAt) = :year and c.user.id = :userId")
    List<Transaction> findByTypeAndTransferAccountIdAndCreatedAt(String type, Long id, int month, int year, Long userId);

    List<Transaction> findByUserId(Long id);
}
