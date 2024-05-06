package com.example.html.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.html.demo.model.Rating;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Integer>{

}
