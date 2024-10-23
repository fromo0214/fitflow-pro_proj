package com.example.html.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.html.demo.model.User;
import com.example.html.demo.model.VerificationToken;
import com.example.html.demo.repository.VerificationTokenRepository;
import com.example.html.demo.service.UserService;

import java.util.UUID;
import java.time.LocalDateTime;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        // Check if username is already taken
        boolean usernameTaken = userService.isUsernameTaken(user.getUsername());
        if (usernameTaken){
            model.addAttribute("usernameError", "Username is already taken.");
        }

        // Check if email is already registered
        boolean emailTaken = userService.isEmailTaken(user.getEmail());
        if (emailTaken){
            model.addAttribute("emailError", "Email is already taken.");
        }
        
        // If any of these conditions are true then return to register page with errors.
        if (emailTaken || usernameTaken){
            return "register";
        }


        String rawPassword = user.getPassword();
        // Encoding password before saving the user to db
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // not enabled until email verified
        user.setEnabled(false);

        // Generate verification token
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(LocalDateTime.now().plusHours(24)); //token expires in 24 hours
        tokenRepository.save(verificationToken);

        // Send verification email
        sendVerificationEmail(user.getEmail(), token);

        // // Save user to the db
        // userService.saveUserDetails(user);

        // Authenticate the user upon registering
        // UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getUsername(), rawPassword);
        // try {
        //     SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(authToken));
        //     System.out.println("Authentication successful for user: " + user.getUsername());
        // } catch (Exception e) {
        //     e.printStackTrace();
        //     System.out.println("Authentication failed for user: " + user.getUsername());
        // }


        return "redirect:/login?registered=true";
    }

    private void sendVerificationEmail(String recipientEmail, String token) {

        String subject = "Email Verification - FitFlow Pro";
        String confirmationURL = "http://www.fitflowpro.pro/verify?token=" + token;
        String message = "Please click the link below to verify your account: \n" + confirmationURL;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientEmail);
        email.setSubject(subject);
        email.setText(message);
        email.setFrom("support@fitflowpro.pro");

        mailSender.send(email);
    }
}

