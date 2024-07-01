package com.web.springmvc.budgetmanagement.service;

import com.web.springmvc.budgetmanagement.dto.UsersDto;
import com.web.springmvc.budgetmanagement.model.Users;
import com.web.springmvc.budgetmanagement.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    
    public UsersDto getUserById(Long id) {
        return mapToDto(usersRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found user")));
    }

    private UsersDto mapToDto(Users users) {
        return new UsersDto(users.getId(), users.getUsername(), users.getGender(), users.getBudget());
    }
}
