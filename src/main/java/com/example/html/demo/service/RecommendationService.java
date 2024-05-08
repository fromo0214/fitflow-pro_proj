package com.example.html.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.html.demo.generator.UseritemMatrixGenerator;
import com.example.html.demo.model.CollaborativeFilteringModel;
import com.example.html.demo.model.User;
import com.example.html.demo.model.WorkoutRoutine;

@Service
public class RecommendationService {

    @Autowired
    private UseritemMatrixGenerator matrixGenerator;
    
    @Autowired
    private CollaborativeFilteringModel filteringModel;

    public List<WorkoutRoutine> getRecommendations(List<User> allUser, List<WorkoutRoutine> allRoutines, int activeUserIndex, int k){
        double[][] userItemMatrix = matrixGenerator.generateUserItemMatrix(allUser, allRoutines);

        // double[][] similarityMatrix = filteringModel.calculateSimilarities(userItemMatrix);

        List<Double> similarites = filteringModel.calculateSimilarities(userItemMatrix, activeUserIndex);

        List<WorkoutRoutine> recommendations = filteringModel.generateRecommendations(userItemMatrix, similarites, allRoutines, activeUserIndex, k);

        return recommendations;
    }
    

}
