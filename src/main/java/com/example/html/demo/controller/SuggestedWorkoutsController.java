package com.example.html.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.html.demo.model.LinearRegressionModel;
import com.example.html.demo.model.User;
import com.example.html.demo.model.Workout;
import com.example.html.demo.repository.UserRepository;
import com.example.html.demo.repository.WorkoutRepository;
import com.example.html.demo.service.UserService;
import com.example.html.demo.service.WorkoutService;




@Controller
public class SuggestedWorkoutsController {
    @Autowired
    private UserService userService;

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkoutRepository workoutRepository;

    @GetMapping("/suggested_workouts")
    public String getSuggestedWorkouts(Model model, @RequestParam(name = "userId", required = false) Integer userId) {
        if (userId == null) {
            // Handle the case where userId is null
            System.out.println("User id is null");
            return "redirect:/"; 
        }
    
        User user = userService.getUserById(userId);
        if (user == null) {
            System.out.println("User is not found");
            // Handle the case where user is not found
            return "redirect:/error"; // Redirect to error page or handle appropriately
        }

        if (user.getExperienceLevel() == null) {
            // You need to set the experienceLevel field here.
            // For example, you can set it to a default value or retrieve it from another source.
            user.setExperienceLevel(5.0); // Set it to a default value for now
        }
    
        List<Workout> allWorkouts = workoutService.getAllWorkouts();

        // Gather user's experience level and difficulty ratings from database
        List<Double> experienceLevels = userRepository.findExperienceLevelsByUserId(userId);
        List<Double> difficultyRatings = workoutRepository.findDifficultyRatings();

        // Train the linear regression model
        LinearRegressionModel regressionModel = new LinearRegressionModel();
        regressionModel.train(experienceLevels, difficultyRatings);

        // Predict difficulty rating for user's experience level
        double predictedRating = regressionModel.predict(user.getExperienceLevel());

        // Suggest workouts based on predicted difficulty rating
        List<Workout> suggestedWorkouts = regressionModel.suggestWorkouts(user.getExperienceLevel(), allWorkouts);

        model.addAttribute("suggestedWorkouts", suggestedWorkouts);
        model.addAttribute("predictedRating", predictedRating);

        return "suggested_workouts";
    }
    

   
    
}
