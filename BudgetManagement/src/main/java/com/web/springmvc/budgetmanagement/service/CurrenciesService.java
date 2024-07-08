package com.web.springmvc.budgetmanagement.service;

import com.web.springmvc.budgetmanagement.dto.CurrenciesDto;
import com.web.springmvc.budgetmanagement.exception.ResourceNotFoundException;
import com.web.springmvc.budgetmanagement.model.Currency;
import com.web.springmvc.budgetmanagement.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrenciesService {
    private final CurrencyRepository currencyRepository;

    public List<CurrenciesDto> getAllCurrencies() {
        return currencyRepository.findAll().stream().map(this::mapToDto).toList();
    }

    public CurrenciesDto getCurrencyById(Long id) {
        return mapToDto(currencyRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found currency")));
    }

    private CurrenciesDto mapToDto(Currency currency) {
        return new CurrenciesDto(currency.getId(), currency.getName(), currency.getUnit());
    }
}
