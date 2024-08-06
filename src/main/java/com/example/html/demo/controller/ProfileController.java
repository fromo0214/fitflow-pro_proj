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
        double previousWeight = existingUser.getCurrentWeight();
        double currentWeight = user.getCurrentWeight();
        double weightChange = userService.calculateWeightChange(existingUser.getCurrentWeight(), user.getCurrentWeight());

        if(previousWeight != currentWeight){
            WeightChange weightChange2 = new WeightChange(); 
            weightChange2.setWeight(currentWeight);
            weightChange2.setDate(LocalDate.now());
            weightChange2.setUser(existingUser);
            weightChangeRepository.save(weightChange2);
        }

        user.setWeightChange(weightChange);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "redirect:/profile?username=" + user.getUsername();
    
        }
}
