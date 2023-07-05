package com.be05.market.dto;

import com.be05.market.entity.CommentEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    @NotNull(message = "대상 물품이 존재하지 않습니다.")
    private Long itemId;
    @NotNull(message = "작성자를 입력해주세요.")
    private String writer;
    private String password;
    @NotNull(message = "댓글을 작성해주세요.")
    private String content;
    private String reply;

    public static CommentDto fromEntity(CommentEntity entity) {
        CommentDto comments = new CommentDto();
        comments.setId(entity.getId());
        comments.setItemId(entity.getItemId());
        comments.setWriter(entity.getWriter());
        comments.setPassword(entity.getPassword());
        comments.setContent(entity.getContent());
        comments.setReply(entity.getReply());
        return comments;
    }
}
