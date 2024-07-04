package com.web.springmvc.budgetmanagement.repository;

import com.web.springmvc.budgetmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
