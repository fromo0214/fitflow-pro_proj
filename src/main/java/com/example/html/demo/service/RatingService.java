package com.example.html.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.html.demo.model.Rating;
import com.example.html.demo.model.User;
import com.example.html.demo.model.WorkoutRoutine;
import com.example.html.demo.repository.RatingRepository;
import com.example.html.demo.repository.UserRepository;
import com.example.html.demo.repository.WorkoutRoutineRepository;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkoutRoutineRepository routineRepository;


    public double getRatingForWorkout(Long userId, Long routineId) {
        User user = userRepository.findById(userId).orElse(null);
        WorkoutRoutine workoutRoutine = routineRepository.findById(routineId).orElse(null);

        
        if (user == null || workoutRoutine == null){
            System.out.println("user or routine is null");
            return -1;
        }

        Rating rating = ratingRepository.findByUserAndWorkoutRoutine(user, workoutRoutine);

        if(rating != null){
            return rating.getRating();
        }else{
            return -1;
        }
    }

    public void saveRating(User user, WorkoutRoutine workoutRoutine, double ratingValue) {
        // Retrieve user and workout routine entities
        // Create a new rating entity and set its properties
        Rating rating = new Rating();
        rating.setUser(user);
        rating.setWorkoutRoutine(workoutRoutine);
        rating.setRating(ratingValue);

        // Save the rating to the database
        ratingRepository.save(rating);
    }
        
    public List<Rating> getAllRatings(){
        return (List<Rating>) ratingRepository.findAll();
    }

    public List<Object[]> getUserRatings() {
        return ratingRepository.findUserRatings();
    }

    }
