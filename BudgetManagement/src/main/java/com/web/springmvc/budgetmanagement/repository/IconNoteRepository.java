package com.web.springmvc.budgetmanagement.repository;

import com.web.springmvc.budgetmanagement.model.IconNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IconNoteRepository extends JpaRepository<IconNote, Long> {
    List<IconNote> findByUserId(Long id);
}
