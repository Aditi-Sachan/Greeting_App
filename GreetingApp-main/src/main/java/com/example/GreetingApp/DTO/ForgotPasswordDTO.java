package com.example.GreetingApp.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordDTO {

    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[@$!%*?&])(?=.*\\d).{8,}$",
            message = "Password must be at least 8 characters, include one uppercase, one special character, and one number"
    )
    private String password;
}

