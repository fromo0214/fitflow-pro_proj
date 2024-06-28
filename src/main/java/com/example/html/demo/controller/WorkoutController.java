package com.example.html.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.html.demo.model.User;
import com.example.html.demo.model.Workout;

import com.example.html.demo.service.WorkoutService;


@Controller
public class WorkoutController {
    
    @Autowired
    private WorkoutService workoutService;

    @GetMapping("/workouts")
    public String showWorkouts(@ModelAttribute User user, Model model){
        

        List<Workout> workouts = workoutService.getAllWorkouts();
        Map<String,List<Workout>> groupedWorkouts = workoutService.groupWorkoutsByBodyParts(workouts);

        // model.addAttribute("recommendedWorkouts", recommendedWorkout);
        model.addAttribute("groupedWorkouts", groupedWorkouts);
        return "workouts";
    }
    

}

  
