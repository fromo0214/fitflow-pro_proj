package com.example.html.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.html.demo.model.WeightChange;

@Repository
public interface WeightChangeRepository extends CrudRepository<WeightChange, Long>{
    List<WeightChange> findByUser_Id(Long id);
}
