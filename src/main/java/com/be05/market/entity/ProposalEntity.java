package com.be05.market.entity;

import com.be05.market.service.PasswordValidatable;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Data
@Entity
@Table(name = "negotiation")
public class ProposalEntity implements PasswordValidatable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "negotiation_id")
    private Long id;
    private Long suggestedPrice;
    private String status;

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
