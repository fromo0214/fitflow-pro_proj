package com.example.html.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.html.demo.model.User;
import com.example.html.demo.repository.UserRepository;
import com.example.html.demo.service.CalorieService;
import com.example.html.demo.service.ImageService;
import com.example.html.demo.service.UserService;

@Controller
public class HomeController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CalorieService calorieService;

    @GetMapping("/home")
    public String home(Model model) {

    // Authorities or roles represent the permissions granted to the authenticated user.
    // These are used to restrict access to certain parts of the application based on the user's role.

    // (Authentication) the authentication objects holds the auth token and details of the user, including their authorities
    //                  when a user logins spring security creates an authentication object and stores it in SecurityContextHolder

    // (SecurityContextHolder) helper class provided by Spring Security to obtain the current security context
        
        //this line retrieves the current Authentication object from the 'SecurityContext'. 
        //this includes details such as the username, pw, and granted authorities etc
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //example structure of Authentication 
        // Authentication {
        //     principal: UserDetails { username, password, authorities },
        //     credentials: [PROTECTED],
        //     authorities: [ROLE_USER, ROLE_ADMIN],
        //     details: WebAuthenticationDetails { remoteAddress, sessionId }
        // }
        

        // Log authentication details for debugging
        if (auth != null) {
            System.out.println("Authentication Details: " + auth);
        }
    // (auth.getPrincipal()) returns the principal (authenticated user's representation) 
    // (UserDetails) provides methods to get user information such as username, pssword, etc.
        if (auth != null && auth.getPrincipal() instanceof UserDetails) {
            
            //Details are fetch from the repository
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            User user = userRepository.findByUsername(userDetails.getUsername());

            double bmr = user.getExperienceLevel();

            String activityCategory = calorieService.getActivityCategory(bmr);

            int age = userService.calculateAge(user.getDob());

            String randomImage = imageService.getRandomImage();


            if (user != null) {
                model.addAttribute("username", user.getUsername());
                model.addAttribute("age", age);
                model.addAttribute("currentWeight", user.getCurrentWeight());
                model.addAttribute("goalWeight", user.getGoalWeight());
                model.addAttribute("experienceLevel", user.getExperienceLevel());
                model.addAttribute("activityCategory", activityCategory);
                model.addAttribute("randomImage", randomImage);
                return "home";
            }
        }

        // Handle the case where the user is not found or not authenticated
        return "redirect:/login";  // Or show an error message
    }
}
