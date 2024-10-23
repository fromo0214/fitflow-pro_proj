package com.example.html.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/login")
    public String login(){
        sendTestEmail();
        return "login";
    }

    public void sendTestEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("faromo0214@gmail.com");
        message.setSubject("Test Email");
        message.setText("This is a test email from FitFlow Pro.");
        message.setFrom("support@fitflowpro.pro");
        mailSender.send(message);
    }
}
