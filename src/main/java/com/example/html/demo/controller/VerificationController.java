package com.example.html.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.html.demo.model.User;
import com.example.html.demo.model.VerificationToken;
import com.example.html.demo.repository.VerificationTokenRepository;
import com.example.html.demo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class VerificationController {

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/verify")
    public String verifyAccount(@RequestParam("token")String token, Model model) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);

        if (verificationToken == null){
            model.addAttribute("verificationError", true);
            model.addAttribute("errorMessage", "Invalid verification token.");
            return "verify";
        }

        if (verificationToken.isExpired()){
            model.addAttribute("verificationError", true);
            model.addAttribute("errorMessage", "The verification token has expired.");
            return "verify";
        }

        User user = verificationToken.getUser();
        user.setEnabled(true);
        userService.saveUserDetails(user);

        model.addAttribute("verificationSuccess", true);
        return "redirect:/login?emailSuccessfullyVerified";
    }
    

}
