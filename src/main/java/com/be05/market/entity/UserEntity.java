package com.be05.market.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
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

    private String token;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<ItemEntity> items = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ProposalEntity> negotiations = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<CommentEntity> comments = new ArrayList<>();
}