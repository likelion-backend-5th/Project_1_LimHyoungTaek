package com.be05.market.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // unique = true -> sqlite error
    private String userId;
    @Column(nullable = false)
    private String password;

    private String phone;
    private String email;
    private String address;
}