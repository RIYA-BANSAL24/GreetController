package com.example.GreetingAppNew.dto;

import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserDTO {

    @Pattern(regexp = "^[A-Z][a-z]+$", message = "First letter must be uppercase and contain only alphabets.")
    @NotBlank
    private String firstName;

    @Pattern(regexp = "^[A-Z][a-z]+$", message = "First letter must be uppercase and contain only alphabets.")
    @NotBlank
    private String lastName;

    @Email(message = "Invalid email format.")
    @NotBlank
    private String email;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&*()_+=]).{8,}$",
            message = "Password must have at least 1 uppercase letter, 1 number, 1 special character, and be at least 8 characters long.")
    @NotBlank
    private String password;

}
