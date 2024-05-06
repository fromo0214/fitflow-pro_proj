package com.example.html.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.html.demo.model.Rating;
import com.example.html.demo.model.User;
import com.example.html.demo.model.Workout;
import com.example.html.demo.repository.RatingRepository;
import com.example.html.demo.repository.UserRepository;
import com.example.html.demo.repository.WorkoutRepository;
import com.example.html.demo.service.WorkoutService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
public class RatingController {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private WorkoutService workoutService;

    @PostMapping("suggested_workouts")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    
    
}
