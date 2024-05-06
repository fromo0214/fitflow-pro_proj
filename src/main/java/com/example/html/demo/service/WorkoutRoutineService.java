package com.example.html.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.html.demo.model.Workout;
import com.example.html.demo.model.WorkoutRoutine;
import com.example.html.demo.repository.WorkoutRoutineRepository;

@Service
public class WorkoutRoutineService {

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private WorkoutRoutineRepository workoutRoutineRepository;

    public void initializeWorkoutRoutines() {
         // Get all workouts
         List<Workout> workouts = workoutService.getAllWorkouts();

         // Create workout routines
         WorkoutRoutine routine1 = new WorkoutRoutine();
         routine1.setRoutineId(1L);
         routine1.setName("Routine 1");
         routine1.setWorkouts(workouts.subList(0, 5)); // Add first 5 workouts
         workoutRoutineRepository.save(routine1);
 
         WorkoutRoutine routine2 = new WorkoutRoutine();
         routine2.setRoutineId(2L);
         routine2.setName("Routine 2");
         routine2.setWorkouts(workouts.subList(5, 10)); // Add next 5 workouts
         workoutRoutineRepository.save(routine2);
 
         WorkoutRoutine routine3 = new WorkoutRoutine();
         routine3.setRoutineId(3L);
         routine3.setName("Routine 3");
         routine3.setWorkouts(workouts.subList(10, 15)); // Add next 5 workouts
         workoutRoutineRepository.save(routine3);
    }

    public List<WorkoutRoutine> getAllRoutines(){
        return (List<WorkoutRoutine>) workoutRoutineRepository.findAll();
    }
}
