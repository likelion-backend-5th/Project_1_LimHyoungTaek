package com.be05.market.dto;

import com.be05.market.entity.CommentEntity;
import com.be05.market.entity.ItemEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    @NotNull(message = "작성자를 입력해주세요.")
    private String writer;
    private String password;
    @NotNull(message = "댓글을 작성해주세요.")
    private String content;
    private String reply;

    public static CommentDto fromEntity(CommentEntity entity) {
        CommentDto comments = new CommentDto();
        comments.setId(entity.getId());
        comments.setContent(entity.getContent());
        comments.setReply(entity.getReply());
        return comments;
    }

    public CommentEntity newEntity(ItemEntity item) {
        CommentEntity entity = new CommentEntity();
        entity.setItem(item); // 기존 item_id 대체
        entity.setContent(content);
        return entity;
    }
}
