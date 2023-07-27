package com.be05.market.controller;

import com.be05.market.dto.UserDto;
import com.be05.market.service.JpaUserDetailsManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserDetailsManager userManger;
    private final JpaUserDetailsManager jpaUserDetailsManager;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login") @ResponseBody
    public UserDto generateJwt(@RequestBody UserDto dto) {
        return jpaUserDetailsManager.loginToSaveToken(dto);
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
            @RequestParam("password-check") String passwordCheck,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam("address") String address) {
        if ((!userId.isEmpty() && !password.isEmpty())
                && password.equals(passwordCheck)) {
            userManger.createUser(UserDto.builder()
                    .userId(userId)
                    .password(passwordEncoder.encode(password))
                    .phone(phone)
                    .email(email)
                    .address(address)
                    .build());
            return "redirect:/users/login";
        }
        return "redirect:/users/register?error";
    }
}