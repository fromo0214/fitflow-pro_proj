package com.example.html.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.html.demo.model.Meal;

@Repository
public interface MealRepository extends CrudRepository<Meal, String>{
    List<Meal> findByDateAndUsername(LocalDate date, String username);
}
