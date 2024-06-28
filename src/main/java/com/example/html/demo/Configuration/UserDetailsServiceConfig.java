package com.example.html.demo.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.html.demo.model.User;
import com.example.html.demo.repository.UserRepository;
import com.example.html.demo.security.CustomUserDetails;

@Configuration
public class UserDetailsServiceConfig {
    @Autowired
    private UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        // Lambda Expression: Defines the method that Spring Security will call to load user details by username.
        return username -> {
            User user = userRepository.findByUsername(username);
            if(user == null){
                throw new UsernameNotFoundException("User not found");
            }
            return new CustomUserDetails(user);
        };
    }
}
