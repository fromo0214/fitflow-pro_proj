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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/registeredSuccessfully")
    public String registerUser(@ModelAttribute User user, Model model) {
        System.out.println(user.toString());

        //encoding password before saving the user to db
        user.setPasswd(passwordEncoder.encode(user.getPasswd()));

        //sets all true 
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        //saves user to the db
        user = userRepository.save(user);

        //authenticate the user upon registering
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPasswd());
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        //using thymeleaf templating here to display the user details on the front-end
        model.addAttribute("username", user.getUsername());
        model.addAttribute("currentWeight", user.getCurrentWeight());
        model.addAttribute("goalWeight", user.getGoalWeight());
        model.addAttribute("experienceLevel", user.getExperienceLevel());
        return "redirect:/home"; 
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("username", user.getUsername());
        model.addAttribute("currentWeight", user.getCurrentWeight());
        model.addAttribute("goalWeight", user.getGoalWeight());
        model.addAttribute("experienceLevel", user.getExperienceLevel());
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login(){
        return "index";
    }

}

