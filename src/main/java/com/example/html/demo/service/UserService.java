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

    public void initializeUsers(){
        User user1 = new User("test1", "hello@gmail.com", "test", 5.0);
        saveUserDetails(user1);

        User user2 = new User("test2", "hello@gmail.com", "test", 5.0);
        saveUserDetails(user2);

        User user3 = new User("test3", "hello@gmail.com", "test", 4.0);
        saveUserDetails(user3);

        User user4 = new User("test4", "hello@gmail.com", "test", 5.0);
        saveUserDetails(user4);

        User user5 = new User("test5", "hello@gmail.com", "test", 2.0);
        saveUserDetails(user5);

        User user6 = new User("test6", "hello@gmail.com", "test", 5.0);
        saveUserDetails(user6);

        User user7 = new User("test7", "hello@gmail.com", "test", 5.0);
        saveUserDetails(user7);

        User user8 = new User("test8", "hello@gmail.com", "test", 10.0);
        saveUserDetails(user8);

        User user9 = new User("test9", "hello@gmail.com", "test", 5.0);
        saveUserDetails(user9);

        User user10 = new User("test10", "hello@gmail.com", "test", 5.0);
        saveUserDetails(user10);

        User user11 = new User("test11", "hello@gmail.com", "test", 4.0);
        saveUserDetails(user11);

        User user12 = new User("test12", "hello@gmail.com", "test", 5.0);
        saveUserDetails(user12);

        User user13 = new User("test13", "hello@gmail.com", "test", 5.0);
        saveUserDetails(user13);

        User user14 = new User("test14", "hello@gmail.com", "test", 3.0);
        saveUserDetails(user14);

        User user15 = new User("test15", "hello@gmail.com", "test", 5.0);
        saveUserDetails(user15);

        User user16 = new User("test16", "hello@gmail.com", "test", 9.0);
        saveUserDetails(user16);

        User user17 = new User("test17", "hello@gmail.com", "test", 8.0);
        saveUserDetails(user17);

        User user18 = new User("test18", "hello@gmail.com", "test", 5.0);
        saveUserDetails(user18);

        User user19 = new User("test19", "hello@gmail.com", "test", 5.0);
        saveUserDetails(user19);

        User user20 = new User("test20", "hello@gmail.com", "test", 7.0);
        saveUserDetails(user20);

        User user21 = new User("test21", "hello@gmail.com", "test", 5.0);
        saveUserDetails(user21);

        User user22 = new User("test22", "hello@gmail.com", "test", 5.0);
        saveUserDetails(user22);

    }



    public User saveUserDetails(User user){
        return userRepository.save(user);
    } 

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public double getExperienceLevelByUsername(String username){
        User user = findByUsername(username);
        if(user != null){
            return user.getExperienceLevel();
        }else{
            return 0.0;
        }

    }

    public Long getUserIdByUsername(String username){
        User user = userRepository.findByUsername(username);
        if(user != null){
            return user.getUserId();
        }else{
            throw new RuntimeException("User not found for username: " + username);
        }
    }

    public User getUserById(int userId) {
        User user = null;
        List<User> users = getAllUsers();
        for(User u : users){
            if(u.getUserId() == userId){
                user = u;
                break;
            }
        }
        return user;
    }

    public List<User> getAllUsers(){
        return  (List<User>) userRepository.findAll(); 
    }

    
}