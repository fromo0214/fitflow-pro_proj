package com.example.html.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rating_table")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ratingId")
    private Long ratingId;

    @ManyToOne
    private User user;

    @ManyToOne
    private WorkoutRoutine workoutRoutine;

    @Column(name = "rating")
    private double rating;

    public Rating(){

    }

    public Rating(Long userId, Long routineId, double rating){
        this.user = new User();
        this.user.setUserId(userId);

        this.workoutRoutine = new WorkoutRoutine();
        this.workoutRoutine.setRoutineId(routineId);

        this.rating = rating;
    }

    public Long getId() {
        return ratingId;
    }

    public void setId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WorkoutRoutine getWorkoutRoutine() {
        return workoutRoutine;
    }

    public void setWorkoutRoutine(WorkoutRoutine workout) {
        this.workoutRoutine = workout;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


    
}
