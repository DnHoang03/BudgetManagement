package com.web.springmvc.budgetmanagement.controller;

import com.web.springmvc.budgetmanagement.dto.UsersDto;
import com.web.springmvc.budgetmanagement.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping
    public ResponseEntity<UsersDto> getUserByUsername(@RequestParam(value = "username", required = true) String username) {
        return ResponseEntity.ok(usersService.getUserByUsername(username));
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsersDto> updateUser(@PathVariable("id") Long id, @RequestBody UsersDto usersDto) {
        return ResponseEntity.ok(usersService.updateUser(id, usersDto));
    }
}
