package com.example.html.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "workout")
public class Workout {

    @Id
    @Column(name = "workout_id")
    private Long workoutId;

    @Column(name = "workout_name")
    private String workoutName;

    @Column(name = "workout_desc")
    private String workoutDesc;

    @Column(name = "body_part_focus")
    private String bodyPartFocus;

    //difficulty scale from 1-10 10 being the hardest
    @Column(name = "difficulty_rating")
    private double difficultyRating;


    @ManyToOne
    @JoinColumn(name = "routine_id")
    private WorkoutRoutine workoutRoutine;

    public Workout(){
        
    }

    public Workout(Long workoutId, String workoutName, String workoutDesc, String bodyPartFocus, int difficultyRating) {
        this.workoutId = workoutId;
        this.workoutName = workoutName;
        this.workoutDesc = workoutDesc;
        this.bodyPartFocus = bodyPartFocus;
        this.difficultyRating = difficultyRating;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getWorkoutDesc() {
        return workoutDesc;
    }

    public void setWorkoutDesc(String workoutDesc) {
        this.workoutDesc = workoutDesc;
    }

    public String getBodyPartFocus() {
        return bodyPartFocus;
    }

    public void setBodyPartFocus(String bodyPartFocus) {
        this.bodyPartFocus = bodyPartFocus;
    }

    public double getDifficultyRating() {
        return difficultyRating;
    }

    public void setDifficultyRating(int difficultyRating) {
        this.difficultyRating = difficultyRating;
    }

    public void setDifficultyRating(double difficultyRating) {
        this.difficultyRating = difficultyRating;
    }

    public WorkoutRoutine getWorkoutRoutine() {
        return workoutRoutine;
    }

    public void setWorkoutRoutine(WorkoutRoutine workoutRoutine) {
        this.workoutRoutine = workoutRoutine;
    }

    
}
