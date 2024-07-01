package com.web.springmvc.budgetmanagement.repository;

import com.web.springmvc.budgetmanagement.model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface NotesRepository extends JpaRepository<Notes, Long> {

    @Query("select c from Notes c order by c.createdAt desc")
    List<Notes> findAll();
}
