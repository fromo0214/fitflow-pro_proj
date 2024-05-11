package com.example.html.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.html.demo.model.CollaborativeFilteringModel;
import com.example.html.demo.model.User;
import com.example.html.demo.model.WorkoutRoutine;

@Service
public class RecommendationService {
    
    @Autowired
    private CollaborativeFilteringModel filteringModel;

    public List<WorkoutRoutine> getRecommendations(List<User> allUser, List<WorkoutRoutine> allRoutines, Long activeUserIndex, int k){
        double[][] userItemMatrix = filteringModel.fetchRatings(allUser, allRoutines);

        List<Double> similarites = filteringModel.calculateSimilarities(userItemMatrix, activeUserIndex);

        List<WorkoutRoutine> recommendations = filteringModel.generateRecommendations(userItemMatrix, similarites, allRoutines, activeUserIndex, k);

        return recommendations;
    }
    

}
