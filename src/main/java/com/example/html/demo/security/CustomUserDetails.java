// package com.example.html.demo.security;

// import java.util.Collection;
// import java.util.Collections;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import com.example.html.demo.model.User;

// public class CustomUserDetails implements UserDetails{
//     private final User user;

//     public CustomUserDetails(User user){
//         this.user = user;
//     }
//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.emptyList();
//     }

//     @Override
//     public String getPassword() {
//         return user.getPasswd();
//     }

//     @Override
//     public String getUsername() {
//         // TODO Auto-generated method stub
//         return user.getUsername();
//     }

//     // @Override
//     // public boolean isAccountNonExpired() {
//     //     //check users account is not expired 
//     //     return user.isAccountNonExpired();
//     // }

//     // @Override
//     // public boolean isAccountNonLocked() {
//     //     //check users account is not locked 
//     //     return user.isAccountNonLocked();
//     // }

//     // @Override
//     // public boolean isCredentialsNonExpired() {
//     //     //check users password is not expired 
//     //     return user.isCredentialsNonExpired();
//     // }

//     // @Override
//     // public boolean isEnabled() {
//     //     //checks if the users account is enabled 
//     //     return user.isEnabled();
//     // }

// }
