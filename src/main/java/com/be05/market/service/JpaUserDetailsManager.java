package com.be05.market.service;

import com.be05.market.entity.UserEntity;
import com.be05.market.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class JpaUserDetailsManager implements UserDetailsManager {
    private final UserRepository userRepository;

    public JpaUserDetailsManager(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        createUser(User.withUsername("user")
                .password(passwordEncoder.encode("asdf"))
                .build()
        );
    }


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findByUserId(userId);
        if (optionalUser.isEmpty()) throw new UsernameNotFoundException(userId);

        UserEntity userEntity = optionalUser.get();
        return User.withUsername(userEntity.getPassword())
                .build();
    }

    @Override
    public void createUser(UserDetails user) {

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
