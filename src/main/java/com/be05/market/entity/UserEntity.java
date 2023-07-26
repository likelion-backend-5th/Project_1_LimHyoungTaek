package com.be05.market.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "아이디를 입력해주세요.")
    @Column(nullable = false) // unique = true -> sqlite error
    private String userId;
    @NotNull(message = "비밀번호를 입력해주세요.")
    private String password;

    private String phone;
    private String email;
    private String address;
}