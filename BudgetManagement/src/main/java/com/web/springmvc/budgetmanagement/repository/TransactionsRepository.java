package com.web.springmvc.budgetmanagement.repository;

import com.web.springmvc.budgetmanagement.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
    List<Transactions> findByTransferAccountId(Long id);
}
