package com.example.html.demo.Initializer;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.html.demo.model.Rating;
import com.example.html.demo.model.User;
import com.example.html.demo.model.WorkoutRoutine;
import com.example.html.demo.repository.UserRepository;
import com.example.html.demo.service.RatingService;
import com.example.html.demo.service.UserService;
import com.example.html.demo.service.WorkoutRoutineService;

import jakarta.annotation.PostConstruct;

@Component
public class RatingInitializer {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserService userService;

    @Autowired
    private WorkoutRoutineService routineService;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initializeRatings() {
        List<User> users = userService.getAllUsers();
        List<WorkoutRoutine> routines = routineService.getAllRoutines();
    
        double[] ratings = {3.5, 4.0, 4.5, 3.0, 5.0}; // Sample ratings
    
        for (User user : users) {
            for (WorkoutRoutine routine : routines) {
                // Randomly select a rating from the sample ratings array
                double ratingValue = ratings[new Random().nextInt(ratings.length)];
                
                // Save the rating for the current user and routine
                ratingService.saveRating(user.getUserId(), routine.getRoutineId(), ratingValue);
            }
        }
    }

    private double getRandomRating() {
        Random rand = new Random();
        return 1 + (5 - 1) * rand.nextDouble();
    }
}
