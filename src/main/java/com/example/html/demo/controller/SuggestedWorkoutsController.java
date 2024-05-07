package com.example.html.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.html.demo.model.Rating;
import com.example.html.demo.repository.RatingRepository;
import com.example.html.demo.service.RatingService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class SuggestedWorkoutsController {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RatingService ratingService;

    @PostMapping("suggested_workouts")
    public String postMethodName(@RequestParam("userId") Long userId,
    @RequestParam("routineId") Long routineId,
    @RequestParam("rating") double ratingValue) {
        
        ratingService.saveRating(userId, routineId, ratingValue);

        return "suggested_workouts";
    }
    
    @GetMapping("suggested_workouts")
    public String getMethodName() {
        return "suggested_workouts";
    }
    
}
