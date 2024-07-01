package com.web.springmvc.budgetmanagement.controller;

import com.web.springmvc.budgetmanagement.dto.UsersDto;
import com.web.springmvc.budgetmanagement.model.Users;
import com.web.springmvc.budgetmanagement.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;


    @PutMapping("/{id}")
    public String updateUser(@PathVariable("id") Long id, @RequestBody UsersDto usersDto) {
        System.out.println("User updated");
        return "updated";
    }
}
