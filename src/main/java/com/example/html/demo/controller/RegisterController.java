package com.example.html.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.html.demo.service.UserService;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    
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

        // Save user to the db
        userService.saveUserDetails(user);

        // Authenticate the user upon registering
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getUsername(), rawPassword);
        try {
            SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(authToken));
            System.out.println("Authentication successful for user: " + user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Authentication failed for user: " + user.getUsername());
        }

        System.out.println("Current user: " + SecurityContextHolder.getContext().getAuthentication().getName());


        return "redirect:/login?registered=true";
    }
}

