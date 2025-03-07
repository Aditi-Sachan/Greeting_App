package com.example.GreetingApp.controller;
import com.example.GreetingApp.DTO.AuthUserDTO;
import com.example.GreetingApp.DTO.LoginDTO;
import com.example.GreetingApp.DTO.ResponseDTO;
import com.example.GreetingApp.interfaces.IAuthService;
import com.example.GreetingApp.model.AuthUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.GreetingApp.DTO.ForgotPasswordDTO;

@RestController
@RequestMapping("/auth")
public class AuthUserController {
//    @Autowired
//    AuthenticationService authenticationService;

    //refactor
    @Autowired
    @Qualifier("authenticationService")
    IAuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@Valid @RequestBody AuthUserDTO userDTO) throws Exception{
        AuthUser user=authService.register(userDTO);
        ResponseDTO responseUserDTO =new ResponseDTO("User details is submitted!",user);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO){
        String result=authService.login(loginDTO);
        ResponseDTO responseUserDTO=new ResponseDTO("Login successfully!!",result);
        return  new ResponseEntity<>(responseUserDTO,HttpStatus.OK);
    }

    @PutMapping("/forgotPassword/{email}")
    public ResponseEntity<?> forgotPassword(
            @PathVariable String email,
            @Valid @RequestBody ForgotPasswordDTO forgotPasswordDTO) {

        authService.forgotPassword(email, forgotPasswordDTO.getPassword());
        return ResponseEntity.ok().body("{ \"message\": \"Password has been changed successfully!\" }");
    }

    @PutMapping("/resetPassword/{email}")
    public ResponseEntity<?> resetPassword(
            @PathVariable String email,
            @RequestParam String currentPassword,
            @RequestParam String newPassword) {

        authService.resetPassword(email, currentPassword, newPassword);
        return ResponseEntity.ok().body("{ \"message\": \"Password reset successfully!\" }");
    }
}
