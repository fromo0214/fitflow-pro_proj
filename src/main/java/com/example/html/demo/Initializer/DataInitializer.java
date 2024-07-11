package com.example.html.demo.Initializer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.html.demo.model.User;
import com.example.html.demo.model.Workout;
import com.example.html.demo.model.WorkoutRoutine;
import com.example.html.demo.repository.WorkoutRoutineRepository;
import com.example.html.demo.service.RatingService;
import com.example.html.demo.service.UserService;
import com.example.html.demo.service.WorkoutService;
import com.example.html.demo.service.WorkoutRoutineService;

@Component
public class DataInitializer {
    @Autowired
    private WorkoutService workoutService;

    @Autowired 
    private UserService userService;

    @Autowired
    private WorkoutRoutineRepository workoutRoutineRepository;

    @Autowired
    private WorkoutRoutineService workoutRoutineService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RatingService ratingService;

        public void initializeWorkout() {
        Workout workout1 = new Workout(1L, "Barbell bench press", "Chest pressing with barbell", 
        "Chest", 7);
        workoutService.saveWorkout(workout1);

        Workout workout2 = new Workout(2L, "Barbell Squat", "Bar bell squats.", 
        "Legs", 7);
        workoutService.saveWorkout(workout2);

        Workout workout3 = new Workout(3L, "Dumbbell Curls", "Dumb bell curls.", 
        "Arms", 3);
        workoutService.saveWorkout(workout3);

        Workout workout4 = new Workout(4L, "Tricep Pushdown", "Rope push downs.", 
        "Arms", 3);
        workoutService.saveWorkout(workout4);

        Workout workout5 = new Workout(5L, "Skull Crushers", "Incline ez bar skull crushers.", 
        "Arms", 8);
        workoutService.saveWorkout(workout5);

        Workout workout6 = new Workout(6L, "Lateral Pulls", "Bar lateral pull-downs", 
        "Back", 6);
        workoutService.saveWorkout(workout6);
    
        Workout workout7 = new Workout(7L, "Intense Cardio", "Sprints on treadmill, with 20 second break in between.", 
        "Cardio", 10);
        workoutService.saveWorkout(workout7);

        Workout workout8 = new Workout(8L, "Shoulder Press", "Seated dumb bell shoulder press", 
        "Arms", 5);
        workoutService.saveWorkout(workout8);

        Workout workout9 = new Workout(9L, "Chest Fly", "Chest flys with cables", 
        "Chest", 4);
        workoutService.saveWorkout(workout9);

        Workout workout10 = new Workout(10L, "Preacher Curl", "EZ bar seated preacher curls", 
        "Arms", 4);
        workoutService.saveWorkout(workout10);

        Workout workout11 = new Workout(11L, "Bar Dip", "Dips holding on to the bar", 
        "Chest", 9);
        workoutService.saveWorkout(workout11);

        Workout workout12 = new Workout(12L, "Leg Press", "Seated leg press machine.", 
        "Legs", 4);
        workoutService.saveWorkout(workout12);

        Workout workout13 = new Workout(13L, "Bar Pull-ups", "Pull ups hanging off bar.", 
        "Back", 8);
        workoutService.saveWorkout(workout13);

        Workout workout14 = new Workout(14L, "Leg Extension", "Seated leg extension machine.", 
        "Legs", 3);
        workoutService.saveWorkout(workout14);

        Workout workout15 = new Workout(15L, "Deadlift", "Barbell dead lifts", 
        "Back", 10);
        workoutService.saveWorkout(workout15);

    }

       public void initializeUsers(){
        User user1 = new User("test1", "hello1@gmail.com", passwordEncoder.encode("test"), 5.0);
        userService.saveUserDetails(user1);

        User user2 = new User("test2", "hello2@gmail.com", passwordEncoder.encode("test"), 5.0);
        userService.saveUserDetails(user2);

        User user3 = new User("test3", "hello3@gmail.com", passwordEncoder.encode("test"), 4.0);
        userService.saveUserDetails(user3);

        User user4 = new User("test4", "hello4@gmail.com", passwordEncoder.encode("test"), 5.0);
        userService.saveUserDetails(user4);

        User user5 = new User("test5", "hello5@gmail.com", passwordEncoder.encode("test"), 2.0);
        userService.saveUserDetails(user5);
    }

     public void initializeWorkoutRoutines() {
         // Get all workouts
         List<Workout> workouts = workoutService.getAllWorkouts();

         // Create workout routines
         WorkoutRoutine routine1 = new WorkoutRoutine();
         routine1.setRoutineId(1L);
         routine1.setRoutineDifficulty(7);
         routine1.setName("Routine 1");
         routine1.setWorkouts(workouts.subList(0, 5)); // Adds first 5 workouts
         workoutRoutineRepository.save(routine1);
 
         WorkoutRoutine routine2 = new WorkoutRoutine();
         routine2.setRoutineId(2L);
         routine2.setRoutineDifficulty(3);
         routine2.setName("Routine 2");
         routine2.setWorkouts(workouts.subList(5, 10)); // Adds next 5 workouts
         workoutRoutineRepository.save(routine2);
 
         WorkoutRoutine routine3 = new WorkoutRoutine();
         routine3.setRoutineId(3L);
         routine3.setRoutineDifficulty(5);
         routine3.setName("Routine 3");
         routine3.setWorkouts(workouts.subList(10, 15)); 
         workoutRoutineRepository.save(routine3);
    }

    public void initializeRatings() {
        // Retrieve users and workout routines from the database
        List<User> users = userService.getAllUsers();// Retrieve users from UserRepository
        List<WorkoutRoutine> routines = workoutRoutineService.getAllRoutines();// Retrieve workout routines from WorkoutRoutineRepository

        // Generate and save ratings for each user and workout routine combination
        for (User user : users) {
            for (WorkoutRoutine routine : routines) {
                // Generate a random rating between 1 and 5
                double ratingValue = Math.random() * 5 + 1;
                // Save the rating
                ratingService.saveRating(user, routine, ratingValue);
            }
        }
    }

}
