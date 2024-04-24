package com.example.html.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.html.demo.model.Workout;
import com.example.html.demo.repository.WorkoutRepository;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    public void initializeWorkout() {
        Workout workout1 = new Workout(1, "Barbell bench press", "Chest pressing with barbell", 
        "chest", 7);
        saveWorkout(workout1);

        Workout workout2 = new Workout(2, "Barbell Squat", "Bar bell squats.", 
        "legs", 7);
        saveWorkout(workout2);

        Workout workout3 = new Workout(3, "Dumbbell Curls", "Dumb bell curls.", 
        "arms", 3);
        saveWorkout(workout3);

        Workout workout4 = new Workout(4, "Tricep Pushdown", "Rope push downs.", 
        "arms", 3);
        saveWorkout(workout4);

        Workout workout5 = new Workout(5, "Skull Crushers", "Incline ez bar skull crushers.", 
        "arms", 8);
        saveWorkout(workout5);

        Workout workout6 = new Workout(6, "Lateral Pulls", "Bar lateral pull-downs", 
        "back", 6);
        saveWorkout(workout6);
    
        Workout workout7 = new Workout(7, "Intense Cardio", "Sprints on treadmill, with 20 second break in between.", 
        "cardio", 10);
        saveWorkout(workout7);

        Workout workout8 = new Workout(8, "Shoulder Press", "Seated dumb bell shoulder press", 
        "arms", 5);
        saveWorkout(workout8);

        Workout workout9 = new Workout(9, "Chest Fly", "Chest flys with cables", 
        "chest", 4);
        saveWorkout(workout9);

        Workout workout10 = new Workout(10, "Preacher Curl", "EZ bar seated preacher curls", 
        "arms", 4);
        saveWorkout(workout10);

        Workout workout11 = new Workout(11, "Bar Dip", "Dips holding on to the bar", 
        "chest", 9);
        saveWorkout(workout11);

        Workout workout12 = new Workout(12, "Leg Press", "Seated leg press machine.", 
        "legs", 4);
        saveWorkout(workout12);

        Workout workout13 = new Workout(13, "Bar Pull-ups", "Pull ups hanging off bar.", 
        "back", 8);
        saveWorkout(workout13);

        Workout workout14 = new Workout(14, "Leg Extension", "Seated leg extension machine.", 
        "legs", 3);
        saveWorkout(workout14);

        Workout workout15 = new Workout(15, "Deadlift", "Barbell dead lifts", 
        "back", 10);
        saveWorkout(workout15);

        Workout workout16 = new Workout(16, "Plank", "Hold a plank position for as long as possible.", 
        "core", 8);
        saveWorkout(workout16);

        Workout workout17 = new Workout(17, "Russian Twists", "Twist your torso from side to side while holding a weight or without any weight.", 
        "core", 6);
        saveWorkout(workout17);

        Workout workout18 = new Workout(18, "Mountain Climbers", "Start in a push-up position and alternate bringing each knee towards the chest.", 
        "core", 6);
        saveWorkout(workout18);

        Workout workout19 = new Workout(19, "Burpees", "From a standing position, drop into a squat, jump your feet back into a plank position, do a push-up, jump your feet back to your hands, and jump explosively into the air.", 
        "full body", 8);
        saveWorkout(workout19);

        Workout workout20 = new Workout(20, "Jumping Jacks", "Start with your feet together and arms at your sides, then jump to spread your legs while bringing your arms above your head. Jump again to return to the starting position.", 
        "cardio", 1);
        saveWorkout(workout20);
        
        Workout workout21 = new Workout(21, "Walking", "Simple brisk walking for cardio exercise.", 
        "cardio", 1);
        saveWorkout(workout21);

        Workout workout22 = new Workout(22, "Bodyweight Squats", "Performing squats without weights to strengthen legs.", 
        "legs", 3);
        saveWorkout(workout22);

        Workout workout23 = new Workout(23, "Push-ups", "Basic push-up exercise to strengthen the chest, shoulders, and triceps.", 
        "chest", 3);
        saveWorkout(workout23);

    }

    //saves workout to the database
    public Workout saveWorkout(Workout workout){
        return workoutRepository.save(workout);
    }

    public List<Workout> getAllWorkouts(){
        return (List<Workout>) workoutRepository.findAll();
    }

}
