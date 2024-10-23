package com.example.html.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "height")
    private double height;

    @Column(name = "gender")
    private String gender;

    @Column(name = "birth_date")
    private LocalDate dob;

    @Column(name = "starting_weight")
    private double startWeight;

    @Column(name = "current_weight")
    private double currentWeight;

    @Column(name = "goal_weight")
    private double goalWeight;

    @Column(name = "experience_level")
    private Double experienceLevel;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "weight_change")
    private double weightChange;

    @OneToMany
    private List<Rating> ratedRoutines;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<WeightChange> weightChanges;

    private boolean enabled = false;
    
    public User(){

    }

    public User(String username, String email, String password, Double experienceLevel) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.experienceLevel = experienceLevel;
    }

    

      public User(String username, String email, String password, double height, double startWeight, double currentWeight,
            double goalWeight, Double experienceLevel, String gender) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.height = height;
        this.startWeight = startWeight;
        this.currentWeight = currentWeight;
        this.goalWeight = goalWeight;
        this.experienceLevel = experienceLevel;
        this.gender = gender;
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
        return id;
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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public double getStartWeight() {
        return startWeight;
    }
    public void setStartWeight(double startWeight) {
        this.startWeight = startWeight;
    }
    public double getCurrentWeight() {
        return currentWeight;
    }
    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }
    public double getGoalWeight() {
        return goalWeight;
    }
    public void setGoalWeight(double goalWeight) {
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
        return "User [userId=" + id + ", username=" + username + ", email=" + email + ", passwd=" + password
                + ", height=" + height + ", gender=" + gender + ", dob=" + dob + ", startingWeight=" + startWeight
                + ", currentWeight=" + currentWeight + ", goalWeight=" + goalWeight + ", experienceLevel=" + experienceLevel
                + "]";
    }

    public void setUserId(Long id) {
        this.id = id;
    }

    public List<Rating> getRatedRoutines() {
        return ratedRoutines;
    }

    public void setRatedRoutines(List<Rating> ratedRoutines) {
        this.ratedRoutines = ratedRoutines;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public double getWeightChange() {
        return weightChange;
    }

    public void setWeightChange(double weightChange) {
        this.weightChange = weightChange;
    }

    public List<WeightChange> getWeightChanges() {
        return weightChanges;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWeightChanges(List<WeightChange> weightChanges) {
        this.weightChanges = weightChanges;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    // public void setWeightChanges(List<WeightChange> weightChanges) {
    //     this.weightChanges = weightChanges;
    // }

    // public boolean isAccountNonExpired() {
    //     return accountNonExpired;
    // }

    // public void setAccountNonExpired(boolean accountNonExpired) {
    //     this.accountNonExpired = accountNonExpired;
    // }

    // public boolean isAccountNonLocked() {
    //     return accountNonLocked;
    // }

    // public void setAccountNonLocked(boolean accountNonLocked) {
    //     this.accountNonLocked = accountNonLocked;
    // }

    // public boolean isCredentialsNonExpired() {
    //     return credentialsNonExpired;
    // }

    // public void setCredentialsNonExpired(boolean credentialsNonExpired) {
    //     this.credentialsNonExpired = credentialsNonExpired;
    // }

    // public boolean isEnabled() {
    //     return enabled;
    // }

    // public void setEnabled(boolean enabled) {
    //     this.enabled = enabled;
    // }
    
}
