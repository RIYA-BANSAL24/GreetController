package com.example.GreetingAppNew.controller;

import com.example.GreetingAppNew.dto.AuthUserDTO;
import com.example.GreetingAppNew.dto.LoginDTO;
import com.example.GreetingAppNew.dto.ForgotPassword;
import com.example.GreetingAppNew.dto.ResetPassword;
import com.example.GreetingAppNew.service.AuthService;
import com.example.GreetingAppNew.dto.JwtResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AuthUserDTO userDTO) {
        String response = authService.registerUser(userDTO);
        return ResponseEntity.ok(response);


    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.loginUser(loginDTO));
    }


    // Forgot Password Endpoint
    @PutMapping("/forgotPass/{email}")
    public String forgotPassword(@PathVariable String email, @RequestBody ForgotPassword request) {
        return authService.forgotPassword(email, request);
    }

    // Reset Password Endpoint
    @PutMapping("/resetPass/{email}")
    public String resetPassword(@PathVariable String email, @RequestBody ResetPassword request) {
        return authService.resetPassword(email, request);
    }
}