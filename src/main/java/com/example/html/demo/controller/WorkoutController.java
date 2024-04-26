package com.example.html.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.html.demo.model.User;
import com.example.html.demo.model.Workout;
import com.example.html.demo.model.WorkoutRecommendationSystem;
import com.example.html.demo.repository.UserRepository;
import com.example.html.demo.service.WorkoutService;


@Controller
public class WorkoutController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private WorkoutRecommendationSystem recommendationSystem;

    @PostMapping("/workouts")
    public String postMethodName(@ModelAttribute User user, Model model) {
        
        user = userRepository.save(user); 
        model.addAttribute("experienceLevel", user.getExperienceLevel());
        return "workouts";
    }
    


    @GetMapping("/workouts")
    public String showWorkouts(@PathVariable int userId, Model model){
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            return ("User not found");
        }

        int usrId = user.getUserId();

        List<Workout> workouts = workoutService.getAllWorkouts();
        List<Workout> recommendedWorkout = getUserRecommended(usrId);
        //this s groups the workouts in my list by their corresponding body part they focus on
        Map<String,List<Workout>> groupedWorkouts = groupWorkoutsByBodyParts(workouts);

        model.addAttribute("recommendedWorkouts", recommendedWorkout);
        model.addAttribute("groupedWorkouts", groupedWorkouts);
        return "workouts";
    }

    public List<Workout> getUserRecommended(@PathVariable int userId){
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            return Collections.emptyList();
        }

        int experienceLevel = user.getExperienceLevel();

        return recommendationSystem.recommendWorkouts(experienceLevel);
    }

    //method to group the workouts by body part focus
    private Map<String, List<Workout>> groupWorkoutsByBodyParts(List<Workout> workouts) {
        // TODO Auto-generated method stub
        Map<String,List<Workout>> groupedWorkouts = new HashMap<>();

        for(Workout workout : workouts){
            String bodyPart = workout.getBodyPartFocus();
            //if the grouped workouts list doesn't contain the body part
            if(!groupedWorkouts.containsKey(bodyPart)){
                //adds the body part as a key to the map and a empty list.
                groupedWorkouts.put(bodyPart, new ArrayList<>());
            }
            //here the map adds the workouts to the list
            groupedWorkouts.get(bodyPart).add(workout);
        }
        return groupedWorkouts;
    }
}
