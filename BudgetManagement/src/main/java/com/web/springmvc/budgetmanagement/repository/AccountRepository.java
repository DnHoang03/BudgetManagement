package com.web.springmvc.budgetmanagement.repository;

import com.web.springmvc.budgetmanagement.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserId(Long id);
}
