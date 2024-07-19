package com.example.html.demo.service;

import org.springframework.stereotype.Service;

@Service
public class BMIService {

    public double calculateBMI(double currentWeight, double height){
        return (currentWeight* 703)/((height*height));
    }

    public String getBMICategory(double bmi){
        if(bmi < 18.5){
            return "Underweight";
        }
        else if(bmi >= 18.5 && bmi <= 24.9){
            return "Healthy";
        }
        else if(bmi >=25.0 && bmi <= 29.9){
            return "Overweight";
        }
        else{
            return "Obese";
        }
    }

    public String getMotivationMessage(String bmiCategory){
        if(bmiCategory.equals("Underweight")){
            return "Every small step counts. Keep fueling your body with the right nutrients. You're on the path to greatness!";
        }else if(bmiCategory.equals("Healthy")){
            return "Fantastic job maintaining a healthy weight! Keep up the great work and continue to inspire those around you!";
        }else if(bmiCategory.equals("Overweight")){
            return "You're on an incredible journey towards a healthier you. Focus on small, sustainable changes, and you'll reach your goals!";
        }else{
            return "Your journey might be challenging, but your determination is powerful. Keep striving for a healthier you, one step at a time.";
        }
    }
}
