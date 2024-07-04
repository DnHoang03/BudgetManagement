package com.web.springmvc.budgetmanagement.controller;

import com.web.springmvc.budgetmanagement.dto.IconsDto;
import com.web.springmvc.budgetmanagement.service.IconsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/icons")
@RequiredArgsConstructor
public class IconsController {
    private final IconsService iconsService;
    @GetMapping()
    public ResponseEntity<List<IconsDto>> getAllIcons(@RequestParam(value = "categoryId",required = false)Long id, @RequestParam(value = "type", required = false)String type) {
        return ResponseEntity.ok(iconsService.getIconsCondition(type, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IconsDto> getIconById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(iconsService.getIconById(id));
    }
}
