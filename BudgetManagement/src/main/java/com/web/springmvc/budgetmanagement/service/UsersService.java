package com.web.springmvc.budgetmanagement.service;

import com.web.springmvc.budgetmanagement.dto.UsersDto;
import com.web.springmvc.budgetmanagement.model.User;
import com.web.springmvc.budgetmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UserRepository userRepository;
    
    public UsersDto getUserById(Long id) {
        return mapToDto(userRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found user")));
    }

    private UsersDto mapToDto(User user) {
        return new UsersDto(user.getId(), user.getUsername(), user.getGender(), user.getBudget());
    }
}
