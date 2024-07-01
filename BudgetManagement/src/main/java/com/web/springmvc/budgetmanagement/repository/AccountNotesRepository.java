package com.web.springmvc.budgetmanagement.repository;

import com.web.springmvc.budgetmanagement.model.AccountNotes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountNotesRepository extends JpaRepository<AccountNotes, Long> {
    List<AccountNotes> findByAccountId(Long id);
}
