package com.example.html.demo.Initializer;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.example.html.demo.service.WorkoutService;

import jakarta.annotation.PostConstruct;

@Component
public class WorkoutInitializer{
    @Autowired
    private WorkoutService workoutService;

    @PostConstruct
    public void initialize(){
        workoutService.initializeWorkout();
    }

}
