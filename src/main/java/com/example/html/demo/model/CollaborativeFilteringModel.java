package com.example.html.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.html.demo.repository.RatingRepository;

@Component
public class CollaborativeFilteringModel {

    // Assume userItemMatrix is a 2D array representing user-item interactions
    // and similarityMatrix is a 2D array to store calculated similarities

    @Autowired
    private RatingRepository ratingRepository;

        public double[][] fetchRatings(List<User> allUsers, List<WorkoutRoutine> allRoutines) {
            int numUsers = allUsers.size();
            int numRoutines = allRoutines.size();
            double[][] userItemMatrix = new double[numUsers][numRoutines];
    
            for (int i = 0; i < numUsers; i++) {
                User user = allUsers.get(i);
                for (int j = 0; j < numRoutines; j++) {
                    WorkoutRoutine routine = allRoutines.get(j);
                    Rating rating = ratingRepository.findByUserAndWorkoutRoutine(user, routine);
                    userItemMatrix[i][j] = (rating != null) ? rating.getRating() : 0; 
                }
            }
    
            return userItemMatrix;
        }


    public List<Double> calculateSimilarities(double[][] userItemMatrix, Long activeUserIndex) {
        List<Double> similarities = new ArrayList<>();

        int activeUserIdx = activeUserIndex.intValue();
        double[] activeUserRatings = userItemMatrix[activeUserIdx];

        for(int userIndex = 0; userIndex < userItemMatrix.length; userIndex++){
            if(userIndex != activeUserIndex){
                double similarity = calculateSimilarity(activeUserRatings, userItemMatrix[userIndex]);
                similarities.add(similarity);
            }
        }
        return similarities;
    }
    
    public List<WorkoutRoutine> generateRecommendations(double[][] userItemMatrix, List<Double> similarities,
                                                        List<WorkoutRoutine> allRoutines, Long activeUserIndex, int k) {
        List<WorkoutRoutine> recommendations = new ArrayList<>();

        // Calculate weighted average of ratings from similar users
        double weightedSum = 0;
        double similaritySum = 0;
        int activeUserIdx = activeUserIndex.intValue();
        // Iterate over users to find similar ones
        for (int userIndex = 0; userIndex < userItemMatrix.length; userIndex++) {
            if (userIndex != activeUserIndex) { // Exclude the active user
                double similarity = similarities.get(userIndex);
                double[] userRatings = userItemMatrix[userIndex];

                for (int routineIndex = 0; routineIndex < userRatings.length; routineIndex++) {
                    if (userItemMatrix[activeUserIdx][routineIndex] == 0 && userRatings[routineIndex] != 0) {
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

            for (int routineIndex = 0; routineIndex < userItemMatrix[activeUserIdx].length; routineIndex++) {
                if (userItemMatrix[activeUserIdx][routineIndex] == 0) {
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
            return 0;
        }

        return dotProduct / (magnitudeUser1 * magnitudeUser2);
        }
}