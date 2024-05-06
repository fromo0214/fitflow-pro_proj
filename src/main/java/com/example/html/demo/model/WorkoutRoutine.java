package com.example.html.demo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "routine_t")
public class WorkoutRoutine {

    @Id
    @Column(name = "routineId")
    private Long routineId;

    @Column(name = "routine_name")
    private String name;

    @Column(name = "routine_difficulty")
    private double routineDifficulty;

    @OneToMany
    private List<Workout> workouts;

    
    public WorkoutRoutine(Long routineId, String name, List<Workout> workouts) {
        this.routineId = routineId;
        this.name = name;
        this.workouts = workouts;
    }

    public WorkoutRoutine() {
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<Workout> getWorkouts() {
        return workouts;
    }


    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public Long getRoutineId() {
        return routineId;
    }

    public void setRoutineId(Long routineId) {
        this.routineId = routineId;
    }

    public double getRoutineDifficulty() {
        return routineDifficulty;
    }

    public void setRoutineDifficulty(double routineDifficulty) {
        this.routineDifficulty = routineDifficulty;
    }
    
}
