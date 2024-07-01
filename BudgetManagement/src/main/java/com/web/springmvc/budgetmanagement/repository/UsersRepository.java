package com.web.springmvc.budgetmanagement.repository;

import com.web.springmvc.budgetmanagement.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
