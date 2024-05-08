package com.example.html.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CollaborativeFilteringModel {

    // Assume userItemMatrix is a 2D array representing user-item interactions
    // and similarityMatrix is a 2D array to store calculated similarities

    // Method to hard code a similarity matrix based on predefined ratings
    public double[][] hardCodeSimilarityMatrix() {
        // Define the number of users
        int numUsers = 3; // Example: 3 users

        // Define the similarity matrix
        double[][] similarityMatrix = new double[numUsers][numUsers];

        // Hard code the similarity values based on ratings
        // Example: Assuming ratings for three users are [4.5, 3.0, 2.0]
        double[] user1Ratings = {4.5, 0.0, 0.0};
        double[] user2Ratings = {3.0, 4.0, 0.0};
        double[] user3Ratings = {2.0, 0.0, 5.0};

        // Calculate similarities based on ratings (e.g., cosine similarity)
        for (int i = 0; i < numUsers; i++) {
            for (int j = 0; j < numUsers; j++) {
                if (i != j) {
                    similarityMatrix[i][j] = calculateSimilarity(user1Ratings, user2Ratings);
                    // You can use different similarity metrics or weighting methods here
                }
            }
        }

        return similarityMatrix;
    }

    // Step 1: Calculate similarities
    public List<Double> calculateSimilarities(double[][] userItemMatrix, int activeUserIndex) {
        List<Double> similarities = new ArrayList<>();

        double[] activeUserRatings = userItemMatrix[activeUserIndex];

        for(int userIndex = 0; userIndex < userItemMatrix.length; userIndex++){
            if(userIndex != activeUserIndex){
                double similarity = calculateSimilarity(activeUserRatings, userItemMatrix[userIndex]);
                similarities.add(similarity);
            }
        }
        return similarities;
    }
    
    public List<WorkoutRoutine> generateRecommendations(double[][] userItemMatrix, List<Double> similarities,
                                                        List<WorkoutRoutine> allRoutines, int activeUserIndex, int k) {
        List<WorkoutRoutine> recommendations = new ArrayList<>();

        // Calculate weighted average of ratings from similar users
        double weightedSum = 0;
        double similaritySum = 0;

        // Iterate over users to find similar ones
        for (int userIndex = 0; userIndex < userItemMatrix.length; userIndex++) {
            if (userIndex != activeUserIndex) { // Exclude the active user
                double similarity = similarities.get(userIndex);
                double[] userRatings = userItemMatrix[userIndex];

                for (int routineIndex = 0; routineIndex < userRatings.length; routineIndex++) {
                    if (userItemMatrix[activeUserIndex][routineIndex] == 0 && userRatings[routineIndex] != 0) {
                        // If the active user hasn't rated the routine but the similar user has
                        weightedSum += similarity * userRatings[routineIndex];
                        similaritySum += similarity;
                    }
                }
            }
        }

        // Calculate predicted ratings and generate recommendations
        if (similaritySum != 0) {
            double predictedRating = weightedSum / similaritySum;

            for (int routineIndex = 0; routineIndex < userItemMatrix[activeUserIndex].length; routineIndex++) {
                if (userItemMatrix[activeUserIndex][routineIndex] == 0) {
                    // If the active user hasn't rated the routine, add it to recommendations
                    WorkoutRoutine recommendedRoutine = allRoutines.get(routineIndex);
                    recommendedRoutine.setPredictedRating(predictedRating); // Optionally set predicted rating
                    recommendations.add(recommendedRoutine);
                }
            }
        }

        return recommendations;
    }

    // Calculate similarity between two users (cosine similarity)
    private double calculateSimilarity(double[] user1Ratings, double[] user2Ratings) {
        // Your implementation here
        double dotProduct = 0;
        double magnitudeUser1 = 0;
        double magnitudeUser2 = 0;

        for (int i = 0; i < user1Ratings.length; i++) {
            dotProduct += user1Ratings[i] * user2Ratings[i];
            magnitudeUser1 += Math.pow(user1Ratings[i], 2);
            magnitudeUser2 += Math.pow(user2Ratings[i], 2);
        }

        magnitudeUser1 = Math.sqrt(magnitudeUser1);
        magnitudeUser2 = Math.sqrt(magnitudeUser2);

        if (magnitudeUser1 * magnitudeUser2 == 0) {
            return 0; // Handle division by zero
        }

        return dotProduct / (magnitudeUser1 * magnitudeUser2);
        }
}