package com.web.springmvc.budgetmanagement.repository;

import com.web.springmvc.budgetmanagement.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
