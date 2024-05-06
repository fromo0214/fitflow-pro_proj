package com.example.html.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.html.demo.model.User;
import com.example.html.demo.repository.UserRepository;
import com.example.html.demo.service.UserService;


@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/registeredSuccessfully")
    public String registerUser(@ModelAttribute User user, Model model) {
        System.out.println(user.toString());
        user = userRepository.save(user);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("currentWeight", user.getCurrentWeight());
        model.addAttribute("goalWeight", user.getGoalWeight());
        model.addAttribute("experienceLevel", user.getExperienceLevel());
        return "home"; 
    }

    @GetMapping("/home")
    public String home(Model model){
        return "home";
    }
}
