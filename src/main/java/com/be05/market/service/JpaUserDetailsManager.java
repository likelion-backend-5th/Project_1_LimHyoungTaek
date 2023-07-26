package com.be05.market.service;

import com.be05.market.entity.CustomUserDetails;
import com.be05.market.entity.UserEntity;
import com.be05.market.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class JpaUserDetailsManager implements UserDetailsManager {
    private final UserRepository userRepository;

    public JpaUserDetailsManager(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        createUser(CustomUserDetails.builder()
                .userId("user")
                .password(passwordEncoder.encode("asdf"))
                .email("user@naver.com")
                .phone("010-1234-5678")
                .address("경기 안산")
                .build());
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findByUserId(userId);
        if (optionalUser.isEmpty()) throw new UsernameNotFoundException(userId);
        return CustomUserDetails.fromEntity(optionalUser.get());
    }

    @Override
    public void createUser(UserDetails user) {
        if (this.userExists(user.getUsername()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        try {
            this.userRepository.save(((CustomUserDetails) user).newEntity());
        } catch (ClassCastException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void updateUser(UserDetails user) {
    }

    @Override
    public void deleteUser(String username) {
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
    }

    @Override
    public boolean userExists(String userId) {
        return this.userRepository.existsByUserId(userId);
    }
}
