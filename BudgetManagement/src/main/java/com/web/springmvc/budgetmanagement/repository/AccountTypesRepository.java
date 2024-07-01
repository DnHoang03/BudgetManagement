package com.web.springmvc.budgetmanagement.repository;

import com.web.springmvc.budgetmanagement.model.AccountTypes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypesRepository extends JpaRepository<AccountTypes, Long> {
}
