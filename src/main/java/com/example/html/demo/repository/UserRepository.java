package com.example.html.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.html.demo.model.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    User findByUserId(Long userId);

    User findByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @Query("SELECT u.experienceLevel FROM User u WHERE u.userId = :userId")
    List<Double> findExperienceLevelsByUserId(Long userId);
}
