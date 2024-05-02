package com.example.html.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.html.demo.model.Workout;


@Repository
public interface WorkoutRepository extends CrudRepository<Workout, Integer>{
    Workout findByDifficultyRating(double difficultyRating);

    @Query("SELECT w.difficultyRating FROM Workout w")
    List<Double> findDifficultyRatings();
}
