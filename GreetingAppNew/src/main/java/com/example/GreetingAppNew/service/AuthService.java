package com.example.GreetingAppNew.service;

import com.example.GreetingAppNew.dto.AuthUserDTO;
import com.example.GreetingAppNew.dto.JwtResponse;
import com.example.GreetingAppNew.dto.LoginDTO;
import com.example.GreetingAppNew.model.AuthUser;
import com.example.GreetingAppNew.repository.AuthUserRepository;
import com.example.GreetingAppNew.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public String registerUser(AuthUserDTO userDTO) {
        if (authUserRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            return "Email is already in use.";
        }

        AuthUser newUser = AuthUser.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();

        authUserRepository.save(newUser);
        return "User registered successfully!";
    }

    public JwtResponse loginUser(LoginDTO loginDTO) {
        Optional<AuthUser> user = authUserRepository.findByEmail(loginDTO.getEmail());

        if (user.isEmpty() || !passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
            throw new RuntimeException("Invalid credentials!");
        }

        String token = jwtUtil.generateToken(user.get().getEmail());
        return new JwtResponse(token);
    }
}
