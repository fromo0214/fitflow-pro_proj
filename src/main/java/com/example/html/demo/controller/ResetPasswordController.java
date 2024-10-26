package com.example.html.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.html.demo.model.ResetPasswordToken;
import com.example.html.demo.model.User;
import com.example.html.demo.repository.ResetPasswordTokenRepository;
import com.example.html.demo.repository.UserRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class ResetPasswordController {
    @Autowired
    private ResetPasswordTokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/reset_password")
    public String resetPassword(Model model, @RequestParam("token")String token) {
        ResetPasswordToken resetToken = tokenRepository.findByToken(token);

        if (resetToken == null){
            model.addAttribute("errorMessage", true);
            model.addAttribute("errorMessage", "Invalid password reset token.");
            return "reset_password";
        }

        if (resetToken.isExpired()){
            model.addAttribute("errorMessage", true);
            model.addAttribute("errorMessage", "The password reset token has expired.");
            return "reset_password";
        }

        model.addAttribute("token", token);
        return "reset_password";
    }

    @PostMapping("/reset_password")
    public String postMethodName(@RequestParam("password")String password, Model model, @RequestParam("token")
    String token ) {
        //TODO: process POST request
        ResetPasswordToken passwordToken = tokenRepository.findByToken(token);

        if(passwordToken == null || passwordToken.isExpired()){
            model.addAttribute("errorMessage", "Invalid or expired password reset token.");
            return "reset_password";
        }

        //fetching user and updating new password
        User user = passwordToken.getUser();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        
        //delete token after successful password reset
        tokenRepository.delete(passwordToken);

        return "redirect:/login?passwordUpdatedSuccessfully";
    }
    
}
