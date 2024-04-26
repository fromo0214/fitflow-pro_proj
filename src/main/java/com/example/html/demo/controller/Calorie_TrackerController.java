package com.example.html.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Calorie_TrackerController {

    @GetMapping("/calorie_tracker")
    public String calorieTracker(){
        return "calorie_tracker";
    }

}
