package com.example.html.demo.service;

import java.time.LocalDate;
import java.util.List;

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
}
