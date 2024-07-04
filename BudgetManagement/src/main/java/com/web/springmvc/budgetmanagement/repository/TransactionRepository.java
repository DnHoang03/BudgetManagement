package com.web.springmvc.budgetmanagement.repository;

import com.web.springmvc.budgetmanagement.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByTransferAccountId(Long id);

    @Query("select c from Transaction c where c.transactionType = :type")
    List<Transaction> findByType(String type);

    @Query("select c from Transaction c where c.transactionType = :type and c.transferAccount.id = :id")
    List<Transaction> findByTypeAndTransferAccountId(String type, Long id);

    @Query("select c from Transaction c where extract(month from c.createdAt) = :month and extract(year from c.createdAt) = :year ")
    List<Transaction> findByCreatedAt(int month, int year);

    @Query("select c from Transaction c where c.transactionType = :type and extract(month from c.createdAt) = :month and extract(year from c.createdAt) = :year ")
    List<Transaction> findByTypeAndCreatedAt(String type, int month, int year);
    @Query("select c from Transaction c where c.transferAccount.id = :id and extract(month from c.createdAt) = :month and extract(year from c.createdAt) = :year ")
    List<Transaction> findByTransferAccountIdAndCreatedAt(Long id, int month, int year);
    @Query("select c from Transaction c where c.transactionType = :type and c.transferAccount.id = :id and extract(month from c.createdAt) = :month and extract(year from c.createdAt) = :year ")
    List<Transaction> findByTypeAndTransferAccountIdAndCreatedAt(String type, Long id, int month, int year);

}
