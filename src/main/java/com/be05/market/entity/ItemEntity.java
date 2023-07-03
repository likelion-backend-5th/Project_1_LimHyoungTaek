package com.be05.market.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "sales_item")
public class ItemEntity {
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

    @NotNull(message = "작성자를 작성해주세요.")
    private String writer;

    private String password;
}
