package com.be05.market.dto;

import com.be05.market.entity.CommentEntity;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private Long itemId;
    private String writer;
    private String password;
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
