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
import com.example.html.demo.repository.UserRepository;


@Controller
public class HomeController {


    @GetMapping("/home")
    public String home(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("username", user.getUsername());
        model.addAttribute("currentWeight", user.getCurrentWeight());
        model.addAttribute("goalWeight", user.getGoalWeight());
        model.addAttribute("experienceLevel", user.getExperienceLevel());
        return "home";
    }

}

