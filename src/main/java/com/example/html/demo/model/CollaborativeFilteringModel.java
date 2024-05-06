package com.example.html.demo.model;

import java.util.ArrayList;
import java.util.List;

public class CollaborativeFilteringModel {

    // Assume userItemMatrix is a 2D array representing user-item interactions
    // and similarityMatrix is a 2D array to store calculated similarities

    // Step 1: Calculate similarities
    public void calculateSimilarities(double[][] userItemMatrix) {
        int numUsers = userItemMatrix.length;
        double[][] similarityMatrix = new double[numUsers][numUsers];

        for (int user1 = 0; user1 < numUsers; user1++) {
            for (int user2 = 0; user2 < numUsers; user2++) {
                if (user1 != user2) {
                    double similarity = calculateSimilarity(userItemMatrix[user1], userItemMatrix[user2]);
                    similarityMatrix[user1][user2] = similarity;
                }
            }
        }
    }
    
    // Step 2: Generate recommendations
    public List<Workout> generateRecommendations(double[][] userItemMatrix, double[][] similarityMatrix, int targetUser, List<Workout> allWorkouts, double threshold) {
        int numItems = userItemMatrix[0].length;
        List<Workout> recommendations = new ArrayList<>();

        for (int item = 0; item < numItems; item++) {
            if (userItemMatrix[targetUser][item] == 0) {
                double similaritySum = 0;
                double weightedRatingSum = 0;

                for (int user = 0; user < userItemMatrix.length; user++) {
                    if (userItemMatrix[user][item] != 0) {
                        double similarity = similarityMatrix[targetUser][user];
                        double rating = userItemMatrix[user][item];
                        similaritySum += similarity;
                        weightedRatingSum += similarity * rating;
                    }
                }

                if (similaritySum > 0) {
                    double predictedRating = weightedRatingSum / similaritySum;
                    // Add the workout to recommendations if predicted rating is above a threshold
                    if (predictedRating >= threshold) {
                        recommendations.add(allWorkouts.get(item));
                    }
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