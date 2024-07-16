package com.example.html.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Meal {


    @Id
    private String mealName;
    private int calories;
    private String mealType; //breakfast, lunch, and dinner
    private LocalDate date;
    private String username;

    public Meal(){

    }

    public Meal(String mealName, int calories, String mealType, LocalDate date, String username) {
        this.mealName = mealName;
        this.calories = calories;
        this.mealType = mealType;
        this.date = date;
        this.username = username;
    }
   
    public String getMealName() {
        return mealName;
    }
    public void setMealName(String mealName) {
        this.mealName = mealName;
    }
    public int getCalories() {
        return calories;
    }
    public void setCalories(int calories) {
        this.calories = calories;
    }
    public String getMealType() {
        return mealType;
    }
    public void setMealType(String mealType) {
        this.mealType = mealType;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
