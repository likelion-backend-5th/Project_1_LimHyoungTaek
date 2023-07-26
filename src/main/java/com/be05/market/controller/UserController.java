package com.be05.market.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserDetailsManager userManger;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/profile")
    public String viewProfile() {
        return "profile";
    }

    @GetMapping("/register")
    public String signUpForm() {
        return "register";
    }

    @PostMapping("/register")
    public String signUp(
            @RequestParam("userId") String userId,
            @RequestParam("password") String password,
            @RequestParam("password-check") String passwordCheck) {
        if (password.equals(passwordCheck)) {
            userManger.createUser(User
                    .withUsername(userId)
                    .password(passwordEncoder.encode(password))
                    .build());
            return "redirect:/users/login";
        }
        return "redirect:/users/register?error";
    }
}
