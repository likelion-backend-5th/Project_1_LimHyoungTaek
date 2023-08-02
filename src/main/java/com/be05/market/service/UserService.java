package com.be05.market.service;

import com.be05.market.dto.UserDto;
import com.be05.market.entity.Role;
import com.be05.market.entity.UserEntity;
import com.be05.market.repository.UserRepository;
import com.be05.market.token.JwtTokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils tokenUtils;

    // 나중에 지우고 @RequiredArgsConstructor
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtTokenUtils tokenUtils)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenUtils = tokenUtils;

        createUser(UserDto.builder()
                .userId("운영자")
                .password("asdf")
                .email("admin@naver.com")
                .phone("010-1234-5678")
                .address("경기 안산")
                .role(Role.ROLE_ADMIN)
                .build());
    }

    public void createUser(UserDto dto) {
        if (this.userExists(dto.getUserId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        try {
            UserEntity userEntity =
                    dto.newEntity(passwordEncoder.encode(dto.getPassword()));
            this.userRepository.save(userEntity);
        } catch (ClassCastException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public UserDto loginToSaveToken(UserDto dto) {
        UserEntity userEntity = userRepository.findByUserId(dto.getUserId())
                .orElseThrow(() -> new UsernameNotFoundException(dto.getUserId()));

        UserDetails userDetails = UserDto.fromEntity(userEntity);
        if (!passwordEncoder.matches(dto.getPassword(), userDetails.getPassword()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        String token = tokenUtils.generateToken(dto);
        userEntity.setToken(token);
        userRepository.save(userEntity);
        return UserDto.fromEntity(userEntity);
    }

    public boolean userExists(String userId) {
        return this.userRepository.existsByUserId(userId);
    }
}