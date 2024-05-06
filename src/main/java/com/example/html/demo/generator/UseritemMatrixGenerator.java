package com.example.html.demo.generator;

import java.util.List;

import com.example.html.demo.model.User;
import com.example.html.demo.model.Workout;

public class UseritemMatrixGenerator {
    public double[][] generateUserItemMatrix(List<User> users, List<Workout> workouts) {
        int numRows = users.size();
        int numCols = workouts.size();
        double[][] userItemMatrix = new double[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            User user = users.get(i);
            for (int j = 0; j < numCols; j++) {
                Workout workout = workouts.get(j);
                // Example: Populate matrix cell with user's rating for the workout
                // You may need to adjust this based on your application's logic
                double rating = getUserRatingForWorkout(user, workout);
                userItemMatrix[i][j] = rating;
            }
        }

        return userItemMatrix;
    }

    // Example method to get user's rating for a workout
    private double getUserRatingForWorkout(User user, Workout workout) {
        // You need to implement this based on your application's logic
        // For example, retrieve rating from a database entity or calculate it based on user's interactions
        // This is just a placeholder method
        return 0.0; // Return a default rating for now
    }
}

