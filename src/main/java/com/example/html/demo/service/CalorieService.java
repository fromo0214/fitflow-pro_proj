// To calculate the number of calories someone should consume based on their goal weight, 
//you can use the Harris-Benedict Equation to estimate their Basal Metabolic Rate (BMR), 
//and then adjust this number based on their activity level and weight goals.

// Calculate Basal Metabolic Rate (BMR):

// Use the Harris-Benedict Equation to estimate BMR.

package com.example.html.demo.service;

import org.springframework.stereotype.Service;

@Service
public class CalorieService {

    public double inchesToCm(double inches){
        return inches * 2.54;
    }

    public double poundsToKg(double pounds){
        return pounds * 0.453592;
    }

    public double calculateBMR(double weightInPounds, double heightInInches, int age, String gender){
        if(gender == null){
            gender = "male";
        }

        double weight = poundsToKg(weightInPounds);
        double height = inchesToCm(heightInInches);

        if(gender.equals("male")){
            return 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        }else{
            return 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }
    }

    public double calculateTDEE(double bmr, double experienceLevel){
        return bmr * experienceLevel;
    }
    
    public double calculateCalorieIntake(double currentWeight, double goalWeight, double tdee){
        if(goalWeight < currentWeight){
            //to lose weight
            return tdee-500;
        }
        else if(goalWeight > currentWeight){
            //to gain weight
            return tdee+500;
        }else{
            //maintain weight
            return tdee;
        }
    }  

    public String calorieMessage(double currentWeight, double goalWeight){
        if(currentWeight > goalWeight){
            return "Caloric Deficit";
        }else{
            return "Caloric Surplus";
        }
    }

    public String calorieGoal(int totalCalories, double goalCalories){
        if(totalCalories >= goalCalories){
            return "Calorie goal hit good job!";
        }
        return "";
    }

    public String getActivityCategory(double experienceLevel){
        if(experienceLevel < 1.375){
            return "Sedentary (little or no exercise)";
        }
        else if(experienceLevel >= 1.375 && experienceLevel <= 1.54){
            return "Lightly Active (little exercise/sports 1-3 days/week)";
        }
        else if(experienceLevel >= 1.55 && experienceLevel <= 1.724){
            return "Moderately active (moderate exercise/sports 3-5 days/week)";
        }
        else if(experienceLevel >= 1.725 && experienceLevel <= 1.8){
            return "Very active (hard exercise/sports 6-7 days a week)";
        }
        else{
            return "Super active (very hard exercise/sports and a physical job)";
        }
    }
}
