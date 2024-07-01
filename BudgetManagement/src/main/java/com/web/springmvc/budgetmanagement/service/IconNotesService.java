package com.web.springmvc.budgetmanagement.service;

import com.web.springmvc.budgetmanagement.dto.IconNotesDto;
import com.web.springmvc.budgetmanagement.model.IconNotes;
import com.web.springmvc.budgetmanagement.repository.IconNotesRepository;
import com.web.springmvc.budgetmanagement.repository.IconsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IconNotesService {
    private final IconNotesRepository iconNotesRepository;
    private final IconsRepository iconsRepository;

    public IconNotesDto createIconNote(IconNotesDto iconNotesDto) {
        return mapToDto(iconNotesRepository.save(mapToEntity(iconNotesDto)));
    }

    public List<IconNotesDto> getAllIconNotes() {
        return iconNotesRepository.findAll().stream().map(this::mapToDto).toList();
    }

    public IconNotesDto getIconNoteById(Long id) {
        return mapToDto(iconNotesRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found iconnote")));
    }

    public IconNotesDto updateIconNote(IconNotesDto iconNotesDto, Long id) {
        IconNotes iconNotes = iconNotesRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found iconnote"));
        iconNotes.setName(iconNotesDto.getName());
        iconNotes.setIcon(iconsRepository.findById(iconNotesDto.getIconId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found icon")));
        return mapToDto(iconNotesRepository.save(iconNotes));
    }

    public void deleteIconNote(Long id) {
        iconNotesRepository.deleteById(id);
    }

    private IconNotesDto mapToDto(IconNotes iconNotes) {
        return new IconNotesDto(iconNotes.getId(), iconNotes.getName(), iconNotes.getIcon().getId());
    }

    private IconNotes mapToEntity(IconNotesDto iconNotesDto) {
        return IconNotes.builder()
                .id(iconNotesDto.getId())
                .name(iconNotesDto.getName())
                .icon(iconsRepository.findById(iconNotesDto.getIconId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found icon")))
                .build();
    }
}
