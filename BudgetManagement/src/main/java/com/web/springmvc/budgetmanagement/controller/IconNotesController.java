package com.web.springmvc.budgetmanagement.controller;

import com.web.springmvc.budgetmanagement.dto.IconNotesDto;
import com.web.springmvc.budgetmanagement.service.IconNotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/icon-notes")
@RequiredArgsConstructor
public class IconNotesController {
    private final IconNotesService iconNotesService;

    @GetMapping
    public ResponseEntity<List<IconNotesDto>> getAllIconNotes() {
        return ResponseEntity.ok(iconNotesService.getAllIconNotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IconNotesDto> getIconNoteById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(iconNotesService.getIconNoteById(id));
    }

    @PostMapping()
    public ResponseEntity<IconNotesDto> createIconNote(@RequestBody IconNotesDto iconNotesDto) {
        return new ResponseEntity<IconNotesDto>(iconNotesDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IconNotesDto> updateIconNote(@RequestBody IconNotesDto iconNotesDto, @PathVariable("id") Long id) {
        return ResponseEntity.ok(iconNotesService.updateIconNote(iconNotesDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIconNote(@PathVariable("id") Long id) {
        iconNotesService.deleteIconNote(id);
        return ResponseEntity.noContent().build();
    }
}
