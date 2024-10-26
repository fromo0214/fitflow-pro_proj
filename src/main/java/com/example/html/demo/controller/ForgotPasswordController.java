package com.example.html.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.html.demo.model.User;
import com.example.html.demo.repository.ResetPasswordTokenRepository;
import com.example.html.demo.repository.UserRepository;
import com.example.html.demo.model.ResetPasswordToken;

import java.util.UUID;
import java.time.LocalDateTime;

@Controller
public class ForgotPasswordController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ResetPasswordTokenRepository resetPasswordRepository;

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/forgot_password")
    public String getMethodName() {
        return "forgot_password";
    }
    

    @PostMapping("/forgot_password")
    public String forgotPassword(Model model, @RequestParam("email")String email){
        User user = userRepository.findByEmail(email);

        //check to see if user exists 
        if(user == null){
            model.addAttribute("emailError", "No account found with that email address.");
        }

        //generates a reset token
        String token = UUID.randomUUID().toString();
        ResetPasswordToken resetToken = new ResetPasswordToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiryDate(LocalDateTime.now().plusHours(1)); //expires in 1 hour
        resetPasswordRepository.save(resetToken);

        //send reset email to user
        sendResetEmail(user.getEmail(), token);

        model.addAttribute
        ("message",
"A reset link has been sent to your email. Please allow up to 5-10 minutes for it to appear in your inbox. Check spam folder.");
        
        return "forgot_password";
    }

    //method to send reset email 
    private void sendResetEmail(String recipientEmail, String token){
        String subject = "Reset Password - FitFlow Pro";
        String confirmationURL = "http://www.fitflowpro.pro/reset_password?token=" + token;
        String message = "Please click the link below to reset your password: \n" + confirmationURL;
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientEmail);
        email.setSubject(subject);
        email.setText(message);
        email.setFrom("support@fitflowpro.pro");
        mailSender.send(email);
    }
    
}
