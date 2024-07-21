package com.example.html.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.html.demo.model.User;
import com.example.html.demo.model.WorkoutRoutine;
import com.example.html.demo.repository.UserRepository;
import com.example.html.demo.service.RatingService;
import com.example.html.demo.service.RecommendationService;
import com.example.html.demo.service.UserService;
import com.example.html.demo.service.WorkoutRoutineService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SuggestedWorkoutsController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkoutRoutineService routineService;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private UserService userService;

    @PostMapping("/suggested_workouts")
    public String postMethodName(@RequestParam Map<String, String> allRequestParams, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username);

        // Process the ratings
        allRequestParams.entrySet().stream()
            .filter(entry -> entry.getKey().startsWith("ratings[") && entry.getKey().endsWith("].rating"))
            .forEach(entry -> {
                String key = entry.getKey();
                String index = key.substring(key.indexOf('[') + 1, key.indexOf(']'));
                Long routineId = Long.parseLong(allRequestParams.get("ratings[" + index + "].routineId"));
                double ratingValue = Double.parseDouble(entry.getValue());
                
                WorkoutRoutine workoutRoutine = routineService.findById(routineId);
                ratingService.saveRating(user, workoutRoutine, ratingValue);
            });

        // Retrieve user and all workout routines
        List<WorkoutRoutine> allRoutines = routineService.getAllRoutines();

        // Generate recommendations for the user
        List<WorkoutRoutine> recommendations = recommendationService.getRecommendations(user, allRoutines, 1);

        // Add recommendations to the model
        model.addAttribute("recommendations", recommendations);

        return "suggested_workouts";
    }

    @GetMapping("/suggested_workouts")
    public String getMethodName() {
        return "suggested_workouts";
    }
    
}
