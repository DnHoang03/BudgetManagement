package com.web.springmvc.budgetmanagement.service;

import com.web.springmvc.budgetmanagement.dto.IconsDto;
import com.web.springmvc.budgetmanagement.model.IconTypes;
import com.web.springmvc.budgetmanagement.model.Icons;
import com.web.springmvc.budgetmanagement.repository.IconCategoriesRepository;
import com.web.springmvc.budgetmanagement.repository.IconsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IconsService {
    private final IconsRepository iconsRepository;
    private final IconCategoriesRepository iconCategoriesRepository;

    public IconsDto createIcon(IconsDto icons) {
        return mapToDto(iconsRepository.save(mapToEntity(icons)));
    }

    public List<IconsDto> getAllIcons() {
        return iconsRepository.findAll().stream().map(this::mapToDto).toList();
    }

    public IconsDto getIconById(Long id) {
        return mapToDto(iconsRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found Icon")));
    }

    public List<IconsDto> getIconsCondition(String type, Long id) {
        if(type != null && id != null) {
            return iconsRepository.findByTypeAndIconCategoryId(IconTypes.valueOf(type), id).stream().map(this::mapToDto).toList();
        } else if(type != null) {
            return iconsRepository.findByType(IconTypes.valueOf(type)).stream().map(this::mapToDto).toList();
        } else if(id != null) {
            return iconsRepository.findByIconCategoryId(id).stream().map(this::mapToDto).toList();
        } else {
            return getAllIcons();
        }
    }

    private IconsDto mapToDto(Icons icons) {
        IconsDto iconsDto = new IconsDto();
        iconsDto.setId(icons.getId());
        iconsDto.setUrl(icons.getUrl());
        iconsDto.setType(icons.getType().name());
        iconsDto.setIconCategoryId(icons.getIconCategory().getId());
        return iconsDto;
    }
    private Icons mapToEntity(IconsDto iconsDto) {
        Icons icons = new Icons();
        icons.setId(iconsDto.getId());
        icons.setType(IconTypes.valueOf(iconsDto.getType()));
        icons.setUrl(iconsDto.getUrl());
        icons.setIconCategory(iconCategoriesRepository.findById(iconsDto.getId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found icon category")));
        return icons;
    }
}
