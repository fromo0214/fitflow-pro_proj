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

    @Autowired WorkoutService workoutService;

    // public void initializeUsers(){
    //     User user1 = new User("test1", "hello@gmail.com", "test", 5.0);
    //     saveUserDetails(user1);

    //     User user2 = new User("test2", "hello@gmail.com", "test", 5.0);
    //     saveUserDetails(user2);

    //     User user3 = new User("test3", "hello@gmail.com", "test", 4.0);
    //     saveUserDetails(user3);

    //     User user4 = new User("test4", "hello@gmail.com", "test", 5.0);
    //     saveUserDetails(user4);

    //     User user5 = new User("test5", "hello@gmail.com", "test", 2.0);
    //     saveUserDetails(user5);

    // }

    public User saveUserDetails(User user){
        userRepository.save(user);
        return user;
    } 

    public List<User> getAllUsers(){
        return  (List<User>) userRepository.findAll(); 
    }

    
}