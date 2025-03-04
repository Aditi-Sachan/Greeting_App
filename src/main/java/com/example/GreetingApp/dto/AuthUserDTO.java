package com.example.GreetingApp.dto;

import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserDTO {

    @Pattern(regexp = "^[A-Z][a-z]*$", message = "Firstname must start with an uppercase letter.")
    private String firstName;

    @Pattern(regexp = "^[A-Z][a-z]*$", message = "Lastname must start with an uppercase letter.")
    private String lastName;

    @Email(message = "Invalid email format.")
    private String email;

    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[@#$%^&+=])(?=.*\\d).{8,}$",
            message = "Password must be at least 8 characters long, contain 1 uppercase, 1 special character, and 1 number."
    )
    private String password;
}

