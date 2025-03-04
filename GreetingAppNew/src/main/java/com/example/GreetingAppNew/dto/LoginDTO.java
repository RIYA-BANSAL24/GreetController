package com.example.GreetingAppNew.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    @Email(message = "Invalid email format.")
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
