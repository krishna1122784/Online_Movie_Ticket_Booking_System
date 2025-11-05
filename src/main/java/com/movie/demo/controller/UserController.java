package com.movie.demo.controller;

import com.movie.demo.entity.User;
import com.movie.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String signupForm(User user) {
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(User user) {
        userRepository.save(user);
        return "redirect:/signup-success";
    }

    @GetMapping("/signup-success")
    public String signupSuccess() {
        return "signup-success";
    }
}