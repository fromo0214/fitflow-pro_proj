package com.example.html.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.html.demo.model.User;
import com.example.html.demo.repository.UserRepository;
import com.example.html.demo.service.BMIService;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BMIController {

    @Autowired
    private BMIService bmiService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/bmi")
    public String bmi(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String username = userDetails.getUsername();

        User user = userRepository.findByUsername(username);

        double bmi = bmiService.calculateBMI(user.getCurrentWeight(), user.getHeight());
        String formattedBMI = String.format("%.1f", bmi);
        String bmiCategory = bmiService.getBMICategory(bmi);
        String motivationMessage = bmiService.getMotivationMessage(bmiCategory);


        model.addAttribute("currentWeight", user.getCurrentWeight());
        model.addAttribute("height", user.getHeight());
        model.addAttribute("bmi", formattedBMI);
        model.addAttribute("motivationMessage", motivationMessage);
        model.addAttribute("bmiCategory", bmiCategory);

        return "bmi";
    }
    
}
