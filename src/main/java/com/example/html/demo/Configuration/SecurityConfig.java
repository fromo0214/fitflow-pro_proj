package com.example.html.demo.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.html.demo.service.MyUserDetailsService;

//Spring Security configuration to add user authentication in the back-end.


@Configuration //spring boot bean
@EnableWebSecurity //controls debugging support w/ spring security
public class SecurityConfig{

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Bean
    //httpsecurity object is used to configure security for HTTP requests 
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
        .authorizeHttpRequests((requests) -> requests
            .requestMatchers
            (
                "/verify", "/login", "/register","/css/**"
                , "/js/**", "/images/**", "/reset_password"
                , "/h2/**", "/home","/forgot_password").permitAll() // Allow public access to registration and static resources
            .anyRequest().authenticated()                                               //allows users without accounts to access the /register url
        )                                                                               // and css/js resources from the template, .permitall() allows access to urls without
                                                                                        // authentication
        .formLogin((form) -> form   //configures form based log in
            .loginPage("/login") //specifies the custom log in url
            .defaultSuccessUrl("/home", true)
            .permitAll() //allows all access to the url without authentication 
        )
        .logout((logout) -> logout //configures a log out functionality
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login?logout")
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
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
