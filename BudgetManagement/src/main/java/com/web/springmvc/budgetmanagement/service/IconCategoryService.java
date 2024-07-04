package com.web.springmvc.budgetmanagement.service;

import com.web.springmvc.budgetmanagement.dto.IconCategoryDto;
import com.web.springmvc.budgetmanagement.model.IconCategory;
import com.web.springmvc.budgetmanagement.repository.IconCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IconCategoryService {
    private final IconCategoryRepository iconCategoryRepository;

    public List<IconCategoryDto> getAllIconcategory() {
        return iconCategoryRepository.findAll().stream().map(this::mapToDto).toList();
    }

    private IconCategoryDto mapToDto(IconCategory iconCategory) {
        return IconCategoryDto
                .builder()
                .id(iconCategory.getId())
                .name(iconCategory.getName())
                .build();
    }
}
