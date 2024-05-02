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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.html.demo.model.LinearRegressionModel;
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

    // private WorkoutRecommendationSystem recommendationSystem;

    // @GetMapping("/workouts")
    // public String showUserExperience(@ModelAttribute User user, Model model) {
    //     int userId = user.getUserId();
    //     user = userRepository.findById(userId).orElse(null);
    //     if(user == null){
    //         model.addAttribute("null", user.getExperienceLevel());
    //     }
    //     model.addAttribute("experienceLevel", user.getExperienceLevel());
    //     return "workouts";
    // }
    

    @GetMapping("/workouts")
    public String showWorkouts(@ModelAttribute User user, Model model){
        

        List<Workout> workouts = workoutService.getAllWorkouts();
        Map<String,List<Workout>> groupedWorkouts = groupWorkoutsByBodyParts(workouts);

        // model.addAttribute("recommendedWorkouts", recommendedWorkout);
        model.addAttribute("groupedWorkouts", groupedWorkouts);
        return "workouts";
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

  
