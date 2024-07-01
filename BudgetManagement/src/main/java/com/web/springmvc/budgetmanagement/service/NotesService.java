package com.web.springmvc.budgetmanagement.service;

import com.web.springmvc.budgetmanagement.dto.NotesDto;
import com.web.springmvc.budgetmanagement.model.Notes;
import com.web.springmvc.budgetmanagement.repository.IconNotesRepository;
import com.web.springmvc.budgetmanagement.repository.IconsRepository;
import com.web.springmvc.budgetmanagement.repository.NotesRepository;
import com.web.springmvc.budgetmanagement.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotesService {
    private final NotesRepository notesRepository;
    private final IconNotesRepository iconNotesRepository;
    private final UsersRepository usersRepository;

    public NotesDto createNote(NotesDto notesDto) {
        return mapToDto(notesRepository.save(mapToEntity(notesDto)));
    }

    public List<NotesDto> getAllNotes() {
        return notesRepository.findAll().stream().map(this::mapToDto).toList();
    }

    public NotesDto getNoteById(Long id) {
        return mapToDto(notesRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found note")));
    }

    public NotesDto updateNote(NotesDto notesDto) {
        return mapToDto(notesRepository.save(mapToEntity(notesDto)));
    }

    public void deleteNote(Long id) {
        notesRepository.deleteById(id);
    }

    private NotesDto mapToDto(Notes notes) {
        return new NotesDto(notes.getId(),
                notes.getAmount(),
                notes.getCreatedAt(),
                notes.getNote(),
                notes.getImageUrl(),
                notes.getIconNotes().getId(),
                notes.getUser().getId());
    }

    private Notes mapToEntity(NotesDto notesDto) {
        return new Notes(notesDto.getId(),
                notesDto.getAmount(),
                notesDto.getCreatedAt(),
                notesDto.getNote(),
                notesDto.getImageUrl(),
                iconNotesRepository.findById(notesDto.getIconNotesId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found icon")),
                usersRepository.findById(notesDto.getUserId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found user")));
    }
}
