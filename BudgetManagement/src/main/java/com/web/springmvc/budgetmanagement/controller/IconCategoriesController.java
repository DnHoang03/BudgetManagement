package com.web.springmvc.budgetmanagement.controller;

import com.web.springmvc.budgetmanagement.dto.IconCategoryDto;
import com.web.springmvc.budgetmanagement.service.IconCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/icon-categories")
@RequiredArgsConstructor
public class IconCategoriesController {
    private final IconCategoryService iconCategoryService;

    @GetMapping
    public ResponseEntity<List<IconCategoryDto>> getAllIconCategory() {
        return ResponseEntity.ok(iconCategoryService.getAllIconcategory());
    }
}
