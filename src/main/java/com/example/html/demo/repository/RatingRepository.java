package com.example.html.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.html.demo.model.Rating;
import com.example.html.demo.model.User;
import com.example.html.demo.model.WorkoutRoutine;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long>{

    @Query("SELECT r FROM Rating r WHERE r.user = :user AND r.workoutRoutine = :workoutRoutine")
    Rating findByUserAndWorkoutRoutine(User user, WorkoutRoutine workoutRoutine);

}
