package com.example.html.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.html.demo.model.WorkoutRoutine;
import com.example.html.demo.service.WorkoutRoutineService;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RatingController {

    @Autowired
    private WorkoutRoutineService routineService;


    @GetMapping("rate_workouts")
    public String getMethodName(Model model) {
        List<WorkoutRoutine> routines = routineService.getAllRoutines();

        model.addAttribute("routines", routines);

        return "rate_workouts";
    }
    
}
