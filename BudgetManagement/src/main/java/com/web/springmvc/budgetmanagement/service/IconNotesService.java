package com.web.springmvc.budgetmanagement.service;

import com.web.springmvc.budgetmanagement.dto.IconNotesDto;
import com.web.springmvc.budgetmanagement.model.IconNote;
import com.web.springmvc.budgetmanagement.model.IconNoteType;
import com.web.springmvc.budgetmanagement.repository.IconNoteRepository;
import com.web.springmvc.budgetmanagement.repository.IconRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IconNotesService {
    private final IconNoteRepository iconNoteRepository;
    private final IconRepository iconRepository;

    public IconNotesDto createIconNote(IconNotesDto iconNotesDto) {
        return mapToDto(iconNoteRepository.save(mapToEntity(iconNotesDto)));
    }

    public List<IconNotesDto> getAllIconNotes() {
        return iconNoteRepository.findAll().stream().map(this::mapToDto).toList();
    }

    public IconNotesDto getIconNoteById(Long id) {
        return mapToDto(iconNoteRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found iconnote")));
    }

    public IconNotesDto updateIconNote(IconNotesDto iconNotesDto, Long id) {
        IconNote iconNote = iconNoteRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found iconnote"));
        iconNote.setName(iconNotesDto.getName());
        iconNote.setIconNoteType(IconNoteType.valueOf(iconNotesDto.getIconNoteType()));
        iconNote.setIcon(iconRepository.findById(iconNotesDto.getIconId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found icon")));
        return mapToDto(iconNoteRepository.save(iconNote));
    }

    public void deleteIconNote(Long id) {
        iconNoteRepository.deleteById(id);
    }

    private IconNotesDto mapToDto(IconNote iconNote) {
        return IconNotesDto
                .builder()
                .id(iconNote.getId())
                .iconId(iconNote.getIcon().getId())
                .iconNoteType(iconNote.getIconNoteType().name())
                .name(iconNote.getName())
                .build();
    }

    private IconNote mapToEntity(IconNotesDto iconNotesDto) {
        return IconNote.builder()
                .id(iconNotesDto.getId())
                .name(iconNotesDto.getName())
                .iconNoteType(IconNoteType.valueOf(iconNotesDto.getIconNoteType()))
                .icon(iconRepository.findById(iconNotesDto.getIconId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found icon")))
                .build();
    }
}
