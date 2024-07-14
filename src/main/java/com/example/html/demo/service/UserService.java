package com.example.html.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.html.demo.model.User;

import com.example.html.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    WorkoutService workoutService;

    public boolean isUsernameTaken(String username){
        return userRepository.existsByUsername(username);
    }

    public boolean isEmailTaken(String email){
        return userRepository.existsByEmail(email);
    }

    public User saveUserDetails(User user){
        return userRepository.save(user);
    } 

    public List<User> getAllUsers(){
        return  (List<User>) userRepository.findAll(); 
    }

    
}