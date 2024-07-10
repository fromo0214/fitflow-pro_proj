package com.example.html.demo.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//Spring Security configuration to add user authentication in the back-end.


@Configuration //spring boot bean
@EnableWebSecurity //controls debugging support w/ spring security
public class SecurityConfig{

    @Bean
    //httpsecurity object is used to configure security for HTTP requests 
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
        .authorizeHttpRequests((requests) -> requests
            .requestMatchers("/login", "/register","/css/**", "/js/**", "/images/**", "/h2/**", "/home").permitAll() // Allow public access to registration and static resources
            .anyRequest().authenticated()                                               //allows users without accounts to access the /register url
        )                                                                               // and css/js resources from the template, .permitall() allows access to urls without
                                                                                        // authentication
        .formLogin((form) -> form   //configures form based log in
            .loginPage("/login") //specifies the custom log in url
            .defaultSuccessUrl("/home", true)
            .permitAll() //allows all access to the url without authentication 
        )
        .logout((logout) -> logout //configures a log out functionality
            .permitAll()
        )
        .csrf((csrf) -> csrf
        .ignoringRequestMatchers("/h2/**") //used to be able to access h2 database
        )
        .headers((header) -> header
            .frameOptions((frameOptions) -> frameOptions.disable())
        );
        
    return http.build(); //builds and returns the 'SecurityFilterChain'
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
