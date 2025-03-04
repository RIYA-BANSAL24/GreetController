package com.example.GreetingAppNew.service;

import com.example.GreetingAppNew.dto.AuthUserDTO;
import com.example.GreetingAppNew.dto.LoginDTO;
import com.example.GreetingAppNew.model.AuthUser;
import com.example.GreetingAppNew.dto.JwtResponse;
import com.example.GreetingAppNew.security.JwtUtil;
import com.example.GreetingAppNew.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthUserRepository authUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;



    public String registerUser(AuthUserDTO userDTO) {
        Optional<AuthUser> user1 = authUserRepository.findByEmail(userDTO.getEmail());
        if (user1.isPresent()){
            throw new RuntimeException("Email is already in use!");
        }

        // Hash the password
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());

        AuthUser user = new AuthUser();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        authUserRepository.save(user);


        String token = JwtUtil.generateToken(user.getEmail());

        return "User registered successfully! Token: " + token;
    }

    public String loginUser(LoginDTO loginDTO) {
        AuthUser user = authUserRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new RuntimeException("Invalid email or password!"));

        if ( passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return "Login successful! Check your email for login notification.";
        } else {
            throw new RuntimeException("Invalid email or password!");
        }
    }

}