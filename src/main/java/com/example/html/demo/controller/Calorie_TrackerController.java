package com.example.html.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.html.demo.model.Meal;
import com.example.html.demo.service.MealService;

@Controller
public class Calorie_TrackerController {

    private static final Logger logger = LoggerFactory.getLogger(Calorie_TrackerController.class);

    @Autowired
    private MealService mealService;

    @GetMapping("/calorie_tracker")
    public String calorieTracker(@RequestParam(required = false) LocalDate date, Model model) {
        if (date == null) {
            date = LocalDate.now();
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        logger.debug("Fetching meals for user: {} on date: {}", username, date);


        List<Meal> meals = mealService.getMealsByDateAndUsername(date, username);
        int totalCalories = meals.stream().mapToInt(Meal::getCalories).sum();

        model.addAttribute("meals", meals);
        model.addAttribute("totalCalories", totalCalories);
        model.addAttribute("date", date);
        model.addAttribute("meal", new Meal());

        logger.debug("Total calories for date {}: {}", date, totalCalories);
        return "calorie_tracker";
    }

    @PostMapping("/addMeal")
    public String addMeal(@ModelAttribute Meal meal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        meal.setUsername(username);
        logger.debug("Adding meal for user: {} with details: {}", username, meal);
        
        mealService.saveMeal(meal);
        logger.debug("Meal saved successfully: {}", meal);
        return "redirect:/calorie_tracker?date=" + meal.getDate();
    }
}
