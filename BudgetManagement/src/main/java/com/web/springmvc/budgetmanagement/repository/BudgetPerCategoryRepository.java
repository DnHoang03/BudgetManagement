package com.web.springmvc.budgetmanagement.repository;

import com.web.springmvc.budgetmanagement.model.BudgetPerCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetPerCategoryRepository extends JpaRepository<BudgetPerCategory, Long> {

}
