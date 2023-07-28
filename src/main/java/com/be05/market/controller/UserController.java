package com.be05.market.controller;

import com.be05.market.dto.ResponseDto;
import com.be05.market.dto.UserDto;
import com.be05.market.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService jpaUserDetailsManager;
    private final ResponseDto responseDto = new ResponseDto();

    @PostMapping("/login")
    public UserDto generateJwt(@RequestBody UserDto dto) {
        return jpaUserDetailsManager.loginToSaveToken(dto);
    }

    @PostMapping("/register")
    public ResponseDto signUp(@RequestBody UserDto dto) {
        if ((!dto.getUserId().isEmpty() && !dto.getPassword().isEmpty())) {
            jpaUserDetailsManager.createUser(dto);
            responseDto.setMessage("회원가입 성공");
            return responseDto;
        }
        responseDto.setMessage("회원가입 실패");
        return responseDto;
    }
}