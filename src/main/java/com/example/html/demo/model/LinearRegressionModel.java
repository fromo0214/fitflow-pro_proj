package com.example.html.demo.model;

import java.util.ArrayList;
import java.util.List;

public class LinearRegressionModel {

    private double slope;
    private double intercept;

    public void train(List<Double> x, List<Double> y) {
        // if (x.size() != y.size() || x.size() == 0) {  
        //     throw new IllegalArgumentException("Input lists must be of the same non-zero size");
        // }

        double sumX = 0;
        double sumY = 0;
        double sumXY = 0;
        double sumX2 = 0;

        int n = x.size();

        for (int i = 0; i < n; i++) {
            sumX += x.get(i);
            sumY += y.get(i);
            sumXY += x.get(i) * y.get(i);
            sumX2 += x.get(i) * x.get(i);
        }

        slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        intercept = (sumY - slope * sumX) / n;
    }

    public double predict(double x) {
        return slope * x + intercept;
    }

     // Method to suggest workouts based on predicted difficulty rating
    public List<Workout> suggestWorkouts(double experienceLevel, List<Workout> allWorkouts) {
        List<Workout> suggestedWorkouts = new ArrayList<>();
        double predictedRating = predict(experienceLevel);

        double threshold = 2.0;

        // Iterate through all workouts and add those with difficulty rating close to the predicted rating
        for (Workout workout : allWorkouts) {
            if (Math.abs(workout.getDifficultyRating() - predictedRating) <= threshold) {
                suggestedWorkouts.add(workout);
            }
        }

        return suggestedWorkouts;
    }

}
