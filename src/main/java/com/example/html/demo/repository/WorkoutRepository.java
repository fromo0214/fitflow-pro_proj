package com.example.html.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.html.demo.model.Workout;

@Repository
public interface WorkoutRepository extends CrudRepository<Workout, Integer>{

}
