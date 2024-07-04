package com.web.springmvc.budgetmanagement.controller;

import com.web.springmvc.budgetmanagement.dto.UsersDto;
import com.web.springmvc.budgetmanagement.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
