package com.web.springmvc.budgetmanagement.repository;

import com.web.springmvc.budgetmanagement.model.IconTypes;
import com.web.springmvc.budgetmanagement.model.Icons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IconsRepository extends JpaRepository<Icons, Long> {

    List<Icons> findByIconCategoryId(Long id);

    List<Icons> findByType(IconTypes type);

    List<Icons> findByTypeAndIconCategoryId(IconTypes type, Long iconCategory_id);
}
