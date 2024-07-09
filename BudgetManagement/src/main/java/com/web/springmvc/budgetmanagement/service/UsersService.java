package com.web.springmvc.budgetmanagement.service;

import com.web.springmvc.budgetmanagement.dto.UsersDto;
import com.web.springmvc.budgetmanagement.exception.AuthorizationException;
import com.web.springmvc.budgetmanagement.exception.ResourceNotFoundException;
import com.web.springmvc.budgetmanagement.model.User;
import com.web.springmvc.budgetmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new ResourceNotFoundException("Not found user"));
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), List.of(authority));
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        }
        return null;
    }

    public UsersDto updateUser(Long id, UsersDto usersDto) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Not found user"));

        if(user.getUsername().equals(getCurrentUsername())) throw new AuthorizationException("You are not authorized to update this user");
        user.setBudget(usersDto.getBudget());
        user.setGender(usersDto.getGender());
        return mapToDto(userRepository.save(user));
    }

    private UsersDto mapToDto(User user) {
        return UsersDto
                .builder()
                .id(user.getId())
                .username(user.getUsername())
                .gender(user.getGender())
                .budget(user.getBudget())
                .build();
    }
}
