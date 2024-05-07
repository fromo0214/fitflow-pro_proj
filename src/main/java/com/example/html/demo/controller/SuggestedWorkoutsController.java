package com.example.html.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.html.demo.model.User;
import com.example.html.demo.model.WorkoutRoutine;
import com.example.html.demo.service.RatingService;
import com.example.html.demo.service.RecommendationService;
import com.example.html.demo.service.UserService;
import com.example.html.demo.service.WorkoutRoutineService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class SuggestedWorkoutsController {

    // @Autowired
    // private RatingRepository ratingRepository;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private WorkoutRoutineService routineService;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private UserService userService;

    @PostMapping("suggested_workouts")
    public String postMethodName(@RequestParam("userId") Long userId,
    @RequestParam("routineId") Long routineId,
    @RequestParam("rating") double ratingValue) {
        
        ratingService.saveRating(userId, routineId, ratingValue);

        return "suggested_workouts";
    }
    
    @GetMapping("suggested_workouts")
    public String getMethodName(Model model) {
        List<User> allUsers = userService.getAllUsers();

        List<WorkoutRoutine> allRoutines = routineService.getAllRoutines();

        List<WorkoutRoutine> recommendations = recommendationService.getRecommendations(allUsers, allRoutines);

        model.addAttribute("recommendations", recommendations);

        return "suggested_workouts";
    }
    
}
