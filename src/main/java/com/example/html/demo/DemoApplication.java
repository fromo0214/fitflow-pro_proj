package com.example.html.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.html.demo.Initializer.DataInitializer;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private DataInitializer dataInitializer;
	public static void main(String[] args) {
		
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	public void initialize(){
		dataInitializer.initializeUsers();
		dataInitializer.initializeWorkout();
		dataInitializer.initializeWorkoutRoutines();;
		dataInitializer.initializeRatings();		
	}

}
