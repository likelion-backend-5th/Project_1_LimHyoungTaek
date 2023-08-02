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
    public ResponseDto signUp(@RequestBody UserDto dto,
                              @RequestParam("passwordCheck") String passwordCheck) {
        // users/register/view 에서 이미 처리했지만 Postman 입력 대비
        if (dto.getUserId().isEmpty() || dto.getPassword().isEmpty())
            responseDto.setMessage("아이디 혹은 비밀번호를 입력해주세요.");
        // json 데이터로 다 받아오고 싶지만 내 지식의 한에서는 entity에 추가해야하기 때문에
        // 그냥 form-data로 받아오는 것으로 수정
        if (dto.getPassword().equals(passwordCheck))
            responseDto.setMessage("비밀번호가 일치하지 않습니다. 비밀번호를 확인해주세요.");
        else {
            jpaUserDetailsManager.createUser(dto);
            responseDto.setMessage("회원가입 성공");
        }
        return responseDto;
    }
}