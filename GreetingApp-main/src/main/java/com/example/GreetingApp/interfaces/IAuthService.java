package com.example.GreetingApp.interfaces;

import com.example.GreetingApp.DTO.AuthUserDTO;
import com.example.GreetingApp.DTO.LoginDTO;
import com.example.GreetingApp.model.AuthUser;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.stereotype.Service;

@Service
public interface IAuthService {
    public AuthUser register(@Valid AuthUserDTO userDTO) ;

    String login(@Valid LoginDTO loginDTO);

    void forgotPassword(String email, String newPassword);

    void resetPassword(String email, String currentPassword, String newPassword);
}

