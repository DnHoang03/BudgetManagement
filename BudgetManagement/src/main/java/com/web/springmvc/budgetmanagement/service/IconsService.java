package com.web.springmvc.budgetmanagement.service;

import com.web.springmvc.budgetmanagement.dto.IconsDto;
import com.web.springmvc.budgetmanagement.model.Icon;
import com.web.springmvc.budgetmanagement.model.IconType;
import com.web.springmvc.budgetmanagement.repository.IconCategoryRepository;
import com.web.springmvc.budgetmanagement.repository.IconRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IconsService {
    private final IconRepository iconRepository;
    private final IconCategoryRepository iconCategoryRepository;

    public IconsDto createIcon(IconsDto icons) {
        return mapToDto(iconRepository.save(mapToEntity(icons)));
    }

    public List<IconsDto> getAllIcons() {
        return iconRepository.findAll().stream().map(this::mapToDto).toList();
    }

    public IconsDto getIconById(Long id) {
        return mapToDto(iconRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found Icon")));
    }

    public List<IconsDto> getIconsCondition(String type, Long id) {
        if(type != null && id != null) {
            return iconRepository.findByTypeAndIconCategoryId(IconType.valueOf(type), id).stream().map(this::mapToDto).toList();
        } else if(type != null) {
            return iconRepository.findByType(IconType.valueOf(type)).stream().map(this::mapToDto).toList();
        } else if(id != null) {
            return iconRepository.findByIconCategoryId(id).stream().map(this::mapToDto).toList();
        } else {
            return getAllIcons();
        }
    }

    private IconsDto mapToDto(Icon icon) {
        IconsDto iconsDto = new IconsDto();
        iconsDto.setId(icon.getId());
        iconsDto.setUrl(icon.getUrl());
        iconsDto.setType(icon.getType().name());
        iconsDto.setIconCategoryId(icon.getIconCategory().getId());
        return iconsDto;
    }
    private Icon mapToEntity(IconsDto iconsDto) {
        Icon icon = new Icon();
        icon.setId(iconsDto.getId());
        icon.setType(IconType.valueOf(iconsDto.getType()));
        icon.setUrl(iconsDto.getUrl());
        icon.setIconCategory(iconCategoryRepository.findById(iconsDto.getId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found icon category")));
        return icon;
    }
}
