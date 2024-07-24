package com.web.springmvc.budgetmanagement.service;

import com.web.springmvc.budgetmanagement.dto.IconNotesDto;
import com.web.springmvc.budgetmanagement.exception.AuthorizationException;
import com.web.springmvc.budgetmanagement.exception.ResourceNotFoundException;
import com.web.springmvc.budgetmanagement.model.IconNote;
import com.web.springmvc.budgetmanagement.model.IconNoteType;
import com.web.springmvc.budgetmanagement.model.User;
import com.web.springmvc.budgetmanagement.repository.IconNoteRepository;
import com.web.springmvc.budgetmanagement.repository.IconRepository;
import com.web.springmvc.budgetmanagement.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final UsersService usersService;
    public IconNotesDto createIconNote(IconNotesDto iconNotesDto) {
        return mapToDto(iconNoteRepository.save(mapToEntity(iconNotesDto)));
    }

    public List<IconNotesDto> getAllIconNotesByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->  new ResourceNotFoundException("Not found user"));
        return iconNoteRepository.findByUserId(user.getId()).stream().map(this::mapToDto).toList();
    }

    public IconNotesDto getIconNoteById(Long id) {
        IconNote iconNote = iconNoteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found icon-note"));
        if(!iconNote.getUser().getUsername().equals(usersService.getCurrentUsername())) throw new AuthorizationException("You do not have permission to access this icon-note!");
        return mapToDto(iconNote);
    }

    public IconNotesDto updateIconNote(IconNotesDto iconNotesDto, Long id) {
        IconNote iconNote = iconNoteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not found icon-note"));
        iconNote.setName(iconNotesDto.getName());
        iconNote.setIconNoteType(IconNoteType.valueOf(iconNotesDto.getIconNoteType()));
        iconNote.setIcon(iconRepository.findById(iconNotesDto.getIconId()).orElseThrow(()->new ResourceNotFoundException("Not found icon")));
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
                .iconName(iconNote.getIcon().getUrl())
                .userId(iconNote.getUser().getId())
                .build();
    }

    private IconNote mapToEntity(IconNotesDto iconNotesDto) {
        return IconNote.builder()
                .id(iconNotesDto.getId())
                .name(iconNotesDto.getName())
                .iconNoteType(IconNoteType.valueOf(iconNotesDto.getIconNoteType()))
                .icon(iconRepository.findById(iconNotesDto.getIconId()).orElseThrow(()->new ResourceNotFoundException("Not found icon")))
                .user(userRepository.findByUsername(usersService.getCurrentUsername()).orElseThrow(()->new ResourceNotFoundException("Not found user")))
                .build();
    }
}
