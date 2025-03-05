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

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthUserRepository authUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    // Register User with Encrypted Password
    public String registerUser(AuthUserDTO userDTO) {
        Optional<AuthUser> existingUser = authUserRepository.findByEmail(userDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email is already in use!");
        }

        // Hash the password
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());

        // Save user
        AuthUser user = new AuthUser();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(hashedPassword);
        authUserRepository.save(user);

        // Generate JWT Token upon registration
        String token = JwtUtil.generateToken(user.getEmail());

        return "User registered successfully! Token: " + token;
    }


    public String loginUser(LoginDTO loginDTO) {
        try {
            AuthUser user = authUserRepository.findByEmail(loginDTO.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found!"));

            if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
                throw new RuntimeException("Invalid email or password!");
            }

            sendLoginNotification(user.getEmail());
            String token = JwtUtil.generateToken(user.getEmail());

            return "Login successful! Check your email for a login notification, token: " + token;
        } catch (Exception e) {
            e.printStackTrace(); //
            return "Error during login: " + e.getMessage();
        }
    }

    // Send Email Notification on Login
    public void sendLoginNotification(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Login Notification");
        message.setText("Hey Bro! /n I hope your are doing well. /n If you have received my mail please revert it back.");
        mailSender.send(message);
        System.out.println("Login notification email sent to: " + email);
    }
}