package com.be05.market.entity;

import com.be05.market.service.PasswordValidatable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Data
@Entity
@Table(name = "sales_item")
public class ItemEntity implements PasswordValidatable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "제목을 작성해주세요.")
    private String title;

    @NotNull(message = "등록한 물품에 대한 설명을 작성해주세요.")
    private String description;

    private String imageURL;

    @NotNull(message = "등록한 물품에 대한 최소 가격을 작성해주세요.")
    private Long minPriceWanted;

    private String status;

    @NotNull(message = "작성자를 입력해주세요.")
    private String writer;

    private String password;

    @Override
    public void validatePassword(String password) {
        if (!getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
