package com.be05.market.controller;

import com.be05.market.entity.CustomUserDetails;
import com.be05.market.token.JwtRequestDto;
import com.be05.market.token.JwtTokenDto;
import com.be05.market.token.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserDetailsManager userManger;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils tokenUtils;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public JwtTokenDto generateJwt(@RequestBody JwtRequestDto dto) {
        UserDetails userDetails = userManger.loadUserByUsername(dto.getUserId());
        if (!passwordEncoder.matches(dto.getPassword(), userDetails.getPassword()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        JwtTokenDto response = new JwtTokenDto();
        response.setToken(tokenUtils.generateToken(userDetails));
        return response;
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
            userManger.createUser(CustomUserDetails.builder()
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
