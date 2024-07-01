package com.web.springmvc.budgetmanagement.service;

import com.web.springmvc.budgetmanagement.dto.CurrenciesDto;
import com.web.springmvc.budgetmanagement.model.Currencies;
import com.web.springmvc.budgetmanagement.repository.CurrenciesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrenciesService {
    private final CurrenciesRepository currenciesRepository;

    public List<CurrenciesDto> getAllCurrencies() {
        return currenciesRepository.findAll().stream().map(this::mapToDto).toList();
    }

    private CurrenciesDto mapToDto(Currencies currencies) {
        return new CurrenciesDto(currencies.getId(), currencies.getName(), currencies.getUnit());
    }
}
