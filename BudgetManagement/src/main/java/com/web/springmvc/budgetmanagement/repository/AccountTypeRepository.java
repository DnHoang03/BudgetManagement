package com.web.springmvc.budgetmanagement.repository;

import com.web.springmvc.budgetmanagement.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
}
