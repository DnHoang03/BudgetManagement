package com.web.springmvc.budgetmanagement.service;

import com.web.springmvc.budgetmanagement.dto.BudgetPerCategoryDto;
import com.web.springmvc.budgetmanagement.model.BudgetPerCategory;
import com.web.springmvc.budgetmanagement.repository.BudgetPerCategoryRepository;
import com.web.springmvc.budgetmanagement.repository.IconNotesRepository;
import com.web.springmvc.budgetmanagement.repository.IconsRepository;
import com.web.springmvc.budgetmanagement.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetPerCategoryService {
    private final BudgetPerCategoryRepository budgetPerCategoryRepository;
    private final UsersRepository usersRepository;
    private final IconNotesRepository iconNotesRepository;
    public List<BudgetPerCategoryDto> getAll() {
        return budgetPerCategoryRepository.findAll().stream().map(this::mapToDto).toList();
    }

    public BudgetPerCategoryDto updateBudget(BudgetPerCategoryDto budgetPerCategoryDto) {
        budgetPerCategoryRepository.save(mapToEntity(budgetPerCategoryDto));
        return budgetPerCategoryDto;
    }



    private BudgetPerCategory mapToEntity(BudgetPerCategoryDto budgetPerCategory) {
        return new BudgetPerCategory(budgetPerCategory.getId(),
                budgetPerCategory.getAmount(),
                usersRepository.findById(budgetPerCategory.getUserId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found user")),
                iconNotesRepository.findById(budgetPerCategory.getIconNotesId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found icon")));
    }
    private BudgetPerCategoryDto mapToDto(BudgetPerCategory budgetPerCategory) {
        return new BudgetPerCategoryDto(budgetPerCategory.getId(),
                budgetPerCategory.getAmount(),
                budgetPerCategory.getUser().getId(),
                budgetPerCategory.getIconNotes().getId());
    }
}
