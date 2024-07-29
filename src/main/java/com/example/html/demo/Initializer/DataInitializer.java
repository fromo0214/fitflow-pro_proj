package com.example.html.demo.Initializer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.html.demo.model.Meal;
import com.example.html.demo.model.User;
import com.example.html.demo.model.Workout;
import com.example.html.demo.model.WorkoutRoutine;
import com.example.html.demo.repository.WorkoutRepository;
import com.example.html.demo.repository.WorkoutRoutineRepository;
import com.example.html.demo.service.MealService;
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
    private MealService mealService;

    @Autowired
    private WorkoutRepository workoutRepository;

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

        Workout workout16 = new Workout(16L, "Incline Dumbbell Press", "Incline pressing with dumbbells", 
        "Chest", 8);
        workoutService.saveWorkout(workout16);

        Workout workout17 = new Workout(17L, "Decline Barbell Press", "Decline pressing with barbell", 
        "Chest", 7);
        workoutService.saveWorkout(workout17);

        Workout workout18 = new Workout(18L, "Pec Deck Machine", "Chest flys on pec deck machine", 
        "Chest", 6);
        workoutService.saveWorkout(workout18);

        Workout workout19 = new Workout(19L, "Push-Ups", "Bodyweight push-ups", 
        "Chest", 5);
        workoutService.saveWorkout(workout19);

        Workout workout20 = new Workout(20L, "Cable Crossovers", "Chest flys with cables at different angles", 
        "Chest", 7);
        workoutService.saveWorkout(workout20);

        Workout workout21 = new Workout(21L, "Lunges", "Walking lunges with dumbbells", 
        "Legs", 6);
        workoutService.saveWorkout(workout21);
        
        Workout workout22 = new Workout(22L, "Leg Curl", "Seated leg curl machine", 
        "Legs", 5);
        workoutService.saveWorkout(workout22);
        
        Workout workout23 = new Workout(23L, "Calf Raises", "Standing calf raises", 
        "Legs", 4);
        workoutService.saveWorkout(workout23);
        
        Workout workout24 = new Workout(24L, "Bulgarian Split Squat", "Single leg squats with rear foot elevated", 
        "Legs", 7);
        workoutService.saveWorkout(workout24);
        
        Workout workout25 = new Workout(25L, "Front Squat", "Barbell front squats", 
        "Legs", 8);
        workoutService.saveWorkout(workout25);
        
        Workout workout27 = new Workout(27L, "Jump Rope", "Continuous jump roping", 
        "Cardio", 6);
        workoutService.saveWorkout(workout27);
        
        Workout workout28 = new Workout(28L, "Cycling", "Outdoor or stationary cycling", 
        "Cardio", 7);
        workoutService.saveWorkout(workout28);
        
        Workout workout29 = new Workout(29L, "Rowing", "Rowing machine workout", 
        "Cardio", 8);
        workoutService.saveWorkout(workout29);
        
        Workout workout30 = new Workout(30L, "High-Intensity Interval Training (HIIT)", "Alternating high-intensity and low-intensity exercises", 
        "Cardio", 9);
        workoutService.saveWorkout(workout30);
        
        Workout workout31 = new Workout(31L, "Swimming", "Continuous swimming laps", 
        "Cardio", 7);
        workoutService.saveWorkout(workout31);
        
        Workout workout32 = new Workout(32L, "Elliptical", "Workout on the elliptical machine", 
        "Cardio", 6);
        workoutService.saveWorkout(workout32);
        
        Workout workout33 = new Workout(33L, "Stair Climbing", "Climbing stairs or using a stair machine", 
        "Cardio", 7);
        workoutService.saveWorkout(workout33);

        Workout workout34 = new Workout(34L, "Hammer Curls", "Dumbbell hammer curls", 
        "Arms", 6);
        workoutService.saveWorkout(workout34);
        
        Workout workout35 = new Workout(35L, "Overhead Tricep Extension", "Dumbbell overhead tricep extension", 
        "Arms", 5);
        workoutService.saveWorkout(workout35);
        
        Workout workout36 = new Workout(36L, "Concentration Curls", "Seated concentration curls with dumbbell", 
        "Arms", 5);
        workoutService.saveWorkout(workout36);
        
        Workout workout37 = new Workout(37L, "Bent-Over Rows", "Barbell bent-over rows", 
        "Back", 7);
        workoutService.saveWorkout(workout37);
        
        Workout workout38 = new Workout(38L, "Single-Arm Dumbbell Rows", "Single-arm rows with dumbbell", 
        "Back", 6);
        workoutService.saveWorkout(workout38);
        
        Workout workout39 = new Workout(39L, "Seated Cable Rows", "Seated cable row machine", 
        "Back", 8);
        workoutService.saveWorkout(workout39);
        
        Workout workout40 = new Workout(40L, "T-Bar Rows", "T-bar row machine or barbell T-bar rows", 
        "Back", 7);
        workoutService.saveWorkout(workout40);
        
        Workout workout41 = new Workout(41L, "Face Pulls", "Cable face pulls", 
        "Back", 5);
        workoutService.saveWorkout(workout41);        

    }

       public void initializeUsers(){
        User user1 = new User("test1", "hello1@gmail.com", passwordEncoder.encode("test"), 1.5);
        userService.saveUserDetails(user1);

        User user2 = new User("test2", "hello2@gmail.com", passwordEncoder.encode("test"), 1.6);
        userService.saveUserDetails(user2);

        User user3 = new User("test3", "hello3@gmail.com", passwordEncoder.encode("test"), 1.4);
        userService.saveUserDetails(user3);

        User user4 = new User("test4", "hello4@gmail.com", passwordEncoder.encode("test"), 1.8);
        userService.saveUserDetails(user4);

        User user5 = new User("test5", "hello5@gmail.com", passwordEncoder.encode("test"), 1.2);
        userService.saveUserDetails(user5);

        User user6 = new User("fromo0214", "faromo0214@gmail.com", passwordEncoder.encode("test"), 72.0, 
        150.0, 188.0, 200.0, 1.9, "male");
        userService.saveUserDetails(user6);
    }

     public void initializeWorkoutRoutines() {
         // Get all workouts
         List<Workout> workouts = workoutService.getAllWorkouts();

         //chest routine 1
         Workout chestWorkout1 = workoutRepository.findByWorkoutId(1L);
         Workout chestWorkout2 = workoutRepository.findByWorkoutId(9L);
         Workout chestWorkout3 = workoutRepository.findByWorkoutId(11L);
         Workout chestWorkout4 = workoutRepository.findByWorkoutId(16L);
         List<Workout> chestRoutine1 = new ArrayList<Workout>();
         chestRoutine1.add(chestWorkout1);
         chestRoutine1.add(chestWorkout2);
         chestRoutine1.add(chestWorkout3);
         chestRoutine1.add(chestWorkout4);
         
         //chest routine 2
        List<Workout> chestRoutine2 = new ArrayList<Workout>();
        Workout chestWorkout5 = workoutRepository.findByWorkoutId(17L);
        Workout chestWorkout6 = workoutRepository.findByWorkoutId(18L);
        Workout chestWorkout7 = workoutRepository.findByWorkoutId(19L);
        Workout chestWorkout8 = workoutRepository.findByWorkoutId(20L);
        chestRoutine2.add(chestWorkout5);
        chestRoutine2.add(chestWorkout6);
        chestRoutine2.add(chestWorkout7);
        chestRoutine2.add(chestWorkout8);

        //leg routine 1
        List <Workout> legRoutine1 = new ArrayList<Workout>();
        Workout legWorkout1 = workoutRepository.findByWorkoutId(2L); 
        Workout legWorkout2 = workoutRepository.findByWorkoutId(12L); 
        Workout legWorkout3 = workoutRepository.findByWorkoutId(14L); 
        Workout legWorkout4 = workoutRepository.findByWorkoutId(21L); 
        legRoutine1.add(legWorkout1);
        legRoutine1.add(legWorkout2);
        legRoutine1.add(legWorkout3);
        legRoutine1.add(legWorkout4);

        //leg routine 2
        List <Workout> legRoutine2 = new ArrayList<Workout>();
        Workout legWorkout5 = workoutRepository.findByWorkoutId(22L); 
        Workout legWorkout6 = workoutRepository.findByWorkoutId(23L); 
        Workout legWorkout7 = workoutRepository.findByWorkoutId(24L); 
        Workout legWorkout8 = workoutRepository.findByWorkoutId(25L); 
        legRoutine2.add(legWorkout5);
        legRoutine2.add(legWorkout6);
        legRoutine2.add(legWorkout7);
        legRoutine2.add(legWorkout8);

        //cardio routine 1
        List <Workout> cardioRoutine1 = new ArrayList<Workout>();
        Workout cardioWorkout1 = workoutRepository.findByWorkoutId(7L);
        Workout cardioWorkout2 = workoutRepository.findByWorkoutId(27L);
        Workout cardioWorkout3 = workoutRepository.findByWorkoutId(28L);
        Workout cardioWorkout4 = workoutRepository.findByWorkoutId(29L);
        cardioRoutine1.add(cardioWorkout1);
        cardioRoutine1.add(cardioWorkout2);
        cardioRoutine1.add(cardioWorkout3);
        cardioRoutine1.add(cardioWorkout4);

        //cardio routine 2
        List <Workout> cardioRoutine2 = new ArrayList<Workout>();
        Workout cardioWorkout5 = workoutRepository.findByWorkoutId(30L);
        Workout cardioWorkout6 = workoutRepository.findByWorkoutId(31L);
        Workout cardioWorkout7 = workoutRepository.findByWorkoutId(32L);
        Workout cardioWorkout8 = workoutRepository.findByWorkoutId(33L);
        cardioRoutine2.add(cardioWorkout5);
        cardioRoutine2.add(cardioWorkout6);
        cardioRoutine2.add(cardioWorkout7);
        cardioRoutine2.add(cardioWorkout8);   
        
        //arm routine 1
        List <Workout> armRoutine1 = new ArrayList<Workout>();
        Workout armWorkout1 = workoutRepository.findByWorkoutId(3L);
        Workout armWorkout2 = workoutRepository.findByWorkoutId(4L);
        Workout armWorkout3 = workoutRepository.findByWorkoutId(5L);
        Workout armWorkout4 = workoutRepository.findByWorkoutId(8L);
        armRoutine1.add(armWorkout1);
        armRoutine1.add(armWorkout2);
        armRoutine1.add(armWorkout3);
        armRoutine1.add(armWorkout4);

        //arm routine 2
        List <Workout> armRoutine2 = new ArrayList<Workout>();
        Workout armWorkout5 = workoutRepository.findByWorkoutId(10L);
        Workout armWorkout6 = workoutRepository.findByWorkoutId(34L);
        Workout armWorkout7 = workoutRepository.findByWorkoutId(35L);
        Workout armWorkout8 = workoutRepository.findByWorkoutId(36L);
        armRoutine2.add(armWorkout5);
        armRoutine2.add(armWorkout6);
        armRoutine2.add(armWorkout7);
        armRoutine2.add(armWorkout8);

        //back routine 1
        List <Workout> backRoutine1 = new ArrayList<Workout>();
        Workout backWorkout1 = workoutRepository.findByWorkoutId(6L);
        Workout backWorkout2 = workoutRepository.findByWorkoutId(13L);
        Workout backWorkout3 = workoutRepository.findByWorkoutId(15L);
        Workout backWorkout4 = workoutRepository.findByWorkoutId(37L);
        backRoutine1.add(backWorkout1);
        backRoutine1.add(backWorkout2);
        backRoutine1.add(backWorkout3);
        backRoutine1.add(backWorkout4);

        //back routine 2
        List <Workout> backRoutine2 = new ArrayList<Workout>();
        Workout backWorkout5 = workoutRepository.findByWorkoutId(38L);
        Workout backWorkout6 = workoutRepository.findByWorkoutId(39L);
        Workout backWorkout7 = workoutRepository.findByWorkoutId(40L);
        Workout backWorkout8 = workoutRepository.findByWorkoutId(41L);
        backRoutine2.add(backWorkout5);
        backRoutine2.add(backWorkout6);
        backRoutine2.add(backWorkout7);
        backRoutine2.add(backWorkout8);

         // Create workout routines
         WorkoutRoutine routine1 = new WorkoutRoutine();
         routine1.setRoutineId(1L);
         routine1.setRoutineDifficulty(7);
         routine1.setName("Chest Routine 1");
         routine1.setCategory("chest");
         routine1.setWorkouts(chestRoutine1);
         workoutRoutineRepository.save(routine1);
 
         WorkoutRoutine routine2 = new WorkoutRoutine();
         routine2.setRoutineId(2L);
         routine2.setRoutineDifficulty(3);
         routine2.setName("Chest Routine 2");
         routine2.setCategory("chest");
         routine2.setWorkouts(chestRoutine2); 
         workoutRoutineRepository.save(routine2);
 
         WorkoutRoutine routine3 = new WorkoutRoutine();
         routine3.setRoutineId(3L);
         routine3.setRoutineDifficulty(5);
         routine3.setName("Leg Routine 1");
         routine3.setCategory("legs");
         routine3.setWorkouts(legRoutine1); 
         workoutRoutineRepository.save(routine3);

         WorkoutRoutine routine4 = new WorkoutRoutine();
         routine4.setRoutineId(4L);
         routine4.setRoutineDifficulty(9);
         routine4.setName("Leg Routine 2");
         routine4.setCategory("legs");
         routine4.setWorkouts(legRoutine2);
         workoutRoutineRepository.save(routine4);

         WorkoutRoutine routine5 = new WorkoutRoutine();
         routine5.setRoutineId(5L);
         routine5.setRoutineDifficulty(4);
         routine5.setName("Cardio Routine 1");
         routine5.setCategory("cardio");
         routine5.setWorkouts(cardioRoutine1);
         workoutRoutineRepository.save(routine5);

         WorkoutRoutine routine6 = new WorkoutRoutine();
         routine6.setRoutineId(6L);
         routine6.setRoutineDifficulty(7);
         routine6.setName("Cardio Routine 2");
         routine6.setCategory("cardio");
         routine6.setWorkouts(cardioRoutine2);
         workoutRoutineRepository.save(routine6);

         WorkoutRoutine routine7 = new WorkoutRoutine();
         routine7.setRoutineId(7L);
         routine7.setRoutineDifficulty(4);
         routine7.setName("Arms Routine 1");
         routine7.setCategory("arms");
         routine7.setWorkouts(armRoutine1);
         workoutRoutineRepository.save(routine7);


         WorkoutRoutine routine8 = new WorkoutRoutine();
         routine8.setRoutineId(8L);
         routine8.setRoutineDifficulty(6);
         routine8.setName("Arms Routine 2");
         routine8.setCategory("arms");
         routine8.setWorkouts(armRoutine2);
         workoutRoutineRepository.save(routine8);

         WorkoutRoutine routine9 = new WorkoutRoutine();
         routine9.setRoutineId(9L);
         routine9.setRoutineDifficulty(4);
         routine9.setWorkouts(backRoutine1);
         routine9.setName("Back Routine 1");
         routine9.setCategory("back");
         workoutRoutineRepository.save(routine9);


         WorkoutRoutine routine10 = new WorkoutRoutine();
         routine10.setRoutineId(10L);
         routine10.setRoutineDifficulty(8);
         routine10.setWorkouts(backRoutine2);
         routine10.setName("Back Routine 2");
         routine10.setCategory("back");
         workoutRoutineRepository.save(routine10);

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

    public void initializeMeals(){
        LocalDate date = LocalDate.now();
        Meal meal1 = new Meal("hamburger", 800, "Breakfast", date, "fromo0214");
        mealService.saveMeal(meal1);
    }
}
