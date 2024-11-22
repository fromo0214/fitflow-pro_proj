package com.example.html.demo.controller;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.html.demo.model.Meal;
import com.example.html.demo.model.User;
import com.example.html.demo.repository.UserRepository;
import com.example.html.demo.service.CalorieService;
import com.example.html.demo.service.MealService;
import com.example.html.demo.service.UserService;

@Controller
public class Calorie_TrackerController {

    private static final Logger logger = LoggerFactory.getLogger(Calorie_TrackerController.class); //for debugging

    private static final DecimalFormat INTEGER_FORMAT = new DecimalFormat("#");

    @Autowired
    private MealService mealService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CalorieService calorieService;

    @Autowired
    private UserService userService;

    @GetMapping("/calorie_tracker")
    public String calorieTracker(@RequestParam(value = "date", required = false) String dateStr, Model model) {
        //getting active user details 
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username);

        //getting the dates for the calorie tracker 
        LocalDate date = (dateStr != null) ? LocalDate.parse(dateStr) : LocalDate.now();
        LocalDate previousDay = date.minusDays(1);
        LocalDate nextDay = date.plusDays(1);

        //calculating BMR from user details
        int age = userService.calculateAge(user.getDob());
        double bmr = calorieService.calculateBMR(user.getCurrentWeight(), user.getHeight(), age, user.getGender());
        double tdee = calorieService.calculateTDEE(bmr, user.getExperienceLevel());

        //calculating calorie goal to gain/lose weight for user
        double calorieIntake1LB = calorieService.calculateCalorieIntake1LB(user.getCurrentWeight(), user.getGoalWeight(), tdee);
        String formattedCalorieIntake1LB = INTEGER_FORMAT.format(calorieIntake1LB);

        //to maintain current weight the calorie goal is just the calculate tdee
        double calorieIntakeMaintainWeight = tdee;
        String formattedCalorieIntakeMaintainWeight = INTEGER_FORMAT.format(calorieIntakeMaintainWeight);

        //"Caloric Surplus" or "Caloric Deficit"
        String calorieMessage = calorieService.calorieMessage(user.getCurrentWeight(), user.getGoalWeight());

        //displays all the added meals to tracker
        List<Meal> meals = mealService.getMealsByDateAndUsername(date, username);
        Map<String, List<Meal>> groupedMeals = mealService.groupMealsByMealType(meals);

        //calculates the total calories from meals across the day
        int totalCalories = meals.stream().mapToInt(Meal::getCalories).sum();
        String goalCalorieMessage = calorieService.calorieGoal(totalCalories, calorieIntake1LB);

        model.addAttribute("meal", new Meal()); //adds the meal
        model.addAttribute("groupedMeals", groupedMeals); // displays list of meals grouped by type
        model.addAttribute("totalCalories", totalCalories); //displays total calories
        model.addAttribute("formattedCalorieIntake1LB", formattedCalorieIntake1LB); //displays calorie intake for 1 LB
        model.addAttribute("formattedCalorieIntakeMaintainWeight", 
        formattedCalorieIntakeMaintainWeight); //displays calorie intake for maintaining weight
        model.addAttribute("calorieMessage", calorieMessage);
        model.addAttribute("previousDay", previousDay.toString());
        model.addAttribute("nextDay", nextDay.toString());
        model.addAttribute("selectedDate", date);
        model.addAttribute("goalCalorieMessage", goalCalorieMessage);

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
        return "redirect:/calorie_tracker?date=" + meal.getDate().toString();
    }
}
