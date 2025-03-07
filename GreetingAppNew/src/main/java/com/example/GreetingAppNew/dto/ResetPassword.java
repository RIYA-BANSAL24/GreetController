package com.example.GreetingAppNew.dto;
import lombok.Data;

@Data
public class ResetPassword {
    private String currentPassword;
    private String newPassword;
}

