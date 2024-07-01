package com.web.springmvc.budgetmanagement.controller;

import com.web.springmvc.budgetmanagement.dto.AccountNotesDto;
import com.web.springmvc.budgetmanagement.service.AccountNotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account-notes")
@RequiredArgsConstructor
public class AccountNotesController {

    private final AccountNotesService accountNotesService;

    @GetMapping
    public ResponseEntity<List<AccountNotesDto>> getAllAccountNotes() {
        return ResponseEntity.ok(accountNotesService.getAllAccountNotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountNotesDto> getAccountNoteById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(accountNotesService.getAccountNoteById(id));
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<List<AccountNotesDto>> getAccountNoteByAccountId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(accountNotesService.getAccountNotesByAccountId(id));
    }

    @PostMapping
    public ResponseEntity<AccountNotesDto> createAccountNote(@RequestBody AccountNotesDto accountNotesDto) {
        return new ResponseEntity<>(accountNotesService.createAccountNote(accountNotesDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountNotesDto> updateAccountNote(@RequestBody AccountNotesDto accountNotesDto) {
        return ResponseEntity.ok(accountNotesService.updateAccountNote(accountNotesDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountNote(@PathVariable("id") Long id) {
        accountNotesService.deleteAccountNote(id);
        return ResponseEntity.noContent().build();
    }
}
