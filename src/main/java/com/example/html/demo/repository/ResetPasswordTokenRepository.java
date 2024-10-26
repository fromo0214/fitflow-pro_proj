package com.example.html.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.html.demo.model.ResetPasswordToken;

@Repository
public interface ResetPasswordTokenRepository extends CrudRepository<ResetPasswordToken, Long>{
        ResetPasswordToken findByToken(String token);
}
