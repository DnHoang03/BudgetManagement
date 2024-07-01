package com.web.springmvc.budgetmanagement.service;

import com.web.springmvc.budgetmanagement.dto.AccountNotesDto;
import com.web.springmvc.budgetmanagement.model.AccountNotes;
import com.web.springmvc.budgetmanagement.model.IconNotes;
import com.web.springmvc.budgetmanagement.repository.AccountNotesRepository;
import com.web.springmvc.budgetmanagement.repository.AccountsRepository;
import com.web.springmvc.budgetmanagement.repository.IconNotesRepository;
import com.web.springmvc.budgetmanagement.repository.IconsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountNotesService {
    private final AccountNotesRepository accountNotesRepository;
    private final AccountsRepository accountsRepository;
    private final IconNotesRepository iconNotesRepository;

    public AccountNotesDto createAccountNote(AccountNotesDto accountNotesDto) {
        return mapToDto(accountNotesRepository.save(mapToEntity(accountNotesDto)));
    }

    public List<AccountNotesDto> getAllAccountNotes() {
        return accountNotesRepository.findAll().stream().map(this::mapToDto).toList();
    }

    public AccountNotesDto getAccountNoteById(Long id) {
        return mapToDto(accountNotesRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found account")));
    }

    public List<AccountNotesDto> getAccountNotesByAccountId(Long id) {
        return accountNotesRepository.findByAccountId(id).stream().map(this::mapToDto).toList();
    }

    public AccountNotesDto updateAccountNote(AccountNotesDto accountNotesDto, Long id) {
        AccountNotes accountNotes = accountNotesRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found accountnote"));
        accountNotes.setNote(accountNotesDto.getNote());
        accountNotes.setAmount(accountNotesDto.getAmount());
        accountNotes.setImageUrl(accountNotesDto.getImageUrl());
        accountNotes.setIconNotes(iconNotesRepository.findById(accountNotesDto.getIconNotesId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found iconnote")));
        accountNotes.setAccount(accountsRepository.findById(accountNotesDto.getAccountId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found account")));
        return mapToDto(accountNotesRepository.save(mapToEntity(accountNotesDto)));
    }

    public void deleteAccountNote(Long id) {
        accountNotesRepository.deleteById(id);
    }

    private AccountNotesDto mapToDto(AccountNotes accountNotes) {
        return new AccountNotesDto(accountNotes.getId(),
                accountNotes.getAmount(),
                accountNotes.getCreatedAt(),
                accountNotes.getNote(),
                accountNotes.getImageUrl(),
                accountNotes.getIconNotes().getId(),
                accountNotes.getAccount().getId());
    }

    private AccountNotes mapToEntity(AccountNotesDto accountNotesDto) {
        return new AccountNotes(accountNotesDto.getId(),
                accountNotesDto.getAmount(),
                accountNotesDto.getCreatedAt(),
                accountNotesDto.getNote(),
                accountNotesDto.getImageUrl(),
                iconNotesRepository.findById(accountNotesDto.getIconNotesId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found icon notes")),
                accountsRepository.findById(accountNotesDto.getAccountId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found account")));
    }
}
