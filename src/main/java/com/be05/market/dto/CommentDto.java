package com.be05.market.dto;

import com.be05.market.entity.CommentEntity;
import com.be05.market.entity.ItemEntity;
import com.be05.market.entity.UserEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    @NotNull(message = "댓글을 작성해주세요.")
    private String content;
    private String reply;

    public CommentEntity newEntity(ItemEntity item, UserEntity userEntity) {
        CommentEntity entity = new CommentEntity();
        entity.setItem(item); // 기존 item_id 대체
        entity.setContent(content);
        entity.setUser(userEntity);
        return entity;
    }
}
