package com.example.html.demo.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "workout")
public class Workout {

    @Id
    @Column(name = "workout_id")
    private int workoutId;

    @Column(name = "workout_name")
    private String workoutName;

    @Column(name = "workout_desc")
    private String workoutDesc;

    @Column(name = "body_part_focus")
    private String bodyPartFocus;

    //difficulty scale from 1-10 10 being the hardest
    @Column(name = "difficulty_rating")
    private double difficultyRating;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL)
    private List<SuggestedWorkouts> suggestedWorkouts;

    public Workout(){
        
    }

    public Workout(int workoutId, String workoutName, String workoutDesc, String bodyPartFocus, int difficultyRating) {
        this.workoutId = workoutId;
        this.workoutName = workoutName;
        this.workoutDesc = workoutDesc;
        this.bodyPartFocus = bodyPartFocus;
        this.difficultyRating = difficultyRating;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
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

    
}
