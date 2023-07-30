package com.be05.market.entity;

import com.be05.market.service.PasswordValidatable;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Data
@Entity
@Table(name = "comment")
public class CommentEntity implements PasswordValidatable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    private String content;
    private String reply;

    @ManyToOne
    @JoinColumn(name = "item")
    private ItemEntity item;

    @ManyToOne
    @JoinColumn(name = "user")
    private UserEntity user;

    @Override
    public void validatePassword(String password) {
        if (!user.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
