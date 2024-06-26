package com.example.html.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_user")
public class User {

    //primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String passwd;

    @Column(name = "height")
    private String height;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birth_date")
    private String dob;

    @Column(name = "starting_weight")
    private int startWeight;

    @Column(name = "current_weight")
    private int currentWeight;

    @Column(name = "goal_weight")
    private int goalWeight;

    @Column(name = "experience_level")
    private Double experienceLevel;

    @OneToMany
    private List<Rating> ratedRoutines;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    User(){

    }

    public User(String username, String email, String passwd, Double experienceLevel) {
        this.username = username;
        this.email = email;
        this.passwd = passwd;
        this.experienceLevel = experienceLevel;
    }

      public void rateWorkoutRoutine(WorkoutRoutine routine, double ratingValue) {
        Rating rating = new Rating();
        rating.setUser(this);
        rating.setWorkoutRoutine(routine);
        rating.setRating(ratingValue);
        if (ratedRoutines == null) {
            ratedRoutines = new ArrayList<>();
        }
        ratedRoutines.add(rating);
    }

    public Long getUserId(){
        return userId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPasswd() {
        return passwd;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
    public int getStartWeight() {
        return startWeight;
    }
    public void setStartWeight(int startWeight) {
        this.startWeight = startWeight;
    }
    public int getCurrentWeight() {
        return currentWeight;
    }
    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }
    public int getGoalWeight() {
        return goalWeight;
    }
    public void setGoalWeight(int goalWeight) {
        this.goalWeight = goalWeight;
    }
    public Double getExperienceLevel() {
        return experienceLevel;
    }
    public void setExperienceLevel(Double experienceLevel) {
        this.experienceLevel = experienceLevel;
    }
    
    @Override
    public String toString() {
        return "User [userId=" + userId + ", username=" + username + ", email=" + email + ", passwd=" + passwd
                + ", height=" + height + ", gender=" + gender + ", dob=" + dob + ", startingWeight=" + startWeight
                + ", currentWeight=" + currentWeight + ", goalWeight=" + goalWeight + ", experienceLevel=" + experienceLevel
                + "]";
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Rating> getRatedRoutines() {
        return ratedRoutines;
    }

    public void setRatedRoutines(List<Rating> ratedRoutines) {
        this.ratedRoutines = ratedRoutines;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
}
