package com.example.html.demo.service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.html.demo.model.Rating;
import com.example.html.demo.model.User;
import com.example.html.demo.model.WorkoutRoutine;
import com.example.html.demo.repository.RatingRepository;

@Service
public class RecommendationService {
    

    @Autowired
    private RatingRepository ratingRepository;

  
public List<WorkoutRoutine> getRecommendations(User user, List<WorkoutRoutine> allRoutines, int limit) {
    // Fetch all ratings for the given user
    List<Rating> userRatings = ratingRepository.findByUser(user);

    // Create a map to store the total rating and count for each workout routine
    Map<WorkoutRoutine, Double> routineRatingSum = new HashMap<>();
    Map<WorkoutRoutine, Long> routineRatingCount = new HashMap<>();

    // Iterate over the user's ratings and calculate the total rating and count for each routine
    for (Rating rating : userRatings) {
        WorkoutRoutine routine = rating.getWorkoutRoutine();
        double ratingValue = rating.getRating();
        routineRatingSum.put(routine, routineRatingSum.getOrDefault(routine, 0.0) + ratingValue);
        routineRatingCount.put(routine, routineRatingCount.getOrDefault(routine, 0L) + 1);
    }

    // Calculate the average rating for each routine
    Map<WorkoutRoutine, Double> routineAverageRating = new HashMap<>();
    for (Map.Entry<WorkoutRoutine, Double> entry : routineRatingSum.entrySet()) {
        WorkoutRoutine routine = entry.getKey();
        double sum = entry.getValue();
        long count = routineRatingCount.get(routine);
        double averageRating = sum / count;
        routineAverageRating.put(routine, averageRating);
    }

   // Sort the routines by their average rating in descending order
   List<WorkoutRoutine> sortedRoutines = routineAverageRating.entrySet().stream()
   .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
   .map(Map.Entry::getKey)
   .collect(Collectors.toList());

// Ensure only one routine per category
Map<String, WorkoutRoutine> categoryToRoutine = new HashMap<>();
for (WorkoutRoutine routine : sortedRoutines) {
categoryToRoutine.putIfAbsent(routine.getCategory(), routine);
if (categoryToRoutine.size() >= limit) {
   break;
}
}

return categoryToRoutine.values().stream().collect(Collectors.toList());
}

}
