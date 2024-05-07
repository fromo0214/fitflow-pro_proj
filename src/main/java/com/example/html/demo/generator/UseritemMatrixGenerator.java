package com.example.html.demo.generator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.html.demo.model.User;
import com.example.html.demo.model.Workout;
import com.example.html.demo.model.WorkoutRoutine;
import com.example.html.demo.service.RatingService;

@Component
public class UseritemMatrixGenerator {

    @Autowired
    private RatingService ratingService;

    
    public double[][] generateUserItemMatrix(List<User> users, List<WorkoutRoutine> routines) {
        int numRows = users.size();
        int numCols = routines.size();
        double[][] userItemMatrix = new double[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            User user = users.get(i);
            for (int j = 0; j < numCols; j++) {
                WorkoutRoutine routine = routines.get(j);
                // Example: Populate matrix cell with user's rating for the workout
                // You may need to adjust this based on your application's logic
                double rating = getAverageRatingForRoutine(user, routine);
                userItemMatrix[i][j] = rating;
            }
        }

        return userItemMatrix;
    }

    // Example method to get user's rating for a workout
    private double getAverageRatingForRoutine(User user, WorkoutRoutine routine) {
        //retrieves ratings for each workout in the routine and calculate the average
        List<Workout> workouts = routine.getWorkouts();
        double sum = 0;
        int count = 0;

        for(Workout workout : workouts){
            double workoutRating = ratingService.getRatingForWorkout(user.getUserId(), workout.getWorkoutId());
            if(workoutRating >= 0){
                sum += workoutRating;
                count++;
            }
        }

        return count > 0 ? sum / count : 0; //calculates the average rating for the routine: else returns
    }
}

