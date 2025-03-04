package com.example.GreetingAppNew.controller;

import com.example.GreetingAppNew.dto.AuthUserDTO;
import com.example.GreetingAppNew.dto.JwtResponse;
import com.example.GreetingAppNew.dto.LoginDTO;
import com.example.GreetingAppNew.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AuthUserDTO userDTO) {
        String response = authService.registerUser(userDTO);
        return response.equals("User registered successfully!")
                ? ResponseEntity.status(201).body(response)
                : ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        JwtResponse response = authService.loginUser(loginDTO);
        return ResponseEntity.ok(response);
    }
}
