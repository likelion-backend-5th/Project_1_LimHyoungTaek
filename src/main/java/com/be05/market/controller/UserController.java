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
        // users/register/view 에서 이미 처리했지만 Postman 입력 대비
        if (dto.getUserId().isEmpty() || dto.getPassword().isEmpty())
            responseDto.setMessage("아이디 혹은 비밀번호를 입력해주세요.");
        else {
            jpaUserDetailsManager.createUser(dto);
            responseDto.setMessage("회원가입 성공");
        }
        return responseDto;
    }
}