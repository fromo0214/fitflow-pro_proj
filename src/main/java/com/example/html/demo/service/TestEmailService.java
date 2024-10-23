// package com.example.html.demo.service;

// import org.springframework.mail.SimpleMailMessage;
// import org.springframework.mail.javamail.JavaMailSender;

// import org.springframework.beans.factory.annotation.Autowired;

// import org.springframework.stereotype.Service;

// @Service
// public class TestEmailService {

//     @Autowired
//     private JavaMailSender mailSender;

//     public void sendTestEmail() {
//         SimpleMailMessage message = new SimpleMailMessage();
//         message.setTo("faromo0214@gmail.com");
//         message.setSubject("Test Email");
//         message.setText("This is a test email from FitFlow Pro.");
//         message.setFrom("support@fitflowpro.pro");

//         mailSender.send(message);
//     }
// }
