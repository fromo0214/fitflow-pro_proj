package com.example.html.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.html.demo.model.User;
import com.example.html.demo.model.Workout;
import com.example.html.demo.model.WorkoutRoutine;
import com.example.html.demo.repository.UserRepository;
import com.example.html.demo.service.RatingService;
import com.example.html.demo.service.RecommendationService;
import com.example.html.demo.service.UserService;
import com.example.html.demo.service.WorkoutRoutineService;
import com.example.html.demo.service.WorkoutService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class SuggestedWorkoutsController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkoutRoutineService routineService;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private UserService userService;

    @PostMapping("suggested_workouts")
    public String postMethodName(@RequestParam("userId") Long userId,
    @RequestParam("routineId") Long routineId,
    @RequestParam("rating") double ratingValue, Model model) {
        User user = userRepository.findByUserId(userId);
        WorkoutRoutine workoutRoutine = routineService.findById(routineId);
        
        ratingService.saveRating(user, workoutRoutine, ratingValue);
              // Retrieve user and all workout routines
              List<WorkoutRoutine> allRoutines = routineService.getAllRoutines();
      
              // Generate recommendations for the hardcoded user
              List<WorkoutRoutine> recommendations = recommendationService.getRecommendations(user, allRoutines, 1);
      
              // Add recommendations to the model
              model.addAttribute("recommendations", recommendations);

        return "suggested_workouts";
    }
    
    // @GetMapping("suggested_workouts")
    // public String getSuggestedWorkouts(@RequestParam("userId") Long userId, Model model) {
        
    //     // Retrieve user and all workout routines
    //     User user = userRepository.findByUserId(userId);
    //     List<WorkoutRoutine> allRoutines = routineService.getAllRoutines();

    //     // Generate recommendations for the hardcoded user
    //     List<WorkoutRoutine> recommendations = recommendationService.getRecommendations(user, allRoutines, 1);

    //     // Add recommendations to the model
    //     model.addAttribute("recommendations", recommendations);

    //     return "suggested_workouts";
    // }
    
}
