package com.be05.market.dto;

import com.be05.market.entity.UserEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Builder @Data @AllArgsConstructor
public class UserDto implements UserDetails {

    @Getter
    private Long id;
    private String userId;
    private String password;
    @Getter
    private String email;
    @Getter
    private String phone;
    @Getter
    private String address;
    @Getter
    private String token;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserDto fromEntity(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .token(entity.getToken())
                .build();
    }

    public UserEntity newEntity(String password) {
        UserEntity entity = new UserEntity();
        entity.setUserId(userId);
        entity.setPassword(password);
        entity.setEmail(email);
        entity.setPhone(phone);
        entity.setAddress(address);
        entity.setToken(token);
        return entity;
    }
}
