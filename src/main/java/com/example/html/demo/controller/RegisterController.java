package com.example.html.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
        System.out.println(user.toString());

        String rawPassword = user.getPassword();
        // Encoding password before saving the user to db
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save user to the db
        userService.saveUserDetails(user);

        // Authenticate the user upon registering
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getUsername(), rawPassword);
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            // Log the error for further investigation
            e.printStackTrace();
            // Handle authentication failure (e.g., redirect to an error page or show a message)
            model.addAttribute("error", "Authentication failed after registration");
            return "register";
        }

        // Using Thymeleaf templating here to display the user details on the front-end
        model.addAttribute("username", user.getUsername());
        model.addAttribute("currentWeight", user.getCurrentWeight());
        model.addAttribute("goalWeight", user.getGoalWeight());
        model.addAttribute("experienceLevel", user.getExperienceLevel());

        return "redirect:/home";
    }
}

