package com.example.html.demo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.example.html.demo.model.User;
import com.example.html.demo.model.WeightChange;
import com.example.html.demo.repository.UserRepository;
import com.example.html.demo.repository.WeightChangeRepository;
import com.example.html.demo.service.UserService;



@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WeightChangeRepository weightChangeRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String showProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);
        Long id = user.getUserId();
        
        List<WeightChange> weightChanges = weightChangeRepository.findByUser_Id(id);
        List<String> dates = weightChanges.stream().map(wc -> wc.getDate().toString()).collect(Collectors.toList());
        List<Double> weights = weightChanges.stream().map(WeightChange::getWeight).collect(Collectors.toList());


        model.addAttribute("dates", dates);
        model.addAttribute("weights", weights);

        return "profile";
    }
    

    @PostMapping("/profile")
public String updateProfile(@ModelAttribute User user) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) auth.getPrincipal();
    User existingUser = userRepository.findByUsername(userDetails.getUsername());

    // Update fields from the user input to existingUser
    existingUser.setEmail(user.getEmail());
    existingUser.setHeight(user.getHeight());
    existingUser.setGender(user.getGender());
    existingUser.setDob(user.getDob());
    existingUser.setStartWeight(user.getStartWeight());
    existingUser.setGoalWeight(user.getGoalWeight());
    existingUser.setExperienceLevel(user.getExperienceLevel());

    // Calculate weight change before updating the current weight
    double previousWeight = existingUser.getCurrentWeight();
    double currentWeight = user.getCurrentWeight();
    
    if (previousWeight != currentWeight) {
        double weightChange = userService.calculateWeightChange(previousWeight, currentWeight);
        
        // Save the weight change record
        WeightChange weightChangeRecord = new WeightChange();
        weightChangeRecord.setWeight(currentWeight);
        weightChangeRecord.setDate(LocalDate.now());
        weightChangeRecord.setUser(existingUser);
        weightChangeRepository.save(weightChangeRecord);

        // Update the weightChange field for existingUser
        existingUser.setWeightChange(weightChange);
        existingUser.setCurrentWeight(currentWeight);  // Update current weight in existingUser
    }

    // Update password if a new password was provided
    if (user.getPassword() != null && !user.getPassword().trim().isEmpty()) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        existingUser.setPassword(encodedPassword);
    }

    // Save the updated user details
    userRepository.save(existingUser);

    return "redirect:/profile?username=" + existingUser.getUsername();
}

    
}
