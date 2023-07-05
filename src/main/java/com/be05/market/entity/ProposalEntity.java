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
    private Long id;
    private Long itemId;
    private Long suggestedPrice;
    private String status;
    private String writer;
    private String password;

    @Override
    public void validatePassword(String password) {
        if (!getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
