package com.web.springmvc.budgetmanagement.repository;

import com.web.springmvc.budgetmanagement.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    List<Accounts> findByUserId(Long id);
}
