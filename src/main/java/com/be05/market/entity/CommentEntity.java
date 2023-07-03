package com.be05.market.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "대상 물품이 존재하지 않습니다.")
    private Long itemId;
    @NotNull(message = "작성자를 입력해주세요.")
    private String writer;
    private String password;
    @NotNull(message = "댓글을 작성해주세요.")
    private String content;
    private String reply;
}
