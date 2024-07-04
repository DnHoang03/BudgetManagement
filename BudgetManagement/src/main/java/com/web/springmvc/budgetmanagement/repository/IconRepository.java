package com.web.springmvc.budgetmanagement.repository;

import com.web.springmvc.budgetmanagement.model.Icon;
import com.web.springmvc.budgetmanagement.model.IconType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IconRepository extends JpaRepository<Icon, Long> {

    List<Icon> findByIconCategoryId(Long id);

    List<Icon> findByType(IconType type);

    List<Icon> findByTypeAndIconCategoryId(IconType type, Long iconCategory_id);
}
