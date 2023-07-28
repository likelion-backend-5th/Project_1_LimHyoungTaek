package com.be05.market.entity;

import com.be05.market.service.PasswordValidatable;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "sales_item")
public class ItemEntity implements PasswordValidatable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String imageURL;
    private Long minPriceWanted;
    private String status;
    private String writer;
    private String password;

//    @OneToMany(mappedBy = "items")
//    private List<CommentEntity> itemComments = new ArrayList<>();
//
//    @OneToMany(mappedBy = "item")
//    private List<ProposalEntity> itemNegotiations = new ArrayList<>();
//
    @ManyToOne
    @JoinColumn(name = "user")
    private UserEntity user;

    @Override
    public void validatePassword(String password) {
        if (!getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
