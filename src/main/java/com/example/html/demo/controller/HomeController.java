package com.example.html.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.html.demo.model.User;
import com.example.html.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class HomeController {
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/home")
    public String postMethodName(@ModelAttribute User user, Model model) {
        System.out.println(user.toString());
        user = userRepository.save(user);
        model.addAttribute("username", user.getUsername());

        return "home";
    }
    

    @GetMapping("/home")
    public String home(@ModelAttribute User user, Model model){
        return "home";
    }
}
