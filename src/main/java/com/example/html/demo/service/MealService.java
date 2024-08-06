package com.example.html.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.html.demo.model.Meal;
import com.example.html.demo.repository.MealRepository;
// 
@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;

    public List<Meal> getMealsByDateAndUsername(LocalDate localDate, String username){
        return mealRepository.findByDateAndUsername(localDate, username);
    }

    public Meal saveMeal(Meal meal){
        return mealRepository.save(meal);
    }

    public Map<String, List<Meal>> groupMealsByMealType(List<Meal> meals) {
        Map<String,List<Meal>> groupedMeals = new HashMap<>();

        for(Meal meal : meals){
            String mealType = meal.getMealType();
            //if the grouped workouts list doesn't contain the body part
            if(!groupedMeals.containsKey(mealType)){
                //adds the body part as a key to the map and a empty list.
                groupedMeals.put(mealType, new ArrayList<>());
            }
            //here the map adds the workouts to the list
            groupedMeals.get(mealType).add(meal);
        }
        return groupedMeals;
    }
}
