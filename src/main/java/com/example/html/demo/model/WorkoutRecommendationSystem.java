package com.example.html.demo.model;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class WorkoutRecommendationSystem {
    private Map<Integer, List<Workout>> workoutExperienceRatings;

    public WorkoutRecommendationSystem(){
        workoutExperienceRatings = new HashMap<>();
    }

    public void addWorkoutExperienceRating(int experienceLevel, List<Workout> workouts){
        workoutExperienceRatings.put(experienceLevel, workouts);
    }

    //recommends workouts based on experience level
    public List<Workout> recommendWorkouts(int experienceLevel){
        List<Workout> recommendations = new ArrayList<>();
        for(int i = experienceLevel; i >= 1; i--){
            List<Workout> workouts = workoutExperienceRatings.get(i);
            if (workouts != null) {
                recommendations.addAll(workouts);
            }
        }
        return recommendations;
    }
}
