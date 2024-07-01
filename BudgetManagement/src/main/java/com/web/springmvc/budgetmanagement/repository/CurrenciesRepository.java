package com.web.springmvc.budgetmanagement.repository;

import com.web.springmvc.budgetmanagement.model.Currencies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrenciesRepository extends JpaRepository<Currencies, Long> {
}
