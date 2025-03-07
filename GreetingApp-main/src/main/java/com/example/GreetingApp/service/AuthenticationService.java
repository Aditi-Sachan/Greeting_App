package com.example.GreetingApp.service;
import com.example.GreetingApp.DTO.AuthUserDTO;
import com.example.GreetingApp.DTO.LoginDTO;
import com.example.GreetingApp.Exception.UserException;
import com.example.GreetingApp.Util.EmailSenderService;
import com.example.GreetingApp.Util.jwttoken;
import com.example.GreetingApp.interfaces.IAuthService;
import com.example.GreetingApp.model.AuthUser;
import com.example.GreetingApp.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthenticationService implements IAuthService {

    @Autowired
    AuthUserRepository authUserRepository;

    @Autowired
    jwttoken tokenUtil;

    @Autowired
    EmailSenderService emailSenderService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public AuthUser register(AuthUserDTO userDTO) {
        AuthUser user = new AuthUser(userDTO);

        String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(encryptedPassword);

        String token = tokenUtil.createToken(user.getUserId());
        authUserRepository.save(user);

        emailSenderService.sendEmail(user.getEmail(), "Registered in Greeting App", "Hi "
                + user.getFirstName() + ",\nYou have been successfully registered!\n\nYour registered details are:\n\n User Id:  "
                + user.getUserId() + "\n First Name:  "
                + user.getFirstName() + "\n Last Name:  "
                + user.getLastName() + "\n Email:  "
                + user.getEmail() + "\n Token:  " + token);

        return user;
    }

    @Override
    public String login(LoginDTO loginDTO) {
        Optional<AuthUser> user = Optional.ofNullable(authUserRepository.findByEmail(loginDTO.getEmail()));

        if (user.isPresent()) {
            // Check if the password matches the encrypted password
            if (passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
                emailSenderService.sendEmail(user.get().getEmail(), "Logged in Successfully!", "Hi "
                        + user.get().getFirstName() + ",\n\nYou have successfully logged in into Greeting App!");

                return "Congratulations!! You have logged in successfully!";
            } else {
                throw new UserException("Sorry! Email or Password is incorrect!");
            }
        } else {
            throw new UserException("Sorry! Email or Password is incorrect!");
        }
    }

    @Override
    public void forgotPassword(String email, String newPassword) {
        Optional<AuthUser> userOptional = Optional.ofNullable(authUserRepository.findByEmail(email));

        if (userOptional.isEmpty()) {
            throw new UserException("Sorry! We cannot find the user email: " + email);
        }

        AuthUser user = userOptional.get();

        // Hash the new password using BCrypt
        String hashedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(hashedPassword);

        // Save the updated password
        authUserRepository.save(user);

        // Send confirmation email
        emailSenderService.sendEmail(user.getEmail(),
                "Password Reset Confirmation",
                "Hi " + user.getFirstName() + ",\n\nYour password has been successfully updated!");

        System.out.println("Password has been changed successfully!");
    }

    @Override
    public void resetPassword(String email, String currentPassword, String newPassword) {
        AuthUser user = authUserRepository.findByEmail(email);

        if (user == null) {
            throw new UserException("User not found with email: " + email);
        }

        // Check if the current password matches
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new UserException("Current password is incorrect!");
        }

        // Hash the new password and update it in the database
        String hashedNewPassword = passwordEncoder.encode(newPassword);
        user.setPassword(hashedNewPassword);
        authUserRepository.save(user);
    }
}