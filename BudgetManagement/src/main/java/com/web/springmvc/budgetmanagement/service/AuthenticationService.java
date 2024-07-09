package com.web.springmvc.budgetmanagement.service;

import com.web.springmvc.budgetmanagement.config.jwt.JwtService;
import com.web.springmvc.budgetmanagement.dto.AuthenticationRequest;
import com.web.springmvc.budgetmanagement.dto.AuthenticationResponse;
import com.web.springmvc.budgetmanagement.dto.SignUpDto;
import com.web.springmvc.budgetmanagement.exception.ResourceNotFoundException;
import com.web.springmvc.budgetmanagement.model.User;
import com.web.springmvc.budgetmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(SignUpDto signUpDto) {
        User user = User
                .builder()
                .username(signUpDto.getUsername())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .build();
        userRepository.save(user);
        String token = jwtService.generateToken(user.getUsername());
        return AuthenticationResponse.builder().token(token).build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new ResourceNotFoundException("Not found user"));
        String token = jwtService.generateToken(user.getUsername());
        return AuthenticationResponse.builder().token(token).build();
    }
}
