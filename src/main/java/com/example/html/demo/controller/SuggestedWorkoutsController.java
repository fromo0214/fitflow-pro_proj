package com.example.html.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.html.demo.model.Workout;
import com.example.html.demo.model.WorkoutRoutine;
import com.example.html.demo.service.RatingService;
import com.example.html.demo.service.WorkoutService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class SuggestedWorkoutsController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private WorkoutService workoutService;


    // @PostMapping("suggested_workouts")
    // public String postMethodName(@RequestParam("userId") Long userId,
    // @RequestParam("routineId") Long routineId,
    // @RequestParam("rating") double ratingValue) {
        
    //     ratingService.saveRating(userId, routineId, ratingValue);

    //     return "suggested_workouts";
    // }
    
    @GetMapping("suggested_workouts")
    public String getMethodName(Model model) {

        List<Workout> allWorkouts = workoutService.getAllWorkouts();


        List<WorkoutRoutine> recommendations = new ArrayList<>();
        recommendations.add(new WorkoutRoutine(4L, "Routine 4", allWorkouts));
        // Add recommendations to the model
        model.addAttribute("recommendations", recommendations);


        model.addAttribute("recommendations", recommendations);

        return "suggested_workouts";
    }
    
}
