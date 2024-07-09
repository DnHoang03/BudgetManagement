package com.web.springmvc.budgetmanagement.controller;

import com.web.springmvc.budgetmanagement.dto.AuthenticationRequest;
import com.web.springmvc.budgetmanagement.dto.AuthenticationResponse;
import com.web.springmvc.budgetmanagement.dto.SignUpDto;
import com.web.springmvc.budgetmanagement.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(authenticationService.register(signUpDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.login(authenticationRequest));
    }
}
