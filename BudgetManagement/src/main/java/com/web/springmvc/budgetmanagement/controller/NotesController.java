package com.web.springmvc.budgetmanagement.controller;

import com.web.springmvc.budgetmanagement.dto.NotesDto;
import com.web.springmvc.budgetmanagement.service.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NotesController {
    private final NotesService notesService;

    @GetMapping()
    public ResponseEntity<List<NotesDto>> getAllNotes() {
        return ResponseEntity.ok(notesService.getAllNotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotesDto> getNote(@PathVariable("id")Long id) {
        return ResponseEntity.ok(notesService.getNoteById(id));
    }

    @PostMapping
    public ResponseEntity<NotesDto> createNote(@RequestBody NotesDto notesDto) {
        return new ResponseEntity<>(notesService.createNote(notesDto),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotesDto> updateNote(@RequestBody NotesDto notesDto) {
        return ResponseEntity.ok(notesService.updateNote(notesDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id")Long id) {
        notesService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}
